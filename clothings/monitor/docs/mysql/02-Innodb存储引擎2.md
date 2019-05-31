
# 02-Innodb存储引擎

- 2.1 InnoDB体系结构
	* 后台线程
	* 内存
- 2.2 CheckPoint技术 

##  2.1 InnoDB体系结构

InnoDB有很多内存块，这些内存块组成了一个大的内存池，负责如下工作：

- 维护线程需要访问的多个数据结构；
- 缓存磁盘上的数据，方便快速的读取；
- 重做日志(redo log)缓冲；

同时InnoDB还有很多后台线程，主要作用是刷新内存池的数据，保证缓冲池的数据是最近的数据，此外将已修改的数据刷到磁盘文件中。同时保证数据库发生异常时，InnoDB能恢复到正常情况。

### 2.1.1 后台线程

InnoDB有很多不同的后台线程，处理不同的事情。

**1. Master Thread**

Master Thread是一个非常核心的线程，主要工作是将缓冲池的数据异步刷到磁盘，包括脏页的刷新、合并插入缓冲、Undo页的回收等。

**2. IO Thread**

IO Thread主要负责IO请求的回调。

**3. Purge Thread**

事物被提交后、其所使用的undolog可能不在需要，Purge Thread 就是来回收该Undo页的。

**4. Purge Cleaner Thread**

是InnoDB 1.2.x版本中引入的，其作用是将之前版本中的脏页刷新的操作有Master Thread放到该线程来。

### 2.1.2 内存

**1. 缓冲池**

InnoDB对磁盘的操作都是以页为单位的，一页默认16K,可以存储多行记录，在内存里，一页也是一个节点，该节点可以是叶子和非叶子节点。在B+Tree中，只有叶子节点才真正有数据。缓冲池简单来说就是一块内存区域，数据库读取数据首先从缓冲池读取，若没有则从磁盘刷入到缓冲池里，下次读取就先判断缓冲池是否有。缓冲池缓冲的类型有：

- 缓冲池 (innodb_buffer_pool)：
	* 数据页（data page）
	* 索引页(index page)
	* 插入缓冲(insert buffer)
	* 锁信息(lock info)
	* 自适应哈希索引
	* 数据字典
- 重做日志缓冲 （redo log buffer）	
- 额外内存池 (innodb_additional_mem_pool_size)

## 2.2 CheckPoint技术 

CheckPoint技术主要解决重做日志过大，恢复时间过长的问题，CheckPoint会在某些时间刷新脏页。CheckPoint发生的时间有如下几种情况：

- **Sharp CheckPoint**: 数据库关闭时将所有的脏页刷回磁盘；
- **Master Thread CheckPoint**: Master Thread差不多以每秒取脏页列表的一部分刷入磁盘。 
- **Flush LRU List CheckPoint**: 刷新LRU列表检查，InnoDB引擎要保证LRU列表中差不多有100个空闲页可供使用，在1.1.x版本之前，检查LRU是否足够的可以空间操作发生在用户查询线程，显然这会阻塞用户查询操作，1.2.x开始这个检查放在Page Cleaner线程进行。检查LRU若没有100个空闲页，那将会从LRU尾端移除，如果移除的列表中有脏页就进行CheckPoint。
- **Async/Sync CheckPoint**: 重做日志文件不可用情况，这时候需要强制刷新脏页，



# Mysql Questions #

- MYSQL 复制原理及流程
- MyISAM和Innodb的区别
- MYSQL VARCHAR(50)代表的含义，char与varchar的区别
- Innodb事物与日志的实现方式
- Mysql binlog几种日志记录格式及区别
- MYSQL CPU飙到500%怎么处理
- SQL优化重点内容
	* explain结构各item的意义
	* profile 意义及使用场景
- 备份计划，mysqldump及xtranback的原理
- 500台代表在最快时间内重启
- Innodb读写参数优化
- 你是如何监控数据库的，慢日志是怎么查询的
- 是否做过主从一致性校验
- 你们数据库是否支持emoji表情，如果不支持，怎么处理
- 如何维护数据库的数据字典
- 是否有开发规范，怎么执行
- 表中有大字段，且字段不会经常更新，会继续存放在该表中，还是选择拆成子表
- Innodb行锁是通过加在什么伤实现的
- 如何从mysqldump产生的全库备份文件中，只恢复一个库或一个表
- 一个6亿表A、一个3亿表B，通过TID关联，是如何快速查询第50000~50200的这200条记录的


## MYSQL 复制原理及流程  ##

- 主MYSQL-binlog线程：记录下所有改变数据库数据的语句，存入master的binlog文件中
- 从-IO线程：和主线程建立主从关系后，从IO线程负责拉去master的binlog文件内容，放入relay log文件
- 从-SQL执行线程：执行relay-log文件中SQL语句

## MyISAM和Innodb的区别  ##

- Innodb支持事物，而MYISAM不支持
- Innodb支持行级锁，而MYISAM只支持表锁
- BTREE实现方式不同：Innodb支持主键聚簇索引，而Myisam是非聚簇索引

## MYSQL VARCHAR(50)代表的含义，char与varchar的区别  ##

- 50代表的含义是最多存储50个字节，VARCHAR(50)和VARCHAR(200)存储‘hello’使用字节空间是一样的，但是后者在排序时会消耗更多内存，因为order by col采用200来计算的。

## Innodb事物与日志的实现方式  ##

	有多少种日志：
- 错误日志：记录出错信息，也记录一些警告信息
- 查询日志：记录所有对数据库请求的信息
- 慢查询日志：设置一个阀值，查询时间超过该值的sql将被记录到SQL慢查询日志中
- 二进制日志：记录所有对数据更改的操作-binlog
- 中继日志：事物日志

	事物是如何通过日志来实现的
- 事物是通过redo和InnoDB存储引擎日志缓冲来实现的
- 当开始一个事物时，会记录一个事物的序列号lsn
- 当事物执行时，会往事物日志缓冲区插入事物日志
- 当事物提交时，将日志缓冲区的日志刷入磁盘。
- 总结：就是在写入数据前先写日志。

## Mysql binlog几种日志记录格式及区别  ##

	Statement格式
- 每一条修改数据的SQL都会记录在binlog文件
	* 优点：不需要记录每一行的变化，减少binlog日志量，减少IO提高性能。（相比ROW能节约多少性能取决于应用）	

	Row格式
- 不记录sql上下文数据，只记录那条记录被修改

	Mixed格式
- 取两个中间值

## MYSQL CPU飙到500%怎么处理  ##

- show processlist 列出所有进程，查看是否有进程多少秒状态没有变化，有就干掉。查看超时日志或错误日志，一般会是大数据的查询和大批量的插入导致CPU爆表。

## SQL优化重点内容  ##

- explain各item含义：
	* select_type: 每个查询的type
	* type(重要)：表示查到数据使用了那种方式：const -> eq_ref -> ref -> rang -> index -> all. 最好控制在 rang内
	* key: 是否使用到索引
	* key_len: 用到索引长度, 是否全部利用到索引长度，尽量用全量

	
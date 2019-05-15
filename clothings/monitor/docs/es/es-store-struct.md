
# ES存储结构详解 #
	本文我们将研究ES的各个部分写入数据目录的文件，我们将查看节点、索引和分片级文件。并简要说明其内容，以便了解ES写入磁盘的数据。
	
- 从ES路径说起
- 文件从哪来
- 节点数据
- 索引数据
- 分片数据
- 每个分片的事物日志translog
- Lucene索引文件
- 修复碎片问题
- 存储快照
- 补充知识
- 每个segment索引包括信息
	
## 从ES路径说起 ##

	ES配置了多个路径：
- path.home: 运行ES进程的主目录，默认为usr.dir
- path.conf: 配置文件目录
- path.plugins：插件目录
- path.logs: 存储生成的日志位置，如果其中一卷磁盘空间不足，可以放在不同卷上。
- path.data: ES存储数据的文件夹

## 文件从哪来  ##
	ES使用lucene来处理分片级别的索引和查询，因此数据目录中的文件是由ES和Lucene写入的，两者职责非常明确：
- Lucene负责写和维护Lucene索引文件
- 而ES在Lucene之上，写与功能相关的元数据，如：字段映射、索引设置、集群数据。
	在深入研究Lucene索引文件之前，让我们先了解一下ES编写的外部级数据。
	
## 节点数据  ##
	启动ES就会有以下文件生成
- node.lock: 节点锁文件

## 索引数据  ##
	索引名下面有两个目录_state和0/1/2(分片数)
- _state：文件包含了索引状态信息，state-{version}.st，其中包含有索引的元数据，如索引的创建时间、索引的设置和映射。
- 0/1/2: 包含了索引相关分片数据。
	接下来我们仔细研究一下分片数据
	
## 分片数据  ##	
	分片数据目录包含分片的状态文件、分片是主分片还是副分片信息
- index: index目录包含lucene索引文件
- _state: 分片状态信息
- translog：es事物文件，translog对于ES的性能和功能非常重要，下面我们先着重讲解一下。

## 每个分片的事物日志translog  ##

- ES事物日志可以让数据安全的索引到ES，而无需为每个文档执行低级Lucene提交，提交Lucene索引会在Lucene级别创建一个新的Segment，即执行fsync(),会导致大量的磁盘IO影响性能。
- 为了接受索引文档并使其能被搜索，而不需要完整Lucene提交，ES将文档添加到IndexWriter,并将其添加到事物日志中，在每个refresh_interval之后，它将在Lucene调用reopen()方法，这将使数据在不需要提交的情况下可以被搜索。这是Lucene Near Real Api的一部分，当IndexWriter最终由于刷新事物日志或显示刷新操作而提交时，先前的事物日志将被丢弃，新的事物日志取代它。
- 如果需要恢复，将首先恢复在Lucene中写入的Segments，然后然后重放translog日志，以防丢失尚未写入磁盘的数据。
	
## Lucene索引文件  ##

	Lucene在记录索引文件方面做得很好，下面解释lucene索引目录下各个文件：
	
| 文件名称        | 文件后缀   |	描述   |
| --------   | -----: | -----: |
| Segment file     | segment_N | 存储段文件提交信息 |
| Lock file     | write.lock | 以防多个IndexWriter同时写一个文件 |
| Segment info     | .si | 存储一个段的元数据 |
| Fields		   | .fnm | 存储一个段下的字段信息，类似定义 |
| Field Index   | .fdx | 存储指向字段数据的指针 |
| Field Data   | .fdt | 存储文档的字段数据 |
| Term Dictionary   | .tim | Term词典，存储term信息 |
| Term Index   | .tip | 存储到Term Dictionary 的索引 |
| Position    | .pos | 存储词在索引的位置信息 |
| Per-Document Values    | .dvd, .dvm  | .dvd 保存索引文档评分因子的元数据，.dvm 保存评分数据 |


## 修复碎片问题 ##

- CheckIndex: Lucene提供了索引检测工具，通过该工具可以扫描和修复有问题的段，使用这个修复，通常只会丢失很少的数据。
- 通常我们建议ES用户重新索引数据（根据传统DB）
- 如果CheckIndex扫描后，修复建议比较合理，可以使用-fix命令

##  补充知识  ##
	一份数据写入ES会产生多份数据用于不同的查询方式，会比原始数据占用更多的磁盘，而索引settings里设置'codec':'best_compression'是指针对_source进行压缩的，压缩比为6
总结：
- 存储原文_source的文件：.fdt,fdm,fdx
- 存储倒排索引的文件：.tim,.tip,.doc	
- 用于聚合排序的文件：.dvd, .dvm
- 全文检索文件：	

## 每个segment索引包括信息  ##










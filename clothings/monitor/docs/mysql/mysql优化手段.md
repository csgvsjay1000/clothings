
# Mysql 优化手段 #

- 索引优化
- 缓冲池优化
- 查询优化


## 索引优化

- 尽可能使用较短单列作为主键

## 缓冲池优化

- 在内存充裕时，可以扩大插入缓冲池的大小

## 查询优化

使用explain分析SQL语句

- 主要关注：key, key_len, 

索引匹配原则

- 全列匹配
- 最左匹配
- 当查询条件使用了精准匹配，但中间某个条件未提供，在没有利用到最左匹配时，可以考虑填坑方式来提升性能，注意坑的数据量。
- 范围查询中，索引最多用于一个范围查询，而不行全部使用
- 查询条件中含有表达式或函数，则也不能使用索引


- 前缀索引兼顾索引大小和长度
- 在考虑建索引时，优先考虑索引的选择性，级基数的大小，可以去联合索引以及前缀的大小区查询匹配


## MySQL配置详解

- innodb_flush_log_at_trx_commit:重做日志刷盘策略，该参数默认值为1、即事物提交必须调用fsync。
- binlog\_cache\_size: 二进制日志缓冲大小，默认为32K,基于会话的，可以通过查看binlog\_cache\_use和binlog\_cache\_disk\_use来判断size设置是否合适。
- innodb\_data\_file\_path进行设置，可以设置多个表空间文件。可以将多个文件映射到不同的磁盘上，可以提高数据库性能。




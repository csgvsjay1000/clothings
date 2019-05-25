
# Mysql Innodb Store Struct #

	Innodb 存储结构

- 简介

## 简介  ##

- 从逻辑上讲，数据都被放在一个tablespace空间中，空间由由很多Segment段组成，Segment由很多Extend组成，Extend由Page组成，Page由Row组成。
- 即：TableSpace -> Segment(leaf/non-leaf/rollback node segment)  ->  Extend  -> Page  -> Row 
- 在Mysql中会有一个默认的共享表空间ibdata1,如果设置了innodb_file_per_table=on则会为每个表放在各自的tablespace,否则都统一使用共享表空间
- 私有表空间，仅包括数据、索引、插入缓冲Bitmap页

- 常见的Segment有：数据段、索引段、回滚段。
- Extend区是由连续Page组成，任何情况下大小为1M
	* 为了保证连续性Innodb一次从磁盘申请4~5个区，页的大小为16K,所以一个Extend有64个Page
- Page是Innodb管理磁盘最小的单位，默认16K。常见的页有：数据页、undo页、系统页、事物页。
- 数据是按行存放的，每页最少存放2行，最多7992行。	
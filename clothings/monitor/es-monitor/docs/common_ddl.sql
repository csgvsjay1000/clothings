
CREATE TABLE `t_node_jvm` (
  `fid` bigint(20) NOT NULL COMMENT '主键id',
  `ip` varchar(64) NOT NULL COMMENT '节点IP',
  `heap_used_in_bytes` bigint(20) NOT NULL COMMENT '堆内存使用量',
  `heap_used_percent` int(8) NOT NULL COMMENT '堆内存使用量百分比',
  `heap_committed_in_bytes` bigint(20) NOT NULL COMMENT '稳定内存字节数',
  `heap_max_in_bytes` bigint(20) NOT NULL COMMENT '最大内存字节数',
  `threads_count` int(8) NOT NULL COMMENT '线程数',
  `young_collection_count` bigint(20) NOT NULL COMMENT '年轻代垃圾回收次数',
  `young_avg_collection_time_in_millis` int NOT NULL COMMENT '最近年轻代垃圾平均回收时间',
  `young_collection_frequency` int NOT NULL COMMENT '最近年轻代垃圾频率，比如一天回收几次',
  `old_collection_count` bigint(20) NOT NULL COMMENT '年老代垃圾回收次数',
  `old_avg_collection_time_in_millis` int NOT NULL COMMENT '最近年老代垃圾平均回收时间',
  `old_collection_frequency` int NOT NULL COMMENT '最近年老代垃圾频率，比如一天回收几次',
  `create_date` timestamp COMMENT '创建时间',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='节点JVM监控表';


CREATE TABLE `t_node_system` (
  `fid` bigint(20) NOT NULL COMMENT '主键id',
  `ip` varchar(64) NOT NULL COMMENT '节点IP',
  `fs_free_in_bytes` bigint(20) NOT NULL COMMENT '磁盘剩余空间',
  `cpu_percent` int(8) NOT NULL COMMENT 'cpu使用率',
  `open_file_descriptors` int NOT NULL COMMENT '打开文件描述符数',
  `rx_size_in_bytes` bigint(20) NOT NULL COMMENT '节点接收字节数',
  `rx_rate_in_bytes` bigint(20) NOT NULL COMMENT '节点接收速率',
  `tx_size_in_bytes` bigint(20) NOT NULL COMMENT '节点发送字节数',
  `tx_rate_in_bytes` bigint(20) NOT NULL COMMENT '节点发送速率',
  `create_date` timestamp COMMENT '创建时间',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='节点系统负载监控表';


CREATE TABLE `t_node_docs` (
  `fid` bigint(20) NOT NULL COMMENT '主键id',
  `ip` varchar(64) NOT NULL COMMENT '节点IP',
  `docs_count` bigint(20) NOT NULL COMMENT '文档数',
  `docs_store_size_in_bytes` bigint(20) NOT NULL COMMENT '文档存储总字节数',
  `index_avg_time_in_millis` int NOT NULL COMMENT '索引请求平均耗时',
  `index_current` int NOT NULL COMMENT '当前索引请求数',
  `get_avg_time_in_millis` int NOT NULL COMMENT 'get请求平均耗时',
  `get_current` int NOT NULL COMMENT 'get索引请求数',
  `query_avg_time_in_millis` int NOT NULL COMMENT 'query请求平均耗时',
  `query_current` int NOT NULL COMMENT 'query索引请求数',
  `fetch_avg_time_in_millis` int NOT NULL COMMENT 'fetch请求平均耗时',
  `fetch_current` int NOT NULL COMMENT 'fetch索引请求数',
  `flush_avg_time_in_millis` int NOT NULL COMMENT 'flush请求平均耗时',
  `create_date` timestamp COMMENT '创建时间',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='节点文档监控表';


CREATE TABLE `t_node_thread_pool` (
  `fid` bigint(20) NOT NULL COMMENT '主键id',
  `ip` varchar(64) NOT NULL COMMENT '节点IP',
  `write_queue` int NOT NULL COMMENT '写请求线程池队列数',
  `write_total_rejected` int NOT NULL COMMENT '总拒绝数',
  `write_rejected_one_day` int NOT NULL COMMENT '最近一天拒绝数',
  `search_queue` int NOT NULL COMMENT '查询请求线程池队列数',
  `search_total_rejected` int NOT NULL COMMENT '总拒绝数',
  `search_rejected_one_day` int NOT NULL COMMENT '最近一天拒绝数',
  `get_queue` int NOT NULL COMMENT 'get请求线程池队列数',
  `get_total_rejected` int NOT NULL COMMENT '总拒绝数',
  `get_rejected_one_day` int NOT NULL COMMENT '最近一天拒绝数',
  `create_date` timestamp COMMENT '创建时间',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='节点线程池监控表';



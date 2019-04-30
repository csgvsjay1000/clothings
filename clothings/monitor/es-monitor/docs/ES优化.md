## ElasticSearch优化-集群节点规划

### 1、节点职责单一,各司其职 ###

- 数据节点
	配置：node.master: false    node.data: true
	该node只作为一个数据节点，只存储索引数据和数据查询
- master节点
	配置: node.master: true 	  node.data: false
	该node不存储任何索引数据，该节点只是来创建索引请求和查询请求, 将这些请求合理分发到相关node服务器上
- client节点
	配置: node.master: false 	  node.data: false
	该节点只是再并发特别大的时候可以引入，只负责路由数据的查询，不存储数据

### 2、关闭数据节点HTTP服务 ###
	
	http.enable: false 来禁用http服务


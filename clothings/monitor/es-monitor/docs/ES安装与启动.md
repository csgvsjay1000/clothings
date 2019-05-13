## ElasticSearch知识整理

### ES的安装与启动 ###

***1、基础服务***

- 安装并配置java环境
- 下载 并解压 elasticsearch-6.2.4.tar.gz
- es 不能使用root账户启动, 所以解压之后修改es安装目录所属账户 chown -R es_user:es_user .
- cd  elasticsearch-6.2.4/bin
- ./elasticsearch 启动
- ./elasticsearch -d  后台启动
- kill pid  停止es服务

***2、重要配置***

- network.host 
	es默认绑定127.0.0.1回环地址，通常需要改成 192.168.5.131
- cluster.name 集群名字,如 clo-prod  (配置了集群名字后，客户端连接时也要设置集群名字)
- node.name 节点名字 clo-prod_1



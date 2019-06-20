
## Redis 常用设置

### 1 基本设置

	#端口设置，默认6379 
	port 6379

### 2 内存设置

	#最大内存100m
	maxmemory 100mb

	#如果内存满了，按照相应算法删除数据
	
	# 根据LRU算法移除过期key
	maxmemory-policy volatile-lru

	# redis并不是真正LRU/TTL，而是基于采样移除的。即采样10个数据，移除过期的。
	maxmemory-samples 10

Redis并不是真正LRU，Memcached是真正LRU算法。此处要根据实际情况设置缓存淘汰策略。

- 如果缓存数据设置了过期时间，我们可以设置 volatile-lru.
- 如果缓存未设置过期时间，此时可以考虑使用 allkeys-lru/allkeys-random.
- 如果数据不允许删除，那就使用noeviction.

内存大小尽量设置在系统内存的60%~80%之间。因为客户端，主从复制时都需要缓冲区，这些也是耗费系统内存的。

Redis 本身是单线程的，我们可以在如一个128G内存，多核CPU下，开启多个实例，以提高系统吞吐量。如设置10个实例，每个实例6~8G。

### 3 Redis 主从

在实际项目中，为了提高系统吞吐量，我们使用主从策略，即数据写到主redis，读的时候从从redis读，这样可以通过挂载多个从来提高吞吐量。另外可以通过从叶子节点开启持久化方式防止数据丢失。
	
	# 在配置文件上挂载主从，不推荐这种方式。
	slave masterIp masterPort
	# 当主从失去连接或正在复制时，从响应客户端（可能返回过期数据）还是返回‘SYNC wait master in progress’错误。默认yes，响应客户端。
	slave-serve-stale-data yes
	# 从库默认每10s向主库发送ping消息，测试连接是否正常。
	repl-ping-slave-period 10
	# 设置复制超时时间（SYNC期间批量I/O，以及PING的超时时间）,确保此值大于repl-ping-slave-period时间
	repl-timeout 60

	# 当主从断开连接时，主的复制缓冲区大小。仅在第一个从断开时创建一个，该缓冲区越大，则主从可断开的时间越长，否则从重新连接上会发生全量同步
	repl-backlog-size 1mb
	# 当主从断开连接时，主复制缓冲区多久清空该内存区域，清空之后就需要全量复制了
	repl-backlog-ttl 3600
	# 客户端缓冲区(可能是普通客户端、复制客户端),缓冲区超过256mb就直接断开连接，或者持续60s超过60mb就断开连接
	client-output-buffer-limit slave 256mb 60mb 60

### 4 Redis 持久化

Redis虽然不适合持久化存储，但有时为了防止数据丢失还是会做持久化存储，可以只挂载一个从(叶子节点)只做持久化操作。这样其他节点挂了，我们也可以通过这个节点去恢复数据。持久化主要有RDB和AOF两种方式。

**AOF**：append only file 文件追加方式

	
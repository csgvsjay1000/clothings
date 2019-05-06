
## Netty知识梳理

- Netty的各大组件
- Netty执行流程
- Netty线程模型
- 了解哪几种序列化协议？各种协议使用场景
- Netty零拷贝实现
- Netty的高性能体现在哪方面

### 1、Netty的各大组件 ###

- Channel接口: 封装了底层绑定，连接，读写操作
- EventLoop接口：是Selector的封装，处理了在连接过程中发生的事件的核心抽象
- ChannelFuture接口: Netty所有操作都是异步，因此提供了ChannelFuture，其有addListener提供回调。
- ChannelHandler接口：用来处理数据进站和出站的用户逻辑。其子接口主要有ChannelInboundHandler（处理客户有连接，或断开连接，接收数据）
- ChannelPipeline接口：所有的ChannelHandler会在Pipeline中传递，pipeLine是一个责任链模式.
	
### Netty服务端执行流程  ###

- 创建ServerBootstrap实例
- 设置并绑定Reactor线程池：ServerBootstrap实例关联两个事件循环组(bossGroup,workGroup)
- 设置并绑定服务端Channel: 用于接收socket连接请求，ServerBootstrap实例.channel(NioServerSocketChannel.class)
- 设置网络处理事件的handler和pipeLine, handler完成多数的功能定制，如编码解码，接收数据，解码之后，实现自己的逻辑

### Netty线程模型  ###

	分为BossThread和workThread，以及事件处理线程
- Boss线程负载接收新的accept连接，并将连接注册到workthread的EventLoop中，
- workEventLoop接收到读事件后，利用事件处理线程编解码，处理用户真正逻辑 

### Netty零拷贝实现 ###

- Netty使用DirectMemory(堆外内存),直接进行socket读写,少了从用户缓冲区copy到网络发送区的操作。

### Netty的高性能体现在哪方面 ###

- 心跳：定时去除无效的连接, 使的客户端可以接收更多有效的连接
- 串行化无锁设计：Netty消息的处理尽量在一个线程完成，这样避免使用同步锁和多线程竞争。



## NIO知识梳理

### 1、什么是NIO ###

	NIO是java 1.5新出的一套IO接口，N意为Non-block非阻塞。
	IO是面向流的，NIO是面向缓冲区的。
- 读数据和写数据方式
	NIO是从通道进行数据读取的，先创建一个缓冲区，然后通道将数据填入缓冲区。
	NIO的核心组件有三个：Buffer, Channel, Selector
	
### 2、NIO之Buffer(缓冲区) ###

#### 2.1、Buffer介绍 ####

- Buffer用于和Channel交互，我们从Channel中读取数据到Buffer中，也可以将Buffer数据写到Channel。
- Buffer本质是一块内存区
- Buffer重要属性：capacity(容量)、limit(限制,当前缓冲区有效内容)、position(当前读写位置)

#### 2.2、Buffer常见方法 ####

- clear(), 清空缓冲区，为channel的读写操作做好准备,clear之后，limit将变为容量大小，position变为0
- flip(), 读写转换
- position(int newPosi), 设置新的位置

### 3、Channel ###

#### 3.1、Channel的重要实现 ####

- FileChannel
- DatagramChannel UDP通道
- SocketChannel TCP通道，主要方法有read,write
- ServerSocketChannel 可以监听TCP连接的通道

### 4、Selector（选择器） ###

#### 4.1、Selector介绍 ####

- selector一般也称为多路复用，用于检查一个或多个channel（通道）状态是否可读或可写,
- 使用Selector的好处，是使用一个线程，就可以管理多个通道，减少多线程切换带来的开销。

#### 4.2、Selector常用方法 ####

- Selector.open(); Selector的创建
- channel.configBlock(true), channel.register(Selector,SelectionKey.OP_READ); 将通道注册到选择器上。
- selector.wakeup()或close(), 停止选择 



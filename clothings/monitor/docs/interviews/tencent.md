
# Tencent Interview Record #

- 简述常用集合框架，HashMap的put过程，ConcurrentHashMap的put过程，怎么保存线程安全的，以及扩容过程
- 线程的几种实现方式, 几种锁的使用
- BIO/NIO/AIO的区别
- Spring域，Spring MVC的过程，Spring 事物
- 数据库索引与聚蔟索引，数据库优化
- JVM内存模型，垃圾回收算法
- redis高并发原理，有几种数据结构

## 简述常用集合框架，HashMap的put过程，ConcurrentHashMap的put过程，怎么保存线程安全的，以及扩容过程 关键字 ##

- HashMap的put过程
- ConcurrentHashMap怎么保证线程安全

### HashMap的put过程 ###

- put 过程：当hash值发生碰撞时，会把entry放在链表的头部，因为作者觉得后插入的被查的可能性更大
- 计算下标时，1.8不在是hash值取模了，而是&之类。
- 扩容时，生成两倍的数组，然后利用&计算新的下标，而不用去重新计算hash值。
- 扩容时机：当对象数达到容量*负载因子时进行扩容。

###  ConcurrentHashMap怎么保证线程安全 ###

	1.7时期
- ConcurrentHashMap将数据分别放到多个Segment，默认16个。每个段又包含了多个HashEntry列表数组。
- 对一个key需要经过三次hash操作，才能最终确定这个元素位置。
- 每一个Segment都拥有一个锁，当进行写操作时，只需要锁一个Segment，对其他的Segment不影响。

	1.8时期
- 大量的利用了CAS算法实现了无锁化修改操作。
- 先通过计算hash值，确定节点所在的位置，如果节点位置没有数据，就通过CAS机制放入值。
- 如果节点位置有值，就将节点上锁，锁粒度为链表的头节点。





# 01-Java性能调优概述

- 性能概述
	* 看懂程序的性能
	* 性能的参考指标
	* 木桶原理与性能瓶颈
- 性能调优的层次
	* 设计调优
	* 代码优化
	* JVM调优
	* 数据库调优	
	* 操作系统优化
	
## 	性能概述

### 看懂程序的性能

一般来说，程序的性能通过以下几个方面来说

- 执行速度：程序反应是否迅速，响应时间是否足够短。
- 内存分配：内存分配是否合理，是否过多的分配内存或存在内存泄露。
- 启动时间：从程序开始启动，到可以正常处理业务需要花费多长时间。
- 负载承受能力：当系统压力上升时（包括用户量、数据量），系统的执行速度、响应时间上升曲线是否平缓。

### 性能的参考指标

为了能够科学地进行性能分析，对性能指标进行定量的测评是非常重要的。以下是一些可以定量测评的性能指标。

- 执行时间：一段代码从开始运行到运行结束所需时间。
- CPU时间：函数或线程占用CPU时间。
- 内存分配：程序运行时占用内存空间。
- 磁盘吞吐量：描述I/O使用情况。
- 网络吞吐量：描述网络使用情况。
- 响应时间：系统对用户的响应。

### 木桶原理与性能瓶颈

木桶原理又称“短板理论”，其核心思想是：一只木桶能装多少水，不是取决最高那块木板，而是最短的。
根据木桶理论，系统最终性能取决于系统表现最差的组件，因此提升系统性能，就要找出系统表现最差的组件进行优化。
根据应用的特点，任何计算机资源都有可能成为系统瓶颈，具体资源如下：

## 性能调优的层次

为了提升系统的性能，开发人员可以从系统的各个角度和层次对系统进行优化。除了最常见的代码优化外，还有软件架构，JVM虚拟层，数据库以及操作系统层面优化。
下面我们先讲设计调优，也就是架构优化。

### 设计调优

- 一个良好的系统设计可以规避很多潜在的性能问题，因此尽可能的多花些时间在系统设计上。从某种程度上讲，设计优化直接决定了系统的整体品质。

### 代码优化

- 使用合理的数据结构等

### JVM调优

- java是运行在jvm虚拟机上的，对jvm优化也能一定程度上提升程序性能。
- jvm调优通常在软件开发后期：通常设置一些java堆大小，垃圾回收器的选择。

### 数据库调优

对数据库优化主要有3个方面：

- 在应用层对SQL语句进行优化：
- 对数据库结构优化：合理使用冗余字段。
- 对数据库软件配置进行优化：设置合理的缓存缓冲区之类的。

### 操作系统优化

	

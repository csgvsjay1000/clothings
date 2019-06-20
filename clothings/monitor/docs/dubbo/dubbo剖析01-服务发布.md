
# dubbo剖析01-服务发布

## 0. 服务发布的目的

服务提供者向注册中心注册服务，将服务实现类以接口的形式提供出去，以便服务消费者从注册中心查阅并调用服务。

## 1. 服务发布入口

### 1.1 Spring 配置及 ServiceBean 映射

服务发布方会在 Spring 配置文件中有如下配置：

```xml
	
	<!-- 服务提供方应用信息, 用于计算依赖关系 -->
	<dubbo:application name="demo-provider">

	<dubbo:register address="zookeeper://127.0.0.1:2181">
	<!-- 使用dubbo协议20880端口暴露服务 -->
	<dubbo:protocol name="dubbo" port="20880">

	<bean id="demoService" class="com.xxx.DemoService"></bean>

	<dubbo></dubbo>

```

## 2. 几个关键概念

### 2.1 Invoker

可执行对象的抽象，能根据方法的名称参数得到相应的结果。Invoker 可以分为三类：

- AbstractProxyInvoker: 本地执行类的Invoker, 实际通过Java反射的方式执行原始对象的方法。
- AbstractInvoker: 远程通信类的Invoker, 实际通过远程协议发起调用并接收响应。
- AbstractClusterInvoker: 多个远程通信类的Invoker聚合成clusterInvoker, 加入了集群容错和负载均衡机制。

### 2.2 ProxyFactory

服务接口代理抽象，用于生成一个接口的代理类。

- getInvoker()方法：针对server端，将服务端DemoServiceImpl包装成一个Invoker对象。
- getProxy(); 针对Client端，创建接口如

### 2.3 Exporter

维护Invoker的生命周期
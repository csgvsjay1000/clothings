
# 01-dubbo配置文件详解

所有配置项分为三大类：
- 服务发现：该配置主要用于服务的注册于发现，目的是让消费方找到提供方
- 服务治理：该配置项用于治理服务之间的关系，为开发和测试提供便利，或者提供服务动态治理，比如动态调整某个服务的权重。
- 性能调优：用于性能调优，不同的配置会对性能产生影响

## 服务发现

- service interface: 服务提供方暴露接口
- ref: 服务调用方

## 服务治理

dubbo:protocol

- stub: 默认FALSE, 若为true，则使用缺省代理类名
- mock: 服务接口调用失败实现类，接口名+Mock后缀，该Mock必须有一个无参构造函数，Mock只出现在非业务异常（超时、网络异常）情况才会调用，常用来熔断降级
- deprecate： 服务是否过时，若设为true，消费方引用时警告error错误日志
- accesslog：向log中输出访问日志，可以填写日志路径，访问日志直接输出到指定路径
- name: 协议名称，常见的传输协议为：RMI、Dubbo、Hessain、http, 只有dubbo是长连接，默认是dubbo
- threadpool: 线程池类型，fixed、cache
- threads: 服务线程池大小
- iothreads: io线程池大小
- accepts: 服务提供方最大可支持连接数
- payload: 请求响应包大小限制，单位字节，默认8M
- serialization: 序列化协议，dubbo序列化默认是hessian2
- transpoter: 协议客户端和服务端实现类型，dubbo协议的mina/netty等，可以拆分为server和client
- server：协议的服务端实现类型，dubbo协议的mina/netty等，http协议的jetty/servlet
- client: 协议客户端实现类型
- dispatcher: 协议消息的派发类型，比如dubbo的all、driect、messages、connection
- queues: 线程池队列大小，当线程池满时，排队等待执行队列的大小，建议不要设置。

## 服务调优

- delay：延迟注册服务时间单位毫秒，设为-1时，表示Spring容器初始化完成时暴露服务，加快服务启动时间
- timeout：服务调用超时时间，默认1000ms
- retries: 重试次数，默认2次，不需要重试设为0
- connections：对每个提供者的最大连接数，默认100
- loadbalance: 负载均衡策略：随机、轮询、最少活跃，默认随机
- async: 是否异步，忽略返回值，不阻塞执行线程
- weight：服务权重，结合负载均衡使用，在服务升级时，要想少量请求到新版本可以减小权重，减小影响范围，然后慢慢增加服务权重
- executes: 服务提供者每个服务每个方法最大可并行执行请求数，常用来限流，默认0没有限制。是服务方的配置项。
- actives: 消费者配置，每服务消费者每服务方法最大并发调用数。
- cluster: 集群方式，有故障转移、快速失败、失败安全、失败自动恢复等
- filter: 服务提供方拦截器


 
	
	

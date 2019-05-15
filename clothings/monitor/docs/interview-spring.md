
# Spring知识梳理 #

- Spring是什么？
- Spring Aop的理解
- 解释Spring支持的几种bean的作用域。
- Spring事务的实现方式和实现原理
- Spring的事物传播行为

## Spring是什么？ ##

	Spring 是一个轻量级IOC和AOP框架，常见配置形式：基于XML配置、注解配置。主要由以下几个模块组成：
- Spring Core: 核心类库，提供IOC服务。
- Spring Context: 提供框架式Bean访问方式，以及企业级功能（JNDI,定时任务）
- Spring AOP: Aop服务
- Spring Dao：对JDBC的抽象，简化了数据库访问
- Spring ORM：对现有ORM框架提供支持
- Spring Web: 提供了基本面向web的服务，如文件上传操作
- Spring MVC：提供web实现

##	Spring Aop的理解  ##

	Spring AOP是一个面向切面，是面向对象的一种补充。可用于权限认证，事物日志等实现。AOP的实现关键在于代理模式，代理分为静态代理和动态代理，静态代理的代表是AspectJ，动态代理已Spring AOP为代表。
- AspectJ是静态代理的增强，就是AOP代理框架会在代码编译阶段生成AOP代理类，因此也称编译时增强。
- Spring AOP动态代理，就是AOP框架不会去修改字节码，而是在每次运行时在内存中生成一个临时方法。

	Spring AOP动态代理主要有两种方式：JDK动态代理和CGLIB动态代理
- JDK动态代理只提供接口的代理，不提供类的代理，通过反射调用目标类的方法，动态的绑定横切和业务。
- 如果代理目标类没有实现接口，Spring会选择使用CGLIB来实现动态代理目标类。CGLIB通过生成目标类的子类，增加横切方法来实现动态代理，如果目标类为final，则无法使用CGLIB。
	
## 解释Spring支持的几种bean的作用域 ##

- singleton： 单列（默认），每个容器只有一个Bean实例，非线程安全。
- prototype：为每一个Bean请求提供一个实例

## Spring事务的实现方式和实现原理 ##

	Spring事物的本质是数据库对事物的支持，而数据库事物的提交和回滚是通过binlog和redolog实现的。Spring事物种类支持声明式事物和编程式事物。
- 编程式事物管理使用TransactionTemplate。
- 声明式事物管理建立在AOP之上。其本质是通过AOP功能，对其方法前后进行拦截，将事物处理的功能编织到拦截方法中，也就是在目标方法开始之前加入一个事物，在执行完目标方法后根据执行情况提交或回滚事物。不足之处是事物粒度是方法级别，无法向声明式事物一样做到代码块级别。

## Spring的事物传播行为 ##

- PROPAGATION_REQUIRED: 默认，如果没有事物就创建事物，有就加入当前事物。

- Spring事物隔离级别：MYSQL默认是可重复读。


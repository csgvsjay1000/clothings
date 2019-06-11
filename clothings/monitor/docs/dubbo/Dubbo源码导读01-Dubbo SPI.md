
# Dubbo源码导读01-Dubbo SPI

- 1. 简介

## 1. 简介

SPI 全称为 Service Provider Interface. 是一种服务发现机制。SPI本质是将接口实现类的全限定名配置在文件中，并由服务读取配置文件，加载实现类，这样在运行时可以动态为接口替换实现类。

## 2. SPI 示例

### 2.1 Java SPI 示例

首先我们定义一个接口Rebot.


	public interface Rebot { 
		void sayHello();
	}

接下来定义两个实现类，OptimusPrime 和 Bumblebee

	public class OptimusPrime implements Rebot {

		public void sayHello(){
			System.out.println("Hello, I am OptimusPrime.");
		}
	}
	public class Bumblebee implements Rebot {

		public void sayHello(){
			System.out.println("Hello, I am Bumblebee.");
		}
	}
 
接下来META-INF/services文件夹下创建一个文件，名称为Rebot接口的全限定名：ivg.cn.spi.Rebot，文件内容如下：

	ivg.cn.spi.OptimusPrime
	ivg.cn.spi.Bumblebee

做好准备工作，接下来编写测试代码

	public class JavaSPITest {

		@Test
		public void sayHello(){
			ServiceLoader<Rebot> serviceLoader = ServiceLoader.load(Rebot.class);
			serviceLoader.forEach(Robot::sayHello);
		}
	}

最后看看测试结果：

	Hello, I am OptimusPrime.
	Hello, I am Bumblebee.
	
从测试结果看出，两个实现类已被加载，并输出。

### 2.2 Dubbo SPI 示例
	
Dubbo SPI并未使用Java SPI机制，而是重新实现了一套更强的SPI机制。Dubbo SPI被封装在 ExtensionLoader 类中，通过该类我们可以加载指定的实现类，Dubbo SPI配置文件需放在 META-INF/dubbo路径下，文件内容为

	optimusPrime = ivg.cn.spi.OptimusPrime
	bumblebee = ivg.cn.spi.Bumblebee

与Java SPI 不同的是，Dubbo SPI是通过键值对配置的，这样我们可以按需指定实现类，另外在测试时，还需在 Rebot接口上加 @SPI 注解。

	public class DubboSPITest {

		@Test
		public void sayHello(){
			ExtensionLoader<Rebot> extensionLoader = ExtensionLoader.getExtensionLoader(Rebot.class);
			Rebot optimusPrime = extensionLoader.getExtension("optimusPrime");
			optimusPrime.sayHello();
		}
	}
package ivg.cn.clo.factory.db;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

@SpringBootApplication
@DubboComponentScan(basePackages = "ivg.cn.clo.factory.db")
public class FactoryDBStartup {

	public static void main(String[] args) {
//		Main.main(args);
		SpringApplication.run(FactoryDBStartup.class, args);
	}
}

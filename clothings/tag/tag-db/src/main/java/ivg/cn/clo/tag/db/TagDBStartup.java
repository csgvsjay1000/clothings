package ivg.cn.clo.tag.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

@SpringBootApplication
@DubboComponentScan(basePackages = "ivg.cn.clo.tag.db")
public class TagDBStartup {

	public static void main(String[] args) {
		SpringApplication.run(TagDBStartup.class, args);
	}
	
}

package ivg.cn.clo.tag.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

@SpringBootApplication
@DubboComponentScan(basePackages = "ivg.cn.clo.tag.service")
public class TagServiceStartup {

	public static void main(String[] args) {
		SpringApplication.run(TagServiceStartup.class, args);
	}
	
}

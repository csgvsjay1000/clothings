package ivg.cn.clo.factory.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@DubboComponentScan(basePackages = "ivg.cn.clo.factory.service")
public class FactoryServiceStartup 
{
	
	static Logger logger = LoggerFactory.getLogger(FactoryServiceStartup.class);
	
    public static void main( String[] args )
    {
        SpringApplication.run(FactoryServiceStartup.class, args);
//    	Main.main(args);
//        logger.info("FactoryServiceStartup success.");
    }
}

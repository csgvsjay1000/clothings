package ivg.cn.clo.tag.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;


@Configuration
public class DubboConfiguration {

	@Value(value = "${dubbo.port}")
	private int port;
	
	@Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("tag-service");
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://192.168.5.131:10100");
        registryConfig.setClient("curator");
        registryConfig.setProtocol("zookeeper");
        return registryConfig;
    }
    
    @Bean
    public ProtocolConfig protocolConfig() {
    	ProtocolConfig protocolConfig = new ProtocolConfig();
    	
    	protocolConfig.setPort(port);
    	
    	return protocolConfig;
	}
}

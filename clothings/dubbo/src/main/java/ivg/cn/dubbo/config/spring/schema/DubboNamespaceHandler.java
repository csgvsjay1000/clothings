package ivg.cn.dubbo.config.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import ivg.cn.dubbo.config.spring.ServiceBean;

public class DubboNamespaceHandler extends NamespaceHandlerSupport{

	@Override
	public void init() {
		// TODO Auto-generated method stub
		registerBeanDefinitionParser("service", new DubboBeanDefinitionParser(ServiceBean.class, true));
	}

}

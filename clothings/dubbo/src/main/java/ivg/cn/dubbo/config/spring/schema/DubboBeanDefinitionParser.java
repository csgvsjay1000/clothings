package ivg.cn.dubbo.config.spring.schema;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import ivg.cn.dubbo.config.spring.ServiceBean;

public class DubboBeanDefinitionParser implements BeanDefinitionParser{

	private final Class<?> beanClass;
	
	private final boolean required;
	
	public DubboBeanDefinitionParser(Class<?> beanClass, boolean required) {
		this.beanClass = beanClass;
		this.required = required;
	}
	
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		
		if (ServiceBean.class.equals(beanClass)) {
			String className = element.getAttribute("class");
		}
		
		return null;
	}

}

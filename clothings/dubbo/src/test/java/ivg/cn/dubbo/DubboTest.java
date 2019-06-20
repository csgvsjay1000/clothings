package ivg.cn.dubbo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboTest {

	
	@Test
	public void testSpring() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-database.xml");
		
	}
	
}

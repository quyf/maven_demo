package cn.quyf.demo.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.quyf.demo.spring.services.ClientService;

public class SpringTest {

	public static void main(String[] args) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext( new String[]{"spring.xml"});
		ClientService service = (ClientService) context.getBean( ClientService.class);
		service.sayHello();
	}
}

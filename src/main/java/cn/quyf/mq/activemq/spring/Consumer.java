//package cn.quyf.mq.activemq.spring;
//
//import javax.annotation.Resource;
//import javax.jms.Destination;
//import javax.jms.JMSException;
//import javax.jms.TextMessage;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(value = "/mq/activemq/spring/spring-core.xml")
//public class Consumer {
//
//	@Resource(name = "JmsTemplate")
//	private JmsTemplate jmsTemplate;
//
//	@Resource(name = "queueDestination")
//	private Destination destination;
//
//	@Test
//	public void test() {
//		System.out.println(jmsTemplate);
//		TextMessage message = (TextMessage) jmsTemplate.receive(destination);
//		try {
//			System.out.println("接受消息>>>>>>>>>>>" + message.getText());
//		} catch (JMSException e) {
//			e.printStackTrace();
//		}
//	}
//}

package cn.quyf.mq.activemq.spring;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:/mq/activemq/spring/spring-core.xml")

public class Sender {

	@Resource(name = "JmsTemplate")
	private JmsTemplate jmsTemplate;

	@Test
	public void test() {
		System.out.println(jmsTemplate);
		System.out.println("向" + jmsTemplate.getDefaultDestination().toString() + "发送消息");
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("发送消息");
			}
		});

	}
}
package cn.quyf.mq.activemq.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class DemoMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("接受到消息>>>>>>>>"+((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}

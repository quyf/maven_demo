package cn.quyf.mq.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息发送者
 * @author quyf
 *http://blog.csdn.net/jiuqiyuliang/article/details/48608237
 */
public class Producer {

	private static final String userName = ActiveMQConnection.DEFAULT_USER;
	private static final String pwd = ActiveMQConnection.DEFAULT_PASSWORD;
	//默认连接地址
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = 
				new ActiveMQConnectionFactory(userName, pwd, BROKEURL);
		Connection conn = factory.createConnection();
		//conn.start();
		Session session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Destination dest = session.createQueue("quene_mq");
		MessageProducer producer = session.createProducer(dest);
		
		for(int i=0;i<50;i++){
			TextMessage msg = session.createTextMessage("active mq msg:"+i);
			producer.send( msg );
		}
		session.commit();
		//Topic topic = session.createTopic("topic_mq");
		
	}
}

package cn.quyf.mq.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {

	private static final String userName = ActiveMQConnection.DEFAULT_USER;
	private static final String pwd = ActiveMQConnection.DEFAULT_PASSWORD;
	//默认连接地址
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	
	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ActiveMQConnectionFactory(userName, pwd, BROKEURL);
		Connection conn = factory.createConnection();
		conn.start();
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination dest = session.createQueue("quene_mq");
		MessageConsumer consumer = session.createConsumer(dest);
		while( true ){
			TextMessage msg = (TextMessage)consumer.receive();
			if( msg!=null ){
				System.out.println( " consumer receive,"+msg.getText());
			}else{
				break;
			}
			
		}
		session.commit();
	}
}

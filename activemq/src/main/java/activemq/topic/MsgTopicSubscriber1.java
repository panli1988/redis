package activemq.topic;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class MsgTopicSubscriber1 {

	//ActivMQ地址
    private static final String ACTIVEMQ_URL = "tcp://192.168.29.129:61616";
    //主题名称
    private static final String TOPIC_NAME = "test-topic";
    
    @Test
    public void subscribe() throws JMSException {
    	
    	ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
    	Connection connection = connectionFactory.createConnection();
    	connection.start();
    	
    	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    	Topic topic = session.createTopic(TOPIC_NAME);
    	MessageConsumer consumer = session.createConsumer(topic);
    	consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				TextMessage textMesage = (TextMessage) message;
				try {
					System.out.println("订阅者1订阅消息："+textMesage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
    	try {
			Thread.sleep(30*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	connection.close();
    }
}

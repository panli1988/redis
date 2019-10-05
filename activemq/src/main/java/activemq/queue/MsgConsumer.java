package activemq.queue;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class MsgConsumer {

	//ActivMQ地址
    private static final String ACTIVEMQ_URL = "tcp://192.168.29.129:61616";
    //队列名称
    private static final String QUEUE_NAME = "test-queue";
    
    @Test
    public void receive() throws JMSException {
    	ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
    	Connection connection = connectionFactory.createConnection();
    	connection.start();
    	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    	Queue queue = session.createQueue(QUEUE_NAME);
    	MessageConsumer consumer = session.createConsumer(queue);
    	consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println("接收消息："+textMessage.getText());
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

package activemq.topic;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class MsgTopicPublisher {
	
	//ActivMQ地址
    private static final String ACTIVEMQ_URL = "tcp://192.168.29.129:61616";
    //主题名称
    private static final String TOPIC_NAME = "test-topic";
	

    @Test
    public void publish() throws JMSException {
    	ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
    	Connection connection = connectionFactory.createConnection();
    	connection.start();
    	
    	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    	Destination destination = session.createTopic(TOPIC_NAME);
		MessageProducer producer = session.createProducer(destination);
		TextMessage message = session.createTextMessage("topic 测试=============");
		producer.send(message);

		connection.close();
    }
}

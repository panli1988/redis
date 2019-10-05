package activemq.queue;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class MsgProducer {

	//ActivMQ地址
    private static final String ACTIVEMQ_URL = "tcp://192.168.29.129:61616";
    //队列名称
    private static final String QUEUE_NAME = "test-queue";
    
    @Test
    public void sendMessage() throws JMSException {
    	//连接工厂
    	ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
    	//创建连接
    	Connection connection = activeMQConnectionFactory.createConnection();
    	//打开连接
    	connection.start();
    	//创建会话
    	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    	//创建队列目标
    	Destination destination = session.createQueue(QUEUE_NAME);
    	//创建一个生产者
    	MessageProducer producer = session.createProducer(destination);
    	Message message = session.createTextMessage("test ===================");
		producer.send(message);
    	//创建模拟100个消息
    	connection.close();
    	
    }

}

package spring.beans;

import javax.jms.JMSException;
import javax.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;
import spring.annotations.MyReceiverWithConnectionFactory;
import spring.interfaces.IReceiver;

/**
 *
 * @author д06ри, dobri7@gmail.com
 */
@Component
public class ReceiverBean implements IReceiver {

    private final JmsOperations jmsOperations;

    private String queueName;

    @Autowired
    @MyReceiverWithConnectionFactory
    public ReceiverBean(JmsOperations jmsOperations) {
        this.jmsOperations = jmsOperations;
    }

    @Override
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    @Override
    public Message receive() throws JMSException {
        return jmsOperations.receive();
    }

}

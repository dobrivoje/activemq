package spring.interfaces;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 *
 * @author ?06??, dobri7@gmail.com
 */
public interface IReceiver extends IQueue {

    Message receive() throws JMSException;

}

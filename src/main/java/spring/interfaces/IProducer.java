package spring.interfaces;

import javax.jms.JMSException;

/**
 *
 * @author ?06??, dobri7@gmail.com
 */
public interface IProducer extends IQueue {

    void submit(String message) throws JMSException;

}

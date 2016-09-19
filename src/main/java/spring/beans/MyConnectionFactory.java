package spring.beans;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author д06ри, dobri7@gmail.com
 */
@Component
public class MyConnectionFactory extends ActiveMQConnectionFactory {

    public MyConnectionFactory(String brokerURL) {
        super(brokerURL);
    }

}

package spring.config;

import javax.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import spring.beans.ProducerBean2;
import spring.beans.ReceiverBean;
import spring.interfaces.IProducer;
import spring.interfaces.IReceiver;

/**
 *
 * @author д06ри, dobri7@gmail.com
 */
@Configuration
@ComponentScan
public class AppConfiguration {

    @Bean
    public ActiveMQConnectionFactory getACF() {
        return new ActiveMQConnectionFactory("tcp://88.198.46.245:61617");
    }

    @Bean
    public IProducer getProducer(JmsOperations jmsOperations) {
        return new ProducerBean2(jmsOperations);
    }
    
    @Bean
    public IReceiver getReceiver(JmsOperations jmsOperations) {
        return new ReceiverBean(jmsOperations);
    }
    

    @Bean
    public JmsTemplate getJmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jt = new JmsTemplate(connectionFactory);
        jt.setDefaultDestinationName("Dobri");
        
        return jt;
    }

}

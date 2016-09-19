package spring.beans;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;
import spring.interfaces.IProducer;

/**
 *
 * @author д06ри, dobri7@gmail.com
 */
@Component
public class ProducerBean1 implements IProducer {

    private final JmsOperations jmsOperations;

    private String queueName;

    @Autowired
    public ProducerBean1(JmsOperations jmsOperations) {
        this.jmsOperations = jmsOperations;
    }

    @Override
    public void submit(final String message) {
        ExecutorService E = Executors.newCachedThreadPool();

        E.execute(() -> {
            try {
                final String m = "ID=" + String.valueOf((int) (100 * Math.random())) + " " + message;
                jmsOperations.send(queueName, (Session sn) -> sn.createTextMessage(m));

                // TimeUnit.MILLISECONDS.sleep(1);
            } catch (Exception e) {
            }
        });

//        try {
//            E.awaitTermination(10, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException ex) {
//        }

        E.shutdown();
    }

    @Override
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

}

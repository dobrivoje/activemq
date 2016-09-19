package activemq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.AppConfiguration;
import spring.interfaces.IProducer;
import spring.interfaces.IReceiver;

/**
 *
 * @author д06ри, dobri7@gmail.com
 */
public class NewMain {

    private static final ApplicationContext CTX = new AnnotationConfigApplicationContext(AppConfiguration.class);
    // private static final ApplicationContext CTX2 = new ClassPathXmlApplicationContext("activemqCBSTest.xml");

    public static void main(String[] args) {

        IProducer producer = CTX.getBean(IProducer.class);
        IReceiver receiver = CTX.getBean(IReceiver.class);

        producer.setQueueName("Dobri");
        receiver.setQueueName("Dobri");

        int msgLen = 50;

        ExecutorService PES = Executors.newCachedThreadPool();
        ExecutorService RES = Executors.newCachedThreadPool();

        for (int i = 0; i < msgLen; i++) {
            PES.execute(() -> {
                try {
                    producer.submit("poruka.");

                    TimeUnit.MILLISECONDS.sleep((int) (10 * Math.random()));
                } catch (JMSException ex) {
                } catch (InterruptedException ex) {
                    Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        try {
            PES.awaitTermination(10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
        }

        PES.shutdown();

//        RES.execute(() -> {
//            int brojac = 0;
//
//            while (true) {
//                try {
//                    receiver.receive();
//                    Logger.getLogger("Risiver").log(Level.INFO, "primljena poruka->{0}\n", (++brojac));
//                    //TimeUnit.MILLISECONDS.sleep(1);
//                } catch (JMSException ex) {
//                }
//            }
//        });
//
//        RES.shutdown();
    }

}

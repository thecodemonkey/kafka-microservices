package hellokafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@EnableScheduling
public class Producer {

    @Autowired
    public final KafkaTemplate<String, String> kafka = null;

    @Scheduled(fixedRate = 1000)
    public void send() {
        String message = "hello kafka  " + LocalDateTime.now().toString();
        System.out.println("SEND    MESSAGE : " + message );

        this.kafka.send("hello-topic", message);
    }
}

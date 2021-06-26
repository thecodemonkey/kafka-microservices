package hellokafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class Producer {

    @Autowired
    public final KafkaTemplate<String, String> kafka = null;

    void send(String message) {
        System.out.println("PUBLISH message: $message");
        this.kafka.send("hello-topic-ut", message);
    }
}

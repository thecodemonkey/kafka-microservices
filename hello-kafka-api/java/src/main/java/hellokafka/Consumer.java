package hellokafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics= { "hello-topic" }, groupId = "kafka_kotlin_id")
    public void consume(String message) {
        System.out.println("RECEIVE MESSAGE : " + message);
    }
}

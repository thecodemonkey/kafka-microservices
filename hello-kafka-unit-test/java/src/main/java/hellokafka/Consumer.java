package hellokafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
public class Consumer {

    List<String> data = new ArrayList<>();
    CountDownLatch latch = new CountDownLatch(2);

    @KafkaListener(topics= {"hello-topic-ut"}, groupId = "kafka_java_id")
    void consume(String message) {
        System.out.println("RECEIVE message: $message");
        data.add(message);
        latch.countDown();
    }
}

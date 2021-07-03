package hellokafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Consumer {

    @Bean
    public NewTopic outputTopic() {
        return TopicBuilder.name("output-topic").build();
    }

    @KafkaListener(topics= { "output-topic" }, groupId = "kafka_java_id")
    public void consume(String message) {
        System.out.println("receive    MESSAGE : " + message + " at " + LocalDateTime.now());
    }
}

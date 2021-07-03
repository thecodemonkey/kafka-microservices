package hellostreams

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.config.TopicBuilder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class Consumer() {

    @Bean
    fun outputTopic(): NewTopic {
        return TopicBuilder.name("output-topic").build()
    }

    @KafkaListener(topics= ["output-topic"], groupId = "kafka_kotlin_id")
    fun consume(message:String) : Unit {
        println("receive    MESSAGE : $message at ${LocalDateTime.now()}");
    }

}

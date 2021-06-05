package hellostreams

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class Consumer() {

    @KafkaListener(topics= ["output-topic"], groupId = "kafka_kotlin_id")
    fun consume(message:String) : Unit {
        println("receive    MESSAGE : $message at ${LocalDateTime.now()}");
    }

}

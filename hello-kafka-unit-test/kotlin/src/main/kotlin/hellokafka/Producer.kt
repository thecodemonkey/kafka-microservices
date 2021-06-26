package hellokafka

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class Producer(val kafka: KafkaTemplate<String, String>) {

    fun send(message: String) {
        println("PUBLISH message: $message")
        this.kafka.send("hello-topic-ut", message)
    }
}

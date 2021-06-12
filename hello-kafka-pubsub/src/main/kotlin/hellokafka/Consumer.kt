package hellokafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class Consumer {

    @KafkaListener(topics= ["hello-topic"], groupId = "kafka_kotlin_id")
    fun consume(message: String) {
        println("RECEIVE MESSAGE : $message")
    }
}

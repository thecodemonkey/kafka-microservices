package hellokafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.util.concurrent.CountDownLatch

@Service
class Consumer {
    val data: MutableList<String> = ArrayList()
    var latch = CountDownLatch(2)

    @KafkaListener(topics= ["hello-topic-ut"], groupId = "kafka_kotlin_id")
    fun consume(message: String) {
        println("RECEIVE message: $message")
        data.add(message)
        latch.countDown()
    }
}

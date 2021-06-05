package hellokafka


import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
@EnableScheduling
class Producer(val kafka: KafkaTemplate<String, String>) {

    @Scheduled(fixedRate = 1000)
    fun send() {
        val message: String = "hello kafka  " + LocalDateTime.now().toString()
        println("SEND    MESSAGE : $message")

        this.kafka.send("hello-topic", message)
    }
}

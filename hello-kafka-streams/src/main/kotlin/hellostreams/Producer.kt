package hellostreams


import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service


@Service
@EnableScheduling
class Producer(val kafka: KafkaTemplate<String, String>) {

    @Scheduled(fixedRate = 1000)
    fun send() {
        val message = "hello kafka streams";
        println("publish    MESSAGE : $message")
        this.kafka.send("input-topic", message)
    }
}

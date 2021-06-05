package kafka.es.command


import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import kotlin.random.Random.Default.nextInt as rint


@Service
@EnableScheduling
class Producer(val kafka: KafkaTemplate<String, OrderEvent>) {

    @Scheduled(fixedRate = 1000)
    fun send() {

        // simulate different orders
        val event = OrderEvent(rint(1, 4).toString(), "1");

        // create random updates
        when(rint(0, 3)) {
            0 -> event.amount = rint(1, 99)                // update amount with random value (from 1 to 99)
            1 -> event.price = rint(1, 99)                 // update price with random value  (from 1 to 99)
            2 -> event.productId = rint(1,99).toString()   // update product with random value (from 1 to 99)
        }

        println("produce event: $event");
        this.kafka.send("es-orders", event)
    }
}

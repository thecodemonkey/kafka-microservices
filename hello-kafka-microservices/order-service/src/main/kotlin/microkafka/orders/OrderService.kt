package microkafka.orders

import microkafka.orders.model.Order
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(val orderRepository: OrderRepository,
                   val kafkaTemplate: KafkaTemplate<String, Order>) {

    fun createOrder(userId: String, productId: String, amount: Int) {

        val order = Order().also {
            it.id = UUID.randomUUID().toString()
            it.status = "NEW"
            it.userId = userId
            it.productId = productId
            it.amount = amount
        }

        // In this example, NO CQRS and NO event sourcing are implemented,
        // the order is immediately persisted in the local DB when it is created,
        // instead of receiving the corresponding event !
        orderRepository.save(order);

        kafkaTemplate.send("orders", "create", order)
    }
}

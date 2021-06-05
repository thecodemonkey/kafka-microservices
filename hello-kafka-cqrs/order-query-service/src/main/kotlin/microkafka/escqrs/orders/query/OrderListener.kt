package microkafka.escqrs.orders.query

import microkafka.escqrs.orders.query.model.OrderEntity
import microkafka.escqrs.orders.query.model.OrderEvent
import microkafka.escqrs.orders.query.model.fromEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class OrderListener(val orderRepository: OrderRepository) {

    // subscribes to the kafka topic "orders"
    // this function is called for every event(record) of specified topic that has not yet been processed
    @KafkaListener(topics= ["cqrs-orders"])
    fun consume(event: OrderEvent) : Unit {
        println(" order received from orders topic : $event");

        val order = event.fromEvent();

        // persist the received order
        orderRepository.save(order)
    }
}

package kafka.es.query

import kafka.es.query.model.Order
import kafka.es.query.model.OrderEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class OrderListener(val repository: OrderRepository) {

    @KafkaListener(topics= ["es-orders"])
    fun consume(event: OrderEvent) {

        // 1. load entity from local storage by id
        var order = repository.findById(event.id!!)

        // 2. create new order entity if not exists within local storage
        if (order == null)
            order = Order(event.id!!)

        // 3. partially update the entity
        order.apply {
            if (event.userId != null) this?.userId = event.userId
            if (event.productId != null) this?.productId = event.productId
            if (event.amount != null) this?.amount =  event.amount
            if (event.price != null) this?.price = event.price
        }

        // 4. updates local storage
        repository.save(order!!);

        println("RECEIVE MESSAGE : $order")
    }
}

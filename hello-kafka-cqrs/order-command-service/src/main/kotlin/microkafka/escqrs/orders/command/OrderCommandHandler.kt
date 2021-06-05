package microkafka.escqrs.orders.command

import microkafka.escqrs.orders.command.model.*
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderCommandHandler(val kafkaTemplate: KafkaTemplate<String, OrderEvent>) {

    fun handleCreate(command: OrderCreateCommand) {
        val event = OrderCreatedEvent(
            id = UUID.randomUUID().toString()
        ).apply {
            userId = command.userId
            productId = command.productId
            amount = command.amount
        }

        kafkaTemplate.send("cqrs-orders", "OrderCreated", event)
    }

    fun handleUpdate(command: OrderAmountUpdateCommand) {
        val event = OrderUpdatedEvent(
            id = UUID.randomUUID().toString()
        ).apply {
            orderId = command.orderId
            amount = command.amount
        }

        kafkaTemplate.send("cqrs-orders", "AmountUpdated", event)
    }
}

package microkafka.escqrs.orders.query.model

abstract class OrderEvent (
    open val id: String? = null,

    open var userId: String? = null,
    open var productId: String? = null,
    open var amount: Int? = null
)

data class OrderCreatedEvent(override val id: String?) : OrderEvent(id) { }
data class OrderUpdatedEvent(override val id: String?) : OrderEvent(id) { }

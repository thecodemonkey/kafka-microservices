package microkafka.escqrs.orders.query.model

data class OrderDto(
    var id: String? = null,
    var status: String? = null,

    var userId: String? = null,
    var productId: String? = null,
    var amount: Int? = null
)

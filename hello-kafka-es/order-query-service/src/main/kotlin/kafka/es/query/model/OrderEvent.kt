package kafka.es.query.model

data class OrderEvent (
    var id: String? = null,

    var userId: String? = null,
    var productId: String? = null,
    var amount: Int? = null,
    var price: Int? = null
)

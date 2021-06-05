package kafka.es.query.model

data class Order(
    val id: String,
    var userId: String? = null,
    var productId: String? = null,
    var amount: Int? = null,
    var price: Int? = null
)

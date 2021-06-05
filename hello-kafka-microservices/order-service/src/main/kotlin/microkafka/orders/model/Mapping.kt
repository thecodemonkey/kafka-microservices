package microkafka.orders.model

fun List<Order?>.toDTO() = this.map { it?.toDTO() }

fun Order.toDTO() = OrderDto(
    this.id,
    this.status,

    this.userId,
    this.productId,
    this.amount
)

fun OrderDto.fromDTO(): Order {
    val self = this

    return Order().apply {
        id = self.id
        status = self.status

        userId = self.userId
        productId = self.productId
        amount = self.amount
    }
}

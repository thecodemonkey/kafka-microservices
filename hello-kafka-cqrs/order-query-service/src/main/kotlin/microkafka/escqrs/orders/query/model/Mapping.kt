package microkafka.escqrs.orders.query.model

fun List<OrderEntity?>.toDTO() = this.map { it?.toDTO() }

fun OrderEntity.toDTO() = OrderDto(
    this.id,
    this.status,

    this.userId,
    this.productId,
    this.amount
)

fun OrderDto.fromDTO(): OrderEntity {
    val self = this

    return OrderEntity().apply {
        id = self.id
        status = self.status

        userId = self.userId
        productId = self.productId
        amount = self.amount
    }
}

fun OrderEvent.fromEvent(): OrderEntity {
    val self = this

    return OrderEntity().apply {
        id = self.id

        userId = self.userId
        productId = self.productId
        amount = self.amount
    }
}

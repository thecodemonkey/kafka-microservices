package microkafka.escqrs.orders.command.model

data class OrderCreateCommand(val userId: String, val productId: String, val amount: Int)
data class OrderAmountUpdateCommand(val orderId: String, val amount: Int)

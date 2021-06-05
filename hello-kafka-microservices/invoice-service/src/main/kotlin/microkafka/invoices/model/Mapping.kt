package microkafka.invoices.model

fun List<Invoice?>.toDTO() = this.map { it?.toDTO() }

fun Invoice.toDTO() = InvoicesDto(
    this.id,
    this.userId,
    this.orderId,
    this.amount
)

fun InvoicesDto.fromDTO(): Invoice {
    val self = this

    return Invoice().apply {
        id = self.id

        userId = self.userId
        orderId = self.orderId
        amount = self.amount
    }
}

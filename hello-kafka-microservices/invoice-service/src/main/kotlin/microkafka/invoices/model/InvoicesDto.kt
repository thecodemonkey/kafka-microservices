package microkafka.invoices.model

import java.math.BigDecimal

data class InvoicesDto(
    var id: String? = null,
    var userId: String? = null,
    var orderId: String? = null,
    var amount: Double? = null
)

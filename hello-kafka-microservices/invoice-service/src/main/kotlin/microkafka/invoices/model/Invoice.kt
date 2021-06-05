package microkafka.invoices.model

import javax.persistence.*

@Entity
@Table(name = "invoices")
class Invoice {

    @Id
    var id: String? = null

    var userId: String? = null
    var orderId: String? = null

    var amount: Double? = null
}

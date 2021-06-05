package microkafka.orders.model

import javax.persistence.*

@Entity
@Table(name = "orders")
class Order {

    @Id
    var id: String? = null
    var status: String? = null

    var userId: String? = null
    var productId: String? = null
    var amount: Int? = null
}

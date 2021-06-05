package microkafka.escqrs.orders.query.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "orders")
class OrderEntity {

    @Id
    var id: String? = null
    var status: String? = null

    var userId: String? = null
    var productId: String? = null
    var amount: Int? = null
}

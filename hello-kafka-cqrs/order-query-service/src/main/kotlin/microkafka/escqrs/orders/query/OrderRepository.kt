package microkafka.escqrs.orders.query

import microkafka.escqrs.orders.query.model.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<OrderEntity?, String?> {

}

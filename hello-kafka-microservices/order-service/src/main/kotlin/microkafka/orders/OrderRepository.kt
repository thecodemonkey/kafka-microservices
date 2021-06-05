package microkafka.orders

import microkafka.orders.model.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order?, String?> {

}

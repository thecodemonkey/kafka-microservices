package kafka.es.query

import kafka.es.query.model.Order
import org.springframework.stereotype.Component


@Component
class OrderRepository {
    private val records = HashMap<String, Order>()

    fun findById(id: String) = records[id]
    fun findAll() = records.values

    fun save(order: Order) = records.put(order.id, order)

    fun exists(id: String) = records.containsKey(id)
}

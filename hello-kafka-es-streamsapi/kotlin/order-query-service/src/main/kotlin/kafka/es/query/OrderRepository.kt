package kafka.es.query

import kafka.es.query.model.Order
import kafka.es.query.model.OrderEvent
import kafka.es.query.model.toOrder
import org.apache.kafka.streams.state.QueryableStoreTypes
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore
import org.springframework.kafka.config.StreamsBuilderFactoryBean
import org.springframework.stereotype.Component

@Component
class OrderRepository(val streamsBuilderFactoryBean: StreamsBuilderFactoryBean) {

    // create store instance to access the current state of the orders
    fun store(): ReadOnlyKeyValueStore<String, OrderEvent> =
        streamsBuilderFactoryBean.kafkaStreams
            .store("es-orders-store", QueryableStoreTypes.keyValueStore())

    fun findAll() : List<Order> {
        return store().all()
            .iterator()
            .asSequence()
            .toMutableList()
            .map { i -> i.value.toOrder() }
    }

    fun findById(id: String): Order {
        return store().get(id).toOrder()
    }
}

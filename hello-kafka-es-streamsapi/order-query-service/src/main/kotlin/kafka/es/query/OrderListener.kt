package kafka.es.query

import kafka.es.query.model.OrderEvent
import kafka.es.query.model.merge
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.EnableKafkaStreams
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer
import org.springframework.stereotype.Service


@Service
@EnableKafkaStreams
class OrderListener() {

    @Bean
    fun getSerde(): Serde<OrderEvent> {
        // create serde to serialize and deserialize OrderEvent
        val orderSer: JsonSerializer<OrderEvent> = JsonSerializer()
        // param false disables exact type mapping while deserializing
        val orderDes  = JsonDeserializer(OrderEvent::class.java, false)
        return Serdes.serdeFrom(orderSer, orderDes)
    }

    @Autowired
    fun eventProcessor(builder: StreamsBuilder, serde: Serde<OrderEvent>) {

    builder.stream("es-orders-streams",  Consumed.with(Serdes.String(), serde)) // subscribe to stream with serde
           .peek { key, value -> println("process item: $key - $value")}              // just debug output
           .groupByKey(Grouped.with(Serdes.String(), serde))                          // groupByKey with serde!
           .reduce(
                { order, order2 ->                                                   // current state orderEvent and a new one
                    order.merge(order2)                                              // update current state partially
                },
                Materialized.`as`("es-orders-store"))                     // persists current order state to store.
           }
}

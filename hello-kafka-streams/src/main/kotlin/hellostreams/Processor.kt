package hellostreams

import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.KStream
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.EnableKafkaStreams
import org.springframework.stereotype.Service

@Service
@EnableKafkaStreams
class Processor {

    @Bean
    fun processSimple(builder: StreamsBuilder): KStream<String, String> {
        val stream: KStream<String, String> = builder.stream("input-topic")

        stream.mapValues { value -> "##=> $value <=##" }                      // transform each record of the stream
              .peek { _, i -> println("process    MESSAGE in stream: $i") }   // outputs something in the console. debugging only
              .to("output-topic");                                      // publishes the transformed records to another topic

        return stream
    }
}

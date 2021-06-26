package hellokafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.test.annotation.DirtiesContext
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.containers.wait.strategy.WaitStrategy
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@SpringBootTest
@DirtiesContext // resets the context between tests
@Testcontainers
class ContainerKafkaTest(
    @Autowired
    private val consumer: Consumer,
    @Autowired
    private val producer: Producer)
{

    companion object{
        @Container
        var kafka = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));
    }


    @Test
    fun `should publish and receive 2 messages`() {
        val msg = "helllllo";

        consumer.latch = CountDownLatch(2)

        producer.send(msg);
        producer.send(msg);

        // wait max 2 sec.
        consumer.latch.await(2000, TimeUnit.MILLISECONDS)

        Assertions.assertThat(consumer.latch.count).isEqualTo(0)
        Assertions.assertThat(consumer.data).isNotNull
        Assertions.assertThat(consumer.data.count()).isEqualTo(2)
        Assertions.assertThat(consumer.data[0]).isEqualTo(msg)
        Assertions.assertThat(consumer.data[1]).isEqualTo(msg)
    }

    @TestConfiguration
    internal class KafkaTestContainersConfiguration {
        @Bean
        fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<Int, String> {
            val factory = ConcurrentKafkaListenerContainerFactory<Int, String>()
            factory.setConsumerFactory(consumerFactory())



            return factory
        }

        @Bean
        fun consumerFactory(): ConsumerFactory<Int, String> {
            return DefaultKafkaConsumerFactory(consumerConfigs())
        }

        @Bean
        fun consumerConfigs(): Map<String, Any> {
            val props: MutableMap<String, Any> = HashMap()
            props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafka.getBootstrapServers()
            props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest";
            props[ConsumerConfig.GROUP_ID_CONFIG] = "unit-test-c-client";
            props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
            props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
            return props
        }

        @Bean
        fun producerFactory(): ProducerFactory<String, String> {
            val configProps: MutableMap<String, Any> = HashMap()
            configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafka.getBootstrapServers()
            configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
            configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
            return DefaultKafkaProducerFactory(configProps)
        }

        @Bean
        fun kafkaTemplate(): KafkaTemplate<String, String> {
            return KafkaTemplate(producerFactory())
        }
    }
}

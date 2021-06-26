package hellokafka;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@DirtiesContext // resets the context between tests
@Testcontainers
class ContainerKafkaTest {

    @Autowired
    public Consumer consumer;
    @Autowired
    public Producer producer;

    @Container
    public static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));

    @Test
    public void should_publish_and_receive_2_messages() throws InterruptedException {
        String msg = "helllllo";

        consumer.latch = new CountDownLatch(2);

        producer.send(msg);
        producer.send(msg);

        // wait max 2 sec.
        consumer.latch.await(2000, TimeUnit.MILLISECONDS);

        Assertions.assertThat(consumer.latch.getCount()).isEqualTo(0);
        Assertions.assertThat(consumer.data).isNotNull();
        Assertions.assertThat(consumer.data.size()).isEqualTo(2);
        Assertions.assertThat(consumer.data.get(0)).isEqualTo(msg);
        Assertions.assertThat(consumer.data.get(1)).isEqualTo(msg);
    }

    @TestConfiguration
    static class KafkaTestContainersConfiguration {

        @Bean
        ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
            ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<Integer, String>();
            factory.setConsumerFactory(consumerFactory());
            return factory;
        }

        @Bean
        ConsumerFactory<Integer, String> consumerFactory() {
            return new DefaultKafkaConsumerFactory(consumerConfigs());
        }

        @Bean
        Map<String, Object> consumerConfigs()  {
            Map<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "unit-test-c-client");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            return props;
        }

        @Bean
        ProducerFactory<String, String> producerFactory() {
            Map<String, Object> configProps  = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            return new DefaultKafkaProducerFactory(configProps);
        }

        @Bean
        KafkaTemplate<String, String> kafkaTemplate()  {
            return new KafkaTemplate(producerFactory());
        }
    }
}

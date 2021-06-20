package hellokafka

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@SpringBootTest(properties = [
    "spring.kafka.consumer.auto-offset-reset=earliest",
    "spring.kafka.consumer.group-id=unit-test-client",
    "spring.kafka.bootstrap-servers=localhost:9099"
])
@DirtiesContext // resets the context between tests
@EmbeddedKafka(brokerProperties = ["listeners=PLAINTEXT://localhost:9099", "port=9099"])
class EmbeddedKafkaTest(
    @Autowired
    private val consumer: Consumer,
    @Autowired
    private val producer: Producer
) {

    @Test
    fun `should publish and receive 2 messages`() {
        val msg = "helllllo";

        consumer.latch = CountDownLatch(2)

        producer.send(msg);
        producer.send(msg);

        // wait max 2 sec.
        consumer.latch.await(2000, TimeUnit.MILLISECONDS)

        assertThat(consumer.latch.count).isEqualTo(0)
        assertThat(consumer.data).isNotNull
        assertThat(consumer.data.count()).isEqualTo(2)
        assertThat(consumer.data[0]).isEqualTo(msg)
        assertThat(consumer.data[1]).isEqualTo(msg)
    }
}



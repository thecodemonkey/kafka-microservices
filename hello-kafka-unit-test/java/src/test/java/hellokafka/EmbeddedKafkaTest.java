package hellokafka;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootTest(properties = {
        "spring.kafka.consumer.auto-offset-reset=earliest",
        "spring.kafka.consumer.group-id=unit-test-client",
        "spring.kafka.bootstrap-servers=localhost:9099"
})
@DirtiesContext // resets the context between tests
@EmbeddedKafka(brokerProperties = {"listeners=PLAINTEXT://localhost:9099", "port=9099"})
class EmbeddedKafkaTest {
    @Autowired
    public Consumer consumer;
    @Autowired
    public Producer producer;

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
}



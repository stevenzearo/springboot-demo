package steve.springboot.nosql.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @author steve
 */
public class TestConsumerRecord extends ConsumerRecord<String, String> {
    public TestConsumerRecord(String topic, int partition, long offset, String key, String value) {
        super(topic, partition, offset, key, value);
    }
}

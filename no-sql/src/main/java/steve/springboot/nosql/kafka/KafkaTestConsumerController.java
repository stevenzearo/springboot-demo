package steve.springboot.nosql.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RequestMapping("test/kafka")
@RestController
public class KafkaTestConsumerController {

    @KafkaListener(topics = {"test"})
    @RequestMapping(value = "/message", method= RequestMethod.GET)
    public String getMessage(ConsumerRecord<String, String> record) throws Exception {
        return String.format("topic = %s, offset = %d, value = %s \n", record.topic(), record.offset(), record.value());
    }
}

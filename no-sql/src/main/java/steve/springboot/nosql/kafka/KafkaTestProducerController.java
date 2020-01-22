package steve.springboot.nosql.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RequestMapping("/test/kafka")
@RestController
@Component
public class KafkaTestProducerController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public String sendMessage(@RequestBody String message) {
        kafkaTemplate.send("test", message);
        return "message send successfully";
    }
}

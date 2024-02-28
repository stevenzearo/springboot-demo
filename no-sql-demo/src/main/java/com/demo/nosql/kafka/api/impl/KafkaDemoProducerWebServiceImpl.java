package com.demo.nosql.kafka.api.impl;

import com.demo.nosql.kafka.api.KafkaDemoProducerWebService;
import jakarta.annotation.Resource;
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
@RestController
public class KafkaDemoProducerWebServiceImpl implements KafkaDemoProducerWebService {
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public String sendMessage(@RequestBody String message) {
        kafkaTemplate.send("test", message);
        return "message send successfully";
    }
}

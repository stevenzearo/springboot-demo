package com.demo.nosql.kafka.api.impl;

import com.demo.nosql.kafka.service.KafkaDemoService;
import com.demo.nosql.kafka.api.KafkaDemoProducerWebService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
public class KafkaDemoProducerWebServiceImpl implements KafkaDemoProducerWebService {
    @Resource
    private KafkaDemoService kafkaDemoService;

    public String sendMessage(@RequestBody String message) {
        kafkaDemoService.sendMessage(message);
        return "message send successfully";
    }
}

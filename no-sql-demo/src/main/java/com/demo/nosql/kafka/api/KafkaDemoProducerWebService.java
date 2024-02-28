package com.demo.nosql.kafka.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author steve
 */

public interface KafkaDemoProducerWebService {
    @RequestMapping(value = "/api/kafka/message", method = RequestMethod.POST)
    String sendMessage(@RequestBody String message);
}

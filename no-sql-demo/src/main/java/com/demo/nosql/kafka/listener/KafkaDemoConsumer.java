package com.demo.nosql.kafka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.stereotype.Component;

/**
 * @author steve
 */
@Component
@EnableKafka
public class KafkaDemoConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaDemoConsumer.class);

    @KafkaListener(topics = {"test"} )
    public void listen(String value, ConsumerRecordMetadata recordMetadata) throws Exception {
        String info = String.format("topic = %s,  offset = %d, value = %s \n", recordMetadata.topic(), recordMetadata.offset(), value);
        logger.info(info);
    }
}

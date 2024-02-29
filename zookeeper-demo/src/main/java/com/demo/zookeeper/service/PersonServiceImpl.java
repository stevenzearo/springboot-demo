package com.demo.zookeeper.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author steve
 */
public class PersonServiceImpl implements PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Override
    public void say(String words) {
        LOGGER.info("say: " + words);
    }
}

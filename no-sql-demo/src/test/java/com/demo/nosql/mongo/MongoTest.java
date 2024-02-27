package com.demo.nosql.mongo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.demo.nosql.NoSqlApplicationTests;
import com.demo.nosql.mongo.domain.Person;

/**
 * @author steve
 */
@Disabled
public class MongoTest extends NoSqlApplicationTests {

    @Autowired
    MongoTemplate template;

    @Test
    public void testContext() {
    }

    @Test
    public void test() {
        String collectionName = template.getCollectionName(Person.class);
        System.out.println(collectionName);
    }

}

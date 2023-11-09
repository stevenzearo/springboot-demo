package steve.springboot.nosql.mongo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import steve.springboot.nosql.NoSqlApplicationTests;
import steve.springboot.nosql.mongo.spring.domain.User;

/**
 * @author steve
 */
public class MongoTest extends NoSqlApplicationTests {

    @Autowired
    MongoTemplate template;

    @Test
    public void testContext() {
    }

    @Test
    public void test() {
        String collectionName = template.getCollectionName(User.class);
        System.out.println(collectionName);
    }

}

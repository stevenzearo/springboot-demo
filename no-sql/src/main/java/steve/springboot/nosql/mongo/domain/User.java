package steve.springboot.nosql.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author steve
 */
@Document(collection = "user") // this annotation is for create collection
public class User {
    @Id
    public String id;

    public String name;

    public String email;

    public Integer age;

    public User() {
    }

    public User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}

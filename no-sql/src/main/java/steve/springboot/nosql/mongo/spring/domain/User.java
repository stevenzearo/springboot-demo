package steve.springboot.nosql.mongo.spring.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author steve
 */
@Document(collection = "user") // this annotation is for create collection
public class User {
    @Id
    @Field("_id")
    public String id;

    @Field("name")
    public String name;

    @Field("email")
    public String email;

    @Field("age")
    public Integer age;

    public User() {
    }

    public User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}

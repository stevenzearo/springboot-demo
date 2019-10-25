package steve.springboot.nosql.elasticsearch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.stereotype.Repository;

/**
 * @author steve
 */
@Repository
@Document(indexName = "", type = "")
public class Person {
    @Id
    public String id;

    @Field(type = FieldType.Auto)
    public String name;

    public String email;

    public Integer age;

    public Person() {
    }

    public Person(String id, String name, String email, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }
}

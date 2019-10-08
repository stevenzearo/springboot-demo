package steve.springboot.nosql.elasticsearch.prototype;

/**
 * @author steve
 */
public class Person {
    public String name;

    public String email;

    public Integer age;

    public Person() {
    }

    public Person(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}

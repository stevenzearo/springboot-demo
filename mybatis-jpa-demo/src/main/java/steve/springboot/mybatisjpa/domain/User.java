package steve.springboot.mybatisjpa.domain;

import jakarta.persistence.*;

/**
 * Author  ZLH
 * Date  2019/10/3
 * Time  19:20
 * Version  1.0
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @Column(name = "name")
    public String name;

    @Column(name = "age")
    public Integer age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

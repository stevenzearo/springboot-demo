package com.demo.nosql.elasticsearch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.stereotype.Repository;

/**
 * @author steve
 */
@Repository
@Document(indexName = "person")
public class Person {
    @Id
    public String id;

    @Field(type = FieldType.Auto)
    public String name;

    @Field(type = FieldType.Text)
    public String email;

    @Field(type = FieldType.Integer)
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

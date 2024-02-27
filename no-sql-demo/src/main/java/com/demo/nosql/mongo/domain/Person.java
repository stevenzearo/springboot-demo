package com.demo.nosql.mongo.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;

import java.time.ZonedDateTime;

/**
 * @author steve
 */
@Document(collection = "persons") // this annotation is for create collection
public class Person {
    @Id
    @Field(name = "_id")
    public ObjectId id;

    @Field(name = "birthday", targetType = FieldType.DATE_TIME)
    public ZonedDateTime birthday;

    @Field("name")
    public String name;

    @Field("email")
    public String email;

    @Field("address")
    public Address address;

    public Person() {
    }
}

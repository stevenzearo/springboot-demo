package com.demo.nosql.mongo.service;

import jakarta.annotation.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.demo.nosql.mongo.domain.Person;

import java.util.List;

/**
 * @author steve
 */
@Service
public class PersonService {
    @Resource
    MongoTemplate template;

    public Person create(Person person) {
        return template.insert(person);
    }

    public Person get(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return template.findOne(query, Person.class);
    }

    public List<Person> searchByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return template.find(query, Person.class);
    }

    public void update(Person person) {
        Query query = new Query(Criteria.where("_id").is(person.id));
        Update update = new Update();
        update.set("name", person.name)
            .set("email", person.email)
            .set("address", person.address)
            .set("birthday", person.birthday);
        template.updateFirst(query, update, Person.class);
    }

    public void delete(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        template.remove(query, Person.class);
    }
}
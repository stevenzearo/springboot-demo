package com.demo.nosql.mongo.web;

import com.demo.nosql.mongo.web.person.CreatePersonRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.demo.nosql.mongo.domain.Person;

import java.util.List;

/**
 * @author steve
 */
@RestController
public interface PersonWebService {

    @RequestMapping(value = "/api/mongo/person", method = RequestMethod.POST)
    Person create(@RequestBody CreatePersonRequest request);

    @RequestMapping(value = "/api/mongo/person/{id}", method = RequestMethod.GET)
    Person get(@PathVariable("id") String id);

    @RequestMapping(value = "/api/mongo/person/{id}", method = RequestMethod.PUT)
    void update(@PathVariable("id") String id, @RequestBody Person person);

    @RequestMapping(value = "/api/mongo/person", method = RequestMethod.PUT)
    List<Person> search(@RequestBody String name);

    @RequestMapping(value = "/api/mongo/person/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") String id);
}

package com.demo.nosql.mongo.api;

import com.demo.nosql.mongo.api.person.CreatePersonRequest;
import com.demo.nosql.mongo.api.person.CreatePersonResponse;
import com.demo.nosql.mongo.api.person.SearchPersonRequest;
import com.demo.nosql.mongo.api.person.SearchPersonResponse;
import com.demo.nosql.mongo.api.person.UpdatePersonRequest;
import com.demo.nosql.mongo.domain.Person;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steve
 */
@RestController
public interface PersonWebService {

    @RequestMapping(value = "/api/mongo/person", method = RequestMethod.POST)
    CreatePersonResponse create(@RequestBody CreatePersonRequest request);

    @RequestMapping(value = "/api/mongo/person/{id}", method = RequestMethod.GET)
    Person get(@PathVariable("id") String id);

    @RequestMapping(value = "/api/mongo/person/{id}", method = RequestMethod.PUT)
    void update(@PathVariable("id") String id, @RequestBody UpdatePersonRequest request);

    @RequestMapping(value = "/api/mongo/person", method = RequestMethod.PUT)
    SearchPersonResponse search(@RequestBody SearchPersonRequest request);

    @RequestMapping(value = "/api/mongo/person/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") String id);
}

package com.demo.nosql.elasticsearch.web;

import com.demo.nosql.elasticsearch.domain.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author steve
 */
@RequestMapping("/ajax")
public interface ESPersonWebService {
    @RequestMapping(value = "/api/person", method = RequestMethod.POST)
    Person save(Person person);

    @RequestMapping(value = "/api/person", method = RequestMethod.GET)
    Person get(String id);

    @RequestMapping(value = "/api/person", method = RequestMethod.PUT)
    List<Person> search(Person person);

    @RequestMapping(value = "/api/person", method = RequestMethod.DELETE)
    void delete(String id);
}

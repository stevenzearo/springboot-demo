package com.demo.nosql.elasticsearch.web;

import com.demo.nosql.elasticsearch.domain.Person;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author steve
 */
@RestController
public interface ESPersonWebService {
    @RequestMapping(value = "/api/es/person", method = RequestMethod.POST)
    Person save(@RequestBody Person person);

    @RequestMapping(value = "/api/es/person/{id}", method = RequestMethod.GET)
    Person get(@PathVariable String id);

    @RequestMapping(value = "/api/es/person", method = RequestMethod.PUT)
    List<Person> search(@RequestBody Person person);

    @RequestMapping(value = "/api/es/person/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable String id);
}

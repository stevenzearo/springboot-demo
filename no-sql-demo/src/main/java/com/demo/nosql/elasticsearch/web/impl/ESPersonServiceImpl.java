package com.demo.nosql.elasticsearch.web.impl;

import com.demo.nosql.elasticsearch.web.ESPersonWebService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.nosql.elasticsearch.domain.Person;
import com.demo.nosql.elasticsearch.service.ESPersonService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author steve
 */
@RestController
public class ESPersonServiceImpl implements ESPersonWebService {
    @Resource
    ESPersonService personService;

    @Override
    public Person save(Person person) {
        return personService.create(person);
    }

    @Override
    public Person get(String id) {
        return personService.get(id);
    }

    @Override
    public List<Person> search(Person person) {
        return personService.search(person);
    }

    @Override
    public void delete(String id) {
        personService.delete(id);
    }
}

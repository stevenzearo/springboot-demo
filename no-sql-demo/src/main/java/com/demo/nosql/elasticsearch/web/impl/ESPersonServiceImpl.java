package com.demo.nosql.elasticsearch.web.impl;

import com.demo.nosql.elasticsearch.web.ESPersonWebService;
import jakarta.annotation.Resource;
import com.demo.nosql.elasticsearch.domain.Person;
import com.demo.nosql.elasticsearch.service.ESPersonService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author steve
 */
@Component
public class ESPersonServiceImpl implements ESPersonWebService {
    @Resource
    ESPersonService esPersonService;

    @Override
    public Person save(Person person) {
        return esPersonService.create(person);
    }

    @Override
    public Person get(String id) {
        return esPersonService.get(id);
    }

    @Override
    public List<Person> search(Person person) {
        return esPersonService.search(person);
    }

    @Override
    public void delete(String id) {
        esPersonService.delete(id);
    }
}

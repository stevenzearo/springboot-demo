package com.demo.nosql.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import com.demo.nosql.elasticsearch.domain.Person;

/**
 * @author steve
 */
@Component
public interface ESPersonRepository extends ElasticsearchRepository<Person, String> {
}
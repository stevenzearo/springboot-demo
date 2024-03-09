package com.demo.nosql.elasticsearch.service;

import com.demo.nosql.elasticsearch.repository.ESPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;
import com.demo.nosql.elasticsearch.domain.Person;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author steve
 */
@Service
public class ESPersonService {
    @Autowired
    ESPersonRepository repository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public Person create(Person person) {
        return repository.save(person);
    }

    public Person get(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Person> search(Person person) {
        NativeQueryBuilder nativeQueryBuilder = new NativeQueryBuilder();
        if (person.name != null) {
            nativeQueryBuilder.withQuery(new StringQuery(person.name));
        }
        if (person.email!= null) {
            nativeQueryBuilder.withQuery(new StringQuery(person.email));
        }
        NativeQuery query = nativeQueryBuilder.build();
        return elasticsearchTemplate.search(query, Person.class).getSearchHits()
                .stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}

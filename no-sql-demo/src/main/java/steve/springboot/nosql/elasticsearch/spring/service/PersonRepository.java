package steve.springboot.nosql.elasticsearch.spring.service;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import steve.springboot.nosql.elasticsearch.domain.Person;

/**
 * @author steve
 */
@Component
public interface PersonRepository extends ElasticsearchRepository<Person, String> {
}
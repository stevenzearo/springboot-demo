package steve.springboot.nosql.elasticsearch.spring.service;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;
import steve.springboot.nosql.elasticsearch.domain.Person;

import java.util.List;

/**
 * @author steve
 */
@Service
public class PersonService {
    @Autowired
    PersonRepository repository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public Person create(Person person) {
        return repository.save(person);
    }

    public Person get(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Person> search(Person person) {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
            .withQuery(new QueryStringQueryBuilder(person.name))
            .withQuery(new QueryStringQueryBuilder(person.email))
            .build();
        return elasticsearchTemplate.queryForList(query, Person.class);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public interface PersonRepository extends ElasticsearchRepository<Person, String> {
    }
}

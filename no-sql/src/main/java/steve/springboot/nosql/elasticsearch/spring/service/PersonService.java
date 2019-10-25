package steve.springboot.nosql.elasticsearch.spring.service;

import org.apache.lucene.search.Query;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    public interface PersonRepository extends ElasticsearchRepository<Person, String> {
    }

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
}

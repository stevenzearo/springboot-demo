package steve.springboot.nosql.elasticsearch.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import steve.springboot.nosql.elasticsearch.domain.Person;
import steve.springboot.nosql.elasticsearch.spring.service.PersonService;

import java.util.List;

/**
 * @author steve
 */
@Component
public class PersonServiceImpl implements PersonWebService {
    @Autowired
    PersonService personService;

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

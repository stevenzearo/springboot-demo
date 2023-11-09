package steve.springboot.nosql.elasticsearch.spring.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import steve.springboot.nosql.elasticsearch.domain.Person;

import java.util.List;

/**
 * @author steve
 */
@RequestMapping("/ajax")
@RestController
public interface PersonWebService {
    @RequestMapping(value = "/person/", method = RequestMethod.PUT)
    Person save(Person person);

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    Person get(String id);

    @RequestMapping(value = "/person/search", method = RequestMethod.PUT)
    List<Person> search(Person person);

    @RequestMapping(value = "/person/search", method = RequestMethod.DELETE)
    void delete(String id);
}

package steve.springboot.nosql.mongo.spring.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import steve.springboot.nosql.mongo.spring.domain.User;

import java.util.List;

/**
 * @author steve
 */
@RestController
public interface UserWebService {

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    User create(@RequestBody User user);

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    User get(@PathVariable("id") String id);

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    void update(@PathVariable("id") String id, @RequestBody User user);

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    List<User> search(@RequestBody String name);

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") String id);
}

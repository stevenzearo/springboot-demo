package steve.springboot.nosql.mongo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import steve.springboot.nosql.mongo.domain.User;
import steve.springboot.nosql.mongo.service.UserService;

import java.util.List;

/**
 * @author steve
 */
@RestController
public class UserWebServiceImpl implements UserWebService{

    @Autowired
    UserService service;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") String id) {
        return service.get(id);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") String id, @RequestBody User user) {
        user.id = id;
        service.update(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public List<User> search(@RequestBody String name) {
        return service.searchByName(name);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }
}

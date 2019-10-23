package steve.springboot.nosql.mongo.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import steve.springboot.nosql.mongo.spring.domain.User;
import steve.springboot.nosql.mongo.spring.service.UserService;

import java.util.List;

/**
 * @author steve
 */
@Component
public class UserWebServiceImpl implements UserWebService{

    @Autowired
    UserService service;

    public User create(@RequestBody User user) {
        return service.create(user);
    }

    public User get(@PathVariable("id") String id) {
        return service.get(id);
    }

    public void update(@PathVariable("id") String id, @RequestBody User user) {
        user.id = id;
        service.update(user);
    }

    public List<User> search(@RequestBody String name) {
        return service.searchByName(name);
    }

    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }
}

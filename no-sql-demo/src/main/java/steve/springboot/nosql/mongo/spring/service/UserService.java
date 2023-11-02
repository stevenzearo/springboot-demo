package steve.springboot.nosql.mongo.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import steve.springboot.nosql.mongo.spring.domain.User;

import java.util.List;

/**
 * @author steve
 */
@Service
public class UserService {
    @Autowired
    MongoTemplate template;

    public User create(User user) {
        return template.insert(user);
    }

    public User get(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return template.findOne(query, User.class);
    }

    public List<User> searchByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return template.find(query, User.class);
    }

    public void update(User user) {
        Query query = new Query(Criteria.where("_id").is(user.id));
        Update update = new Update();
        update.set("name", user.name)
            .set("email", user.email)
            .set("age", user.age);
        template.updateFirst(query, update, User.class);
    }

    public void delete(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        template.remove(query, User.class);
    }
}
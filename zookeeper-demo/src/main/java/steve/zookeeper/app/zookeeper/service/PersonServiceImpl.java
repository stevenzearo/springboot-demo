package steve.zookeeper.app.zookeeper.service;

/**
 * @author steve
 */
public class PersonServiceImpl implements PersonService {
    @Override
    public void say(String words) {
        System.out.println(words);
    }
}

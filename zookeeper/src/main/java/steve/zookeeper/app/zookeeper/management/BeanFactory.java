package steve.zookeeper.app.zookeeper.management;

import steve.zookeeper.app.zookeeper.registry.Registry;

/**
 * @author steve
 */
public class BeanFactory {
    private static final Registry registry = new Registry("127.0.0.1:2181", 5, "/service");


    public <T> void addBean(Class<T> aClass, T instance) throws Exception {
        registry.registerService(aClass, instance);
    }

    public <T> T getBean(Class<T> tClass) {
        return registry.getClassInstance(tClass);
    }

    public <T> void removeBean(Class<T> tClass) throws Exception {
        registry.removeService(tClass);
    }
}

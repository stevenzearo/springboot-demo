package steve.zookeeper.app;

import steve.zookeeper.app.zookeeper.registry.Registry;

/**
 * @author steve
 */
public class Main {
    public static void main(String[] args) {
        Class<?> aClass = Registry.class;
        System.out.println(aClass.getCanonicalName());
    }
}

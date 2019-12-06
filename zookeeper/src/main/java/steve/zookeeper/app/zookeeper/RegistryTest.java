package steve.zookeeper.app.zookeeper;

import steve.zookeeper.app.zookeeper.registry.Registry;

import java.io.IOException;

/**
 * @author steve
 */
public class RegistryTest {
    public static void main(String[] args) throws IOException {
        Registry registry = new Registry("127.0.0.1:2181", 5000);
//        registry.registerService("/")
    }
}

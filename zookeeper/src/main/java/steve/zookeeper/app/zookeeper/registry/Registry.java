package steve.zookeeper.app.zookeeper.registry;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author steve
 */
public class Registry {
    private static final Map<String, Class<?>> classMap = new ConcurrentHashMap<>();
    private static final Map<String, String> pathClassNameMap = new ConcurrentHashMap<>();
    private static final Map<String, String> classNamePathMap = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger(Registry.class);
    private ZooKeeper zooKeeper;

    public Registry(String host, int timeoutSeconds) throws IOException {
        this.zooKeeper = new ZooKeeper(host, timeoutSeconds * 60 * 1000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState().getIntValue() == 3) {
                    logger.info("connection established......");
                } else {
                    logger.info("connected failed......");
                }
                if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                    String path = watchedEvent.getPath();
                    String className = pathClassNameMap.get(path);
                    if (className != null) {
                        classMap.remove(className);
                        pathClassNameMap.remove(path);
                        classNamePathMap.remove(className);
                    }
                    logger.info(String.format("class: %s removed from registry......", className));
                }
            }
        });
        zooKeeper.addAuthInfo("digest", "admin:admin".getBytes());
    }


    public boolean registerService(String path, Class<?> aClass) throws KeeperException, InterruptedException {
        String className = aClass.getCanonicalName();
        List<ACL> aclList = List.of(new ACL(ZooDefs.Perms.READ, ZooDefs.Ids.ANYONE_ID_UNSAFE));
        String pathCreated = zooKeeper.create(path, className.getBytes(), aclList, CreateMode.EPHEMERAL_SEQUENTIAL);
        classMap.put(className, aClass);
        pathClassNameMap.put(pathCreated, className);
        classNamePathMap.put(className, pathCreated);
        return false;
    }

    public <T> void removeService(String className, Class<T> tClass) {
        String path = classNamePathMap.get(className);
        Class<?> aClass = classMap.get(className);
        if (aClass.isInstance(tClass)) {
            try {
                zooKeeper.delete(path, -1);
                pathClassNameMap.remove(path);
                classNamePathMap.remove(className);
                classMap.remove(className);
            } catch (InterruptedException | KeeperException e) {
                e.printStackTrace();
            }

        }
    }

    public Map<String, Class<?>> getClassMap() {
        return classMap;
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }
}

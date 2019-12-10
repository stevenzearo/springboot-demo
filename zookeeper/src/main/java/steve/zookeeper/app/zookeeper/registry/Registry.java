package steve.zookeeper.app.zookeeper.registry;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
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
    private static final Map<Class<?>, Object> classMap = new ConcurrentHashMap<>();
    private static final Map<String, String> pathClassNameMap = new ConcurrentHashMap<>();
    private static final Map<String, String> classNamePathMap = new ConcurrentHashMap<>();
    private static final Map<String, Class<?>> classNameClassMap = new ConcurrentHashMap<>();
    private final Logger logger = LoggerFactory.getLogger(Registry.class);
    private ZooKeeper zooKeeper;
    private String rootPath;

    public Registry(String host, int timeoutSeconds, String rootPath) {
        try {
            this.rootPath = rootPath;
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
                        Class<?> aClass = classNameClassMap.get(className);
                        if (className != null) {
                            pathClassNameMap.remove(path);
                            classMap.remove(aClass);
                            classNameClassMap.remove(className);
                            classNamePathMap.remove(className);
                        }
                        logger.info(String.format("class: %s removed from registry......", className));
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        zooKeeper.addAuthInfo("digest", "admin:admin".getBytes());
        ACL create = new ACL(ZooDefs.Perms.CREATE, ZooDefs.Ids.ANYONE_ID_UNSAFE);
        ACL read = new ACL(ZooDefs.Perms.READ, ZooDefs.Ids.ANYONE_ID_UNSAFE);
        ACL write = new ACL(ZooDefs.Perms.WRITE, ZooDefs.Ids.ANYONE_ID_UNSAFE);
        ACL delete = new ACL(ZooDefs.Perms.DELETE, ZooDefs.Ids.ANYONE_ID_UNSAFE);

        List<ACL> aclList = List.of(create, read, write, delete);
        try {
            Stat exists = zooKeeper.exists(rootPath, null);
            if (exists == null)
                this.rootPath = zooKeeper.create(rootPath, null, aclList, CreateMode.PERSISTENT);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void registerService(Class<?> aClass, Object instance) throws Exception {
        if (!aClass.isInstance(instance)) throw new Exception("object is not class's instance");
        String className = aClass.getCanonicalName();
        String data = instance.getClass().getCanonicalName();
        ACL create = new ACL(ZooDefs.Perms.CREATE, ZooDefs.Ids.ANYONE_ID_UNSAFE);
        ACL read = new ACL(ZooDefs.Perms.READ, ZooDefs.Ids.ANYONE_ID_UNSAFE);
        ACL write = new ACL(ZooDefs.Perms.WRITE, ZooDefs.Ids.ANYONE_ID_UNSAFE);
        ACL delete = new ACL(ZooDefs.Perms.DELETE, ZooDefs.Ids.ANYONE_ID_UNSAFE);

        List<ACL> aclList = List.of(create, read, write, delete);
        String createPath = rootPath + "/" + className;
        try {

            Stat exists = zooKeeper.exists(createPath, null);
            if (exists == null)
                createPath = zooKeeper.create(rootPath + "/" + className, data.getBytes(), aclList, CreateMode.EPHEMERAL);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        classMap.put(aClass, instance);
        pathClassNameMap.put(createPath, className);
        classNamePathMap.put(className, createPath);
        classNameClassMap.put(className, aClass);
    }

    public <T> void removeService(Class<T> tClass) throws Exception {
        String className = tClass.getCanonicalName();
        String path = classNamePathMap.get(className);
        Object instance = classMap.get(tClass);
        if (instance == null) throw new Exception("class not registered");
        if (tClass.isInstance(instance)) {
            try {
                zooKeeper.delete(path, -1);
                pathClassNameMap.remove(path);
                classMap.remove(tClass);
                classNamePathMap.remove(className);
                classNameClassMap.remove(className);
            } catch (InterruptedException | KeeperException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T getClassInstance(Class<T> tClass) {
        @SuppressWarnings("unchecked")
        T o = (T) classMap.get(tClass);
        return o;
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }
}

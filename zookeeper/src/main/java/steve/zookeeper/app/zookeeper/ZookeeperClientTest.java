package steve.zookeeper.app.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author steve
 */
public class ZookeeperClientTest {
    private final Logger logger = LoggerFactory.getLogger(ZookeeperClientTest.class);
    ZooKeeper zooKeeper = null;

    @BeforeEach
    public void before() throws Exception {
        zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState().getIntValue() == 3) logger.warn("connection established......");
            }
        });
        zooKeeper.addAuthInfo("digest", "admin:admin".getBytes());

    }

    @Test
    public void create() throws Exception {
//        zooKeeper.create("/test", "123".getBytes(), )
        List<ACL> aclList = new ArrayList<>();
        aclList.add(new ACL(ZooDefs.Perms.READ, ZooDefs.Ids.ANYONE_ID_UNSAFE));
        String s = zooKeeper.create("/test", "data".getBytes(), aclList, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println(s.substring("/test".length()));
    }

    @Test
    public void exist() throws KeeperException, InterruptedException {
        String path = "/test0000000064";
        Stat exists = zooKeeper.exists(path, null);
        System.out.println(exists);
        if (exists != null) zooKeeper.delete(path, -1);
        Stat exists2 = zooKeeper.exists(path, null);
        System.out.println(exists2);
    }

    @Test
    public void get() throws KeeperException, InterruptedException {
//        byte[] data = zooKeeper.getData("/test", null, null);
        List<String> stringList = zooKeeper.getChildren("/", false);
//        System.out.println(new String(data));
        stringList.forEach(System.out::println);
    }

    @Test
    @AfterEach
    public void after() throws InterruptedException {
        zooKeeper.close();
    }
}

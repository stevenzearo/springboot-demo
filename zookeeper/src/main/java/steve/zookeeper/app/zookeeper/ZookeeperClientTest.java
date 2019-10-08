package steve.zookeeper.app.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.AuthenticationProvider;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author steve
 */
public class ZookeeperClientTest {
    ZooKeeper zooKeeper = null;
    private final Logger logger = LoggerFactory.getLogger(ZookeeperClientTest.class);

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
        zooKeeper.create("/test", "data".getBytes(), aclList, CreateMode.PERSISTENT);
    }

    @Test
    public void exist() throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists("/test", null);
        System.out.println(exists);

    }

    @Test
    public void get() throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData("/test", null, null);
        System.out.println(new String(data));
    }

    @Test
    @AfterEach
    public void after() throws InterruptedException {
        zooKeeper.close();
    }
}

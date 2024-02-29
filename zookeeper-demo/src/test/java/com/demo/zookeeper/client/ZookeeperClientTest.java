package com.demo.zookeeper.client;

import com.demo.zookeeper.util.ListUtil;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author steve
 */
@Disabled
public class ZookeeperClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperClientTest.class);
    ZooKeeper zooKeeper = null;

    @BeforeEach
    public void before() throws Exception {
        zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState().getIntValue() == 3) LOGGER.warn("connection established......");
            }
        });
        zooKeeper.addAuthInfo("digest", "admin:admin".getBytes());

    }

    @Test
    public void create() throws Exception {
//        zooKeeper.create("/test", "123".getBytes(), )
        List<ACL> aclList = ListUtil.arrayListOf(new ACL(ZooDefs.Perms.READ, ZooDefs.Ids.ANYONE_ID_UNSAFE));
        String s = zooKeeper.create("/test", "data".getBytes(), aclList, CreateMode.PERSISTENT_SEQUENTIAL);
        LOGGER.info(s.substring("/test".length()));
    }

    @Test
    public void exist() throws KeeperException, InterruptedException {
        String path = "/test0000000064";
        Stat exists = zooKeeper.exists(path, null);
        LOGGER.info(String.valueOf(exists));
        if (exists != null) zooKeeper.delete(path, -1);
        Stat exists2 = zooKeeper.exists(path, null);
        LOGGER.info(String.valueOf(exists2));
    }

    @Test
    public void get() throws KeeperException, InterruptedException {
        List<String> stringList = zooKeeper.getChildren("/", false);
        stringList.forEach(LOGGER::info);
    }

    @Test
    @AfterEach
    public void after() throws InterruptedException {
        zooKeeper.close();
    }
}

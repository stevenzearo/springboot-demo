package steve.zookeeper.app.zookeeper;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author steve
 */
public class ZookeeperLock {
    ZooKeeper zooKeeper = null;

    private void init() throws Exception {
        zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, null);
        Stat exists = zooKeeper.exists("/lock", null);
    }

    public String register() {
        return null;
    }

    private boolean isMinimumLock(String lockInfo) {
        return false;
    }

    public boolean getLock() {
        return false;
    }

    public boolean isValidLock() {
        return false;
    }

    private void deleteInvalidLock() {

    }
}

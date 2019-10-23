package steve.zookeeper.app.zookeeper;

import javafx.util.Pair;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.zookeeper.Watcher.Event.EventType.NodeDeleted;

/**
 * @author steve
 */
public class ZookeeperLock {
    private static ZookeeperLock zookeeperLock = null;
    private ZooKeeper zooKeeper;
    private String thisLock;
    private String lockPath;

    private ZookeeperLock(String connectionString, String lockPath) throws Exception {
        this.lockPath = lockPath;
        zooKeeper = new ZooKeeper(connectionString, 5000, null);
        Stat exists = zooKeeper.exists(lockPath, null);
        List<ACL> aclList = new ArrayList<>();
        aclList.add(new ACL(ZooDefs.Perms.ALL, ZooDefs.Ids.AUTH_IDS));
        if (exists == null) zooKeeper.create(lockPath, "VALID".getBytes(), aclList, CreateMode.PERSISTENT);
        List<String> children = zooKeeper.getChildren(lockPath, true);
        thisLock = zooKeeper.create(lockPath + "/" + lockPath + "-", "WAIT".getBytes(), aclList, CreateMode.EPHEMERAL_SEQUENTIAL);
        if (children.size() == 1) {
            zooKeeper.exists(thisLock, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    // 监听最小序号的删除状态，最小节点被删除后，锁的状态改为valid
                    if (watchedEvent.getType() == NodeDeleted) {
                        try {
                            Stat lockExists = zooKeeper.exists(lockPath, false);
                            zooKeeper.setData(lockPath, "VALID".getBytes(), lockExists.getVersion() + 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
        }
        Logger logger = LoggerFactory.getLogger(ZookeeperLock.class);
        logger.warn("create a lock:" + thisLock);
    }

    private synchronized void init() throws Exception {
        if (zookeeperLock == null) {
            zookeeperLock = new ZookeeperLock("127.0.0.1:2181", "/lock");
        }
    }

    private boolean isMinimumLock() throws Exception {
        List<String> stringList = zooKeeper.getChildren(lockPath, false);
        List<Pair<String, Integer>> collect = stringList.stream()
            .map(s -> new Pair<String, Integer>(s, Integer.valueOf(s.split("-")[s.split("-").length - 1])))
            .sorted((p1, p2) -> p1.getValue() - p2.getValue())
            .collect(Collectors.toList());
        return collect.get(0).getKey().equals(thisLock);
    }

    public void acquire() throws Exception {
        init();
        // 判断锁的状态valid, 判断当前锁是否为最小序号
        boolean onLock = false;
        Stat thisExists = zooKeeper.exists(thisLock, false);
        String thisData = new String(zooKeeper.getData(thisLock, false, thisExists));
        if ("ONLOCK".equals(thisData)) onLock = true;
        while (!onLock) {
            Stat lockExists = zooKeeper.exists(lockPath, false);
            String lockData = new String(zooKeeper.getData(lockPath, false, lockExists));
            if ("VALID".equals(lockData) && isMinimumLock()) {
                zooKeeper.setData(lockPath, "INVALID".getBytes(), lockExists.getVersion() + 1);
                zooKeeper.setData(thisLock, "ONLOCK".getBytes(), thisExists.getVersion() + 1);
                onLock = true;
            }
        }
    }

    public void release() throws Exception {
        Stat thisExists = zooKeeper.exists(thisLock, false);
        Stat lockExists = zooKeeper.exists(lockPath, false);
        zooKeeper.delete(thisLock, thisExists.getVersion());
        zooKeeper.setData(lockPath, "VALID".getBytes(), lockExists.getVersion() + 1);
        zooKeeper.close();
    }
}

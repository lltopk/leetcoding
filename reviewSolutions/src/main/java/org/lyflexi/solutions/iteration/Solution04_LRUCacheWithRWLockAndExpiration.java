package org.lyflexi.solutions.iteration;

/**
 * @Author: ly
 * @Date: 2024/3/25 17:41
 */
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/*
* 当前共享数据除了HashMap还有DNode，ConcurrentHashMap可以保证缓存的安全，但DNode的安全无法保证
*
* 所以最好使用synchronized/Lock来统一控制，最终选择使用读写锁ReentrantReadWrite优化性能:
* ReadWriteLock rwLock = = new ReentrantReadWriteLock();
* - Lock readLock = rwLock.readLock();
* - Lock writeLock = rwLock.writeLock();
*
* */
class Solution04_LRUCacheWithRWLockAndExpiration {
    static class DNode {
        int key;
        int value;
        long expirationTime;
        DNode prev;
        DNode next;

        public DNode(int key, int value, long expirationTime) {
            this.key = key;
            this.value = value;
            this.expirationTime = expirationTime;
        }
    }

    private final int capacity;
    private final HashMap<Integer, DNode> keyToDNodeCache;
    private final ReadWriteLock rwLock;

    private final Lock readLock;
    private final Lock writeLock;
    private final DNode dummy;

    //初始化成员变量
    public Solution04_LRUCacheWithRWLockAndExpiration(int capacity) {
        this.capacity = capacity;
        this.keyToDNodeCache = new HashMap<>(capacity);
        this.rwLock = new ReentrantReadWriteLock();
        this.readLock = rwLock.readLock();
        this.writeLock = rwLock.writeLock();
        this.dummy = new DNode(0, 0, Long.MAX_VALUE); // Dummy node with expiration time set to maximum
        this.dummy.next = this.dummy;
        this.dummy.prev = this.dummy;
    }

    //处理方式：分两种情况，分别使用写锁降级+DCL双减
    public int get(int key) {
        readLock.lock();
        try {
            DNode node = keyToDNodeCache.get(key);
            if (node != null) {
                //缓存过期情况
                if (node.expirationTime < System.currentTimeMillis()) {
                    //在获取写锁之前，必须首先释放读锁。
                    readLock.unlock();
                    writeLock.lock();
                    try {
                        //DCL这里需要再次判断数据的有效性,因为在我们释放读锁和获取写锁的空隙之内，可能有其他写线程导致链表满删除了当前缓存。
                        if (node != null) {
                            keyToDNodeCache.remove(key);//?个人感觉没必要使用concurrenthashmap，因为这里要保证if代码块的原子性
                            removeNode(node);
                        }
                    } finally {
                        writeLock.unlock();
                    }
                    return -1; // Node has expired
                }


                //正常情况->缓存没过期
                //在获取写锁之前，必须首先释放读锁。
                readLock.unlock();
                writeLock.lock();
                try {
                    //DCL这里需要再次判断数据的有效性,因为在我们释放读锁和获取写锁的空隙之内，可能有其他写线程导致链表满删除了当前缓存。
                    if (node != null) {
                        moveToTail(node);
                    }
                    //在不释放写锁的情况下，直接获取读锁，这就是读写锁的降级。
                    readLock.lock();
                } finally {
                    //释放了写锁，但是依然持有读锁
                    writeLock.unlock();
                }
                return node.value;
            }
            return -1;
        } finally {
            readLock.unlock();
        }
    }

    public void put(int key, int value, long expirationTimeMillis) {
        writeLock.lock();

        try {
            DNode node = keyToDNodeCache.get(key);

            if (node != null) {
                node.value = value;
                node.expirationTime = System.currentTimeMillis() + expirationTimeMillis; // Update expiration time
                moveToTail(node);
                return;
            }

            if (keyToDNodeCache.size() >= capacity) {
                DNode toRemove = dummy.next;
                keyToDNodeCache.remove(toRemove.key);
                removeNode(toRemove);
            }

            node = new DNode(key, value, System.currentTimeMillis() + expirationTimeMillis);
            addToTail(node);
            keyToDNodeCache.put(key, node);
        } finally {
            writeLock.unlock();
        }
    }

    private void moveToTail(DNode node) {
        removeNode(node);
        addToTail(node);
    }

    private void addToTail(DNode node) {
        node.prev = dummy.prev;
        node.next = dummy;
        dummy.prev.next = node;
        dummy.prev = node;
    }

    private void removeNode(DNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static void main(String[] args) {
        Solution04_LRUCacheWithRWLockAndExpiration lRUCache = new Solution04_LRUCacheWithRWLockAndExpiration(2);
        lRUCache.put(1, 1, 1000); // Adding with 1 second expiration
        lRUCache.put(2, 2, 2000); // Adding with 2 second expiration
        System.out.println(lRUCache.get(1));       // 返回 1
        System.out.println(lRUCache.get(2));       // 返回 2
        try {
            Thread.sleep(1500); // Sleep for 1.5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lRUCache.get(1));       // 返回 -1 (已过期)
        System.out.println(lRUCache.get(2));       // 返回 2
    }
}


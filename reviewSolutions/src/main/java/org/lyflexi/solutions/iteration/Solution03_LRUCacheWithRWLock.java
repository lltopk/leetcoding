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
* 所以最好使用synchronized/Lock来统一控制，使用读写锁ReentrantReadWrite优化性能:
* ReadWriteLock rwLock = = new ReentrantReadWriteLock();
* - Lock readLock = rwLock.readLock();
* - Lock writeLock = rwLock.writeLock();
*
* */
class Solution03_LRUCacheWithRWLock {
    static class DNode {
        int key;
        int value;
        DNode prev;
        DNode next;

        public DNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final HashMap<Integer, DNode> keyToDNodeCache;
    private final ReadWriteLock rwLock;

    private final Lock readLock;
    private final Lock writeLock;
    private final DNode dummy;

    //初始化成员变量
    public Solution03_LRUCacheWithRWLock(int capacity) {
        this.capacity = capacity;//final只赋值一次
        this.keyToDNodeCache = new HashMap<>(capacity);//final只赋值一次
        this.rwLock = new ReentrantReadWriteLock();//final只赋值一次
        this.readLock = rwLock.readLock();//final只赋值一次
        this.writeLock = rwLock.writeLock();//final只赋值一次
        this.dummy = new DNode(0, 0);//final只赋值一次
        this.dummy.next = this.dummy;
        this.dummy.prev = this.dummy;
    }

    //读的时候，虽然keyToDNodeCache的安全的，但无法保证链表操作moveToTail安全
    //处理方式：读写锁降级+DCL双减
    public int get(int key) {
        readLock.lock();
        try {
            DNode node = keyToDNodeCache.get(key);
            if (node != null) {
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
    public void put(int key, int value) {
        writeLock.lock();

        try {
            DNode node = keyToDNodeCache.get(key);

            if (node != null) {
                node.value = value;
                moveToTail(node);
                return;
            }

            if (keyToDNodeCache.size() >= capacity) {
                DNode toRemove = dummy.next;
                keyToDNodeCache.remove(toRemove.key);
                removeNode(toRemove);
            }

            node = new DNode(key, value);
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
        Solution03_LRUCacheWithRWLock lRUCache = new Solution03_LRUCacheWithRWLock(2);
        lRUCache.put(1, 1);
        lRUCache.put(2, 2);
        System.out.println(lRUCache.get(1));       // 返回 1
        lRUCache.put(3, 3);                        // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));       // 返回 -1 (未找到)
        lRUCache.put(4, 4);                        // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));       // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));       // 返回 3
        System.out.println(lRUCache.get(4));       // 返回 4
    }
}


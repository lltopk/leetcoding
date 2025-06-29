package org.lyflexi.solutions.iteration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ly
 * @Date: 2024/3/25 17:27
 */


class Solution02_LRUCacheWithExpiration {

    public static void main(String[] args) {
        Solution02_LRUCacheWithExpiration lruCache = new Solution02_LRUCacheWithExpiration(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);
        lruCache.get(2);
        lruCache.put(4, 4);
        lruCache.get(1);
        lruCache.get(2);
        lruCache.get(3);
    }

    public class DNode {
        public int key;
        public int value;
        public long timestamp;
        public DNode next;
        public DNode prev;

        public DNode(int key, int value, long timestamp) {
            this.key = key;
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    int capacity = 0;
    long expire = 10000; // 过期时间固定为10000毫秒
    Map<Integer, DNode> keyToDNodeCache = new HashMap<>();
    int curLen = 0;
    DNode dummy;

    public Solution02_LRUCacheWithExpiration(int capacity) {
        this.capacity = capacity;
        dummy = new DNode(0, 0, 0);
        dummy.next = dummy;
        dummy.prev = dummy;
    }

    public int get(int key) {
        DNode node = keyToDNodeCache.get(key);
        if (node != null) {
            //缓存过期情况
            if (System.currentTimeMillis() - node.timestamp > expire) {
                //仿照redis惰性删除
                keyToDNodeCache.remove(node.key);
                //别忘了删除链表节点
                removeNode(node);
                return -1; // 过期了，返回-1
            }

            //正常情况，缓存没过期
            moveToTail(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        DNode node = keyToDNodeCache.get(key);
        long currentTime = System.currentTimeMillis();
        if (node != null) {
            node.value = value;
            node.timestamp = currentTime;
            moveToTail(node);
            keyToDNodeCache.put(key, node);
            return;
        }

        if (curLen + 1 > capacity) {
            keyToDNodeCache.remove(dummy.next.key);
            removeNode(dummy.next);
            curLen--;
        }

        node = new DNode(key, value, currentTime);
        addToTail(node);
        keyToDNodeCache.put(key, node);
        curLen++;
    }

    private void moveToTail(DNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        addToTail(node);
    }

    private void addToTail(DNode node) {
        node.prev = dummy.prev;
        node.next = dummy;
        dummy.prev.next = node;
        dummy.prev = node;
    }

    //可以删除头节点，也支持删除过期的节点，比上一个版本的removeHead更强大
    private void removeNode(DNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}


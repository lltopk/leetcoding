package org.lyflexi.solutions.iteration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ly
 * @Date: 2024/3/22 12:08
 */

/*
* 146. LRU 缓存:
* 思路：对于链表的增删改查操作，时间复杂度分析如下
* - 单链表：增删改查由于都需要定位元素，也就是查询元素所以时间复杂度都是On
* - 双向链表：因为双向链表节点都具备前驱指针，所以删除操作时间复杂度可以降为01，所以本题的链表结构是双向链表
*
* 此外,题目要求：
* get的时间复杂度也为O1，所以必须使用hashmap，作为读取缓存
*
*
*
*
*
*
请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
实现 LRUCache 类：
LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
*
* 示例输入:
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]
解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4
* */
class Solution01_LRUCacheByDoubleLinked {
    public static void main(String[] args) {

        Solution01_LRUCacheByDoubleLinked solution01LruCacheByDoubleLinked = new Solution01_LRUCacheByDoubleLinked(2);
        solution01LruCacheByDoubleLinked.put(1,1);
        solution01LruCacheByDoubleLinked.put(2,2);
        solution01LruCacheByDoubleLinked.get(1);
        solution01LruCacheByDoubleLinked.put(3,3);

        solution01LruCacheByDoubleLinked.get(2);
        solution01LruCacheByDoubleLinked.put(4,4);
        solution01LruCacheByDoubleLinked.get(1);
        solution01LruCacheByDoubleLinked.get(2);
        solution01LruCacheByDoubleLinked.get(3);

    }
    public class DNode {
        public int key;
        public int value;
        public DNode next;
        public DNode prev;
        public DNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    //传入最大容量，用于判断链表是否满,
    int capacity = 0;
    //LRU是个缓存队列
    Map<Integer,DNode> keyToDNodeCache = new HashMap<>();
    //动态记录长度
    int curLen = 0;

    DNode dummy;

    public Solution01_LRUCacheByDoubleLinked(int capacity) {
        this.capacity = capacity;
        //创建虚拟头节点
        dummy = new DNode(0,0);
        dummy.next = dummy;
        dummy.prev = dummy;
    }

    //如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
    public int get(int key) {

        DNode node = keyToDNodeCache.get(key);
        if(node!=null){
            moveToTail(node);
            return node.value;
        }

        return -1;

    }

    public void put(int key, int value) {
        DNode node = keyToDNodeCache.get(key);

        //有缓存，则只更新缓存
        if(node!=null){
            node.value = value;
            moveToTail(node);//队列新增
            keyToDNodeCache.put(key,node);//更新缓存
            return;
        }

        //队列满，则删除头新增尾，并更新缓存
        if(curLen+1>capacity){
            keyToDNodeCache.remove(dummy.next.key);//先把头节点对应的缓存删除
            removeHead();//再删除队列头
            node = new DNode(key,value);
            addToTail(node);
            keyToDNodeCache.put(key,node);//新增缓存
            return;//容量最终不变
        }

        //正常情况
        node = new DNode(key,value);
        addToTail(node);
        keyToDNodeCache.put(key,node);
        curLen++;

    }

    private void moveToTail(DNode node){
        //删除当前节点，需要修改两个指针
        node.prev.next = node.next;
        node.next.prev = node.prev;
        addToTail(node);
    }

    private void addToTail(DNode node){
        //需要修改四个指针
        node.prev = dummy.prev;
        node.next = dummy;

        dummy.prev.next  = node;
        dummy.prev = node;
    }

    private void removeHead(){
        //修改两个指针即可
        dummy.next.next.prev = dummy;
        dummy.next = dummy.next.next;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

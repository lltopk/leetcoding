package com.hm.companyydtx.coding;


import com.hm.companyydtx.coding.structDef.ListNode;

/**
 * @Author: ly
 * @Date: 2024/2/15 19:13
 */

/*
622. 设计循环队列
使用链表去实现循环队列十分简单，因为链表天然就是无限增长的，无需在设计层面考虑循环*/
public class Q2_LoopQueueByLinkedList {
    ListNode front;
    ListNode rear;

    int capacity;

    int curSize = 0;//链表实现队列想要判满，只能是创建变量记录当前队列的大小

    public Q2_LoopQueueByLinkedList(int k) {
        this.capacity = k;
    }

    public boolean enQueue(int value) {

        if (isFull()){
            return false;
        }
        ListNode node = new ListNode(value);


        if (isEmpty()){
            front = node;
            rear = node;
        }else {
            rear.next = node;//链接上新的node
            rear = node;//还要移动rear指针到链表尾
        }

        curSize++;
        return true;

    }

    public boolean deQueue() {

        if (isEmpty()){
            return false;
        }

        front = front.next;
        curSize--;
        return true;
    }

    public int Front() {
        if (isEmpty()){
            return -1;
        }
        return front.val;

    }

    public int Rear() {
        if (isEmpty()){
            return -1;
        }
        return rear.val;

    }

    public boolean isEmpty() {

        return curSize==0;
    }

    public boolean isFull() {

        return curSize==capacity;

    }

}

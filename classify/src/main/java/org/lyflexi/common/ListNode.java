package org.lyflexi.common;

/**
 * @Author: ly
 * @Date: 2024/2/15 19:14
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(){

    }

    public ListNode(int value){
        this.val = value;
        next = null;
    }

    /**
     * 同时初始化next
     * @param val
     * @param next
     */
    public ListNode(int val, ListNode next) {
        this.val = val; this.next = next;
    }
}

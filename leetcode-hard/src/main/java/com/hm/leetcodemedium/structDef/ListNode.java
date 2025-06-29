package com.hm.leetcodemedium.structDef;

/**
 * @Author: ly
 * @Date: 2024/2/10 13:37
 */

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int value){
        this.val = value;
        this.next = null;
    }

    public ListNode(){
        this.next = null;
    }
}

package org.lyflexi.solutions.iteration;

import org.lyflexi.structDef.ListNode;

/**
 * @Author: ly
 * @Date: 2024/2/16 11:22
 */


/*
19. 删除链表的倒数第 N 个结点
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

示例 1：
输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]

示例 2：
输入：head = [1], n = 1
输出：[]

示例 3：
输入：head = [1,2], n = 1
输出：[1]
* */
public class Solution06_RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        //傀儡节点用于统一操作，将对head节点的操作统一进来
        ListNode dummy = new ListNode();
        dummy.next = head;
        //cur用于计数
        ListNode cur = head;

        int count = 0;
        while(cur!=null){
            count++;
            cur = cur.next;
        }


        cur = dummy;
        for (int i = 0; i < count-n; i++) {
            cur = cur.next;
        }

        cur.next = cur.next.next;

        return dummy.next;
    }
}

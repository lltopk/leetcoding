package org.lyflexi.solutions.iteration;

import org.lyflexi.structDef.ListNode;

/**
 * @Author: ly
 * @Date: 2024/3/23 23:11
 */
/*
* 25. K 个一组翻转链表
给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
*
* */
public class Solution11_ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;

        while (head != null) {
            ListNode tail = cur;
            // 每次while时都查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return dummy.next;
                }
            }
            ListNode gapHead = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            cur.next = head;
            tail.next = gapHead;
            cur = tail;
            head = tail.next;
        }

        return dummy.next;
    }

    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode cur = head;
        while (prev != tail) {
            ListNode curNext = cur.next;
            cur.next = prev;
            prev = cur;
            cur = curNext;
        }
        return new ListNode[]{tail, head};
    }
}

package org.lyflexi.solutions.iteration;

import org.lyflexi.structDef.ListNode;

/**
 * @Author: ly
 * @Date: 2024/3/23 21:21
 */

/*
* 92. 反转链表 II
给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。

* 示例：
输入：head = [1,2,3,4,5], left = 2, right = 4
输出：[1,4,3,2,5]
* */
public class Solution09_ReverseListⅡ {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode left = dummy;
        ListNode right = head;
        for (int i = 0; i < m - 1; i++) {
            left = left.next;
            right = right.next;
        }
        // 这一步left为要反转的第一个结点的前一个结点
        // 1 234 5 -> 1 324 5 -> 1 432 5
        for (int i = 0; i < n - m; i++) {
            ListNode temp = right.next;
            right.next = right.next.next;
            temp.next = left.next;
            left.next = temp;
        }
        return dummy.next;
    }
}

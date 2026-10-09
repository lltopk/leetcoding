package org.lyflexi;

import java.util.*;

/**
 * 25. K 个一组翻转链表
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * 
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * 
 * 示例 1：
 * 
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 
 * 示例 2：
 * 
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * 
 * 提示：
 * 
 * - 链表中的节点数目为 n
 * 
 * - 1 <= k <= n <= 5000
 * 
 * - 0 <= Node.val <= 1000
 * 
 * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 */

/**
 * 写法一
 */
public class Hot031_LC25_reverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        // 统计节点个数
        int n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            n++;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode lastTail = dummy; // 上一组翻转后的尾节点

        // k 个一组处理
        for (; n >= k; n -= k) {
            ListNode pre = null;
            ListNode cur = lastTail.next;
            for (int i = 0; i < k; i++) { // 同 92 题
                ListNode nxt = cur.next;
                cur.next = pre; // 每次循环只修改一个 next，方便大家理解
                pre = cur;
                cur = nxt;
            }

            // 请结合视频中的图理解
            // 翻转后：
            // pre 是当前组的头节点
            // cur 是下一组的起始节点
            // lastTail 是上一组的尾节点
            // lastTail.next 是当前组的尾节点
            ListNode tail = lastTail.next;
            tail.next = cur; // 当前组的尾节点指向下一组的起始节点
            lastTail.next = pre; // 上一组的尾节点指向当前组的头节点
            lastTail = tail;
        }

        return dummy.next;
    }
}

package org.lyflexi;

import java.util.*;

/**
 * 206. 反转链表
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 
 * 示例 1：
 * 
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 
 * 示例 2：
 * 
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 
 * 示例 3：
 * 
 * 输入：head = []
 * 输出：[]
 * 
 * 提示：
 * 
 * - 链表中节点的数目范围是 [0, 5000]
 * 
 * - -5000 <= Node.val <= 5000
 * 
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 */

/**
 * 方法一：递归（尾插法）
 * 
 * 递归递归，有递有归。
 * 
 * 我们先「递」到链表的末尾节点，作为新链表的头节点。然后在「归」的过程中，一个一个地把节点插在新链表的末尾。
 * 
 * 新链表的末尾节点在哪？就是当前节点的 $next$。具体实现如下。
 */
public class Hot023_LC206_reverseList {
    // 首先「递」到链表末尾，把末尾节点作为新链表的头节点 revHead
    // 然后在「归」的过程中，把经过的节点依次插在新链表的末尾（尾插法）
    public ListNode reverseList(ListNode head) {
        // 判断 head == null 是为了兼容一开始链表就是空的情况
        if (head == null || head.next == null) {
            return head; // 链表末尾，即下面的 revHead
        }
        ListNode revHead = reverseList(head.next); // 「递」到链表末尾，拿到新链表的头节点
        ListNode tail = head.next; // 在「归」的过程中，head.next 就是新链表的末尾
        tail.next = head; // 把 head 插在新链表的末尾
        head.next = null; // 如果不写这行，新链表的末尾两个节点成环，这俩节点互相指向对方
        return revHead;
    }
}

package org.lyflexi;

import java.util.*;

/**
 * 148. 排序链表
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 
 * 示例 1：
 * 
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 
 * 示例 2：
 * 
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 
 * 示例 3：
 * 
 * 输入：head = []
 * 输出：[]
 * 
 * 提示：
 * 
 * - 链表中节点的数目在范围 [0, 5 * 10^4] 内
 * 
 * - -10^5 <= Node.val <= 10^5
 * 
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 */

/**
 * 方法一：归并排序（分治）
 * 
 * 1. 找到链表的中间结点 $head_2$ 的前一个节点，并断开 $head_2$ 与其前一个节点的连接。这样我们就把原链表均分成了两段更短的链表。原理见【基础算法精讲 07】。
 * 2. 分治，递归调用 $sortList$，分别排序 $head$（只有前一半）和 $head_2$。
 * 3. 排序后，我们得到了两个有序链表，那么合并两个有序链表，得到排序后的链表，返回链表头节点。原理见 我的题解。
 */
public class Hot033_LC148_sortList {
    public ListNode sortList(ListNode head) {
        // 如果链表为空或者只有一个节点，无需排序
        if (head == null || head.next == null) {
            return head;
        }
        // 找到中间节点 head2，并断开 head2 与其前一个节点的连接
        // 比如 head=[4,2,1,3]，那么 middleNode 调用结束后 head=[4,2] head2=[1,3]
        ListNode head2 = middleNode(head);
        // 分治
        head = sortList(head);
        head2 = sortList(head2);
        // 合并
        return mergeTwoLists(head, head2);
    }

    // 876. 链表的中间结点（快慢指针）
    private ListNode middleNode(ListNode head) {
        ListNode pre = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            pre = slow; // 记录 slow 的前一个节点
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null; // 断开 slow 的前一个节点和 slow 的连接
        return slow;
    }

    // 21. 合并两个有序链表（双指针）
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(); // 用哨兵节点简化代码逻辑
        ListNode cur = dummy; // cur 指向新链表的末尾
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1; // 把 list1 加到新链表中
                list1 = list1.next;
            } else { // 注：相等的情况加哪个节点都是可以的
                cur.next = list2; // 把 list2 加到新链表中
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 != null ? list1 : list2; // 拼接剩余链表
        return dummy.next;
    }
}

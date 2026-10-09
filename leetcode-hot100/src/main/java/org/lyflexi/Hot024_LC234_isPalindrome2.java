package org.lyflexi;

import java.util.*;

/**
 * 234. 回文链表
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * 
 * 示例 1：
 * 
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 
 * 示例 2：
 * 
 * 输入：head = [1,2]
 * 输出：false
 * 
 * 提示：
 * 
 * - 链表中节点数目在范围[1, 10^5] 内
 * 
 * - 0 <= Node.val <= 9
 * 
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */

/**
 * 答疑
 * 
 * 问：为什么不能反转整个链表？
 * 
 * 答：注意我们还要从 $head$ 开始，从左到右遍历链表。如果反转整个链表，链表前半段的结构就被破坏了，无法从 $head$ 开始访问后续节点。
 * 
 * 下面的代码修改了输入的链表。复原输入的写法可以参考【Python3 写法二】。
 */
public class Hot024_LC234_isPalindrome2 {
    public boolean isPalindrome(ListNode head) {
        ListNode mid = middleNode(head);
        ListNode head2 = reverseList(mid);
        while (head2 != null) {
            if (head.val != head2.val) { // 不是回文链表
                return false;
            }
            head = head.next;
            head2 = head2.next;
        }
        return true;
    }

    // 876. 链表的中间结点
    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 206. 反转链表
    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
}

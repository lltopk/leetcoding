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
 * 在「归」的过程中，用另一个指针 $left$ 从左到右遍历链表，就可以比较对称位置的值是否相等了。
 * 注：递归做法效率比较低，更快的做法见方法二。
 */
public class Hot024_LC234_isPalindrome {
    private ListNode left;

    public boolean isPalindrome(ListNode head) {
        left = head;
        return isPal(head);
    }

    private boolean isPal(ListNode right) {
        // 「递」，先把 right 移到链表末尾
        if (right.next != null && !isPal(right.next)) {
            return false;
        }
        // 「归」的过程就是在从右到左遍历链表
        if (left.val != right.val) {
            return false;
        }
        left = left.next; // left 往右走
        return true; // 归，right 会往左走
    }
}

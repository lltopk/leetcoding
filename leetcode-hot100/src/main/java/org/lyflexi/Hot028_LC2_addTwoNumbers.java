package org.lyflexi;

import java.util.*;

/**
 * 2. 两数相加
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 
 * 示例 1：
 * 
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 
 * 示例 2：
 * 
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 
 * 示例 3：
 * 
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * 
 * 提示：
 * 
 * - 每个链表中的节点数在范围 [1, 100] 内
 * 
 * - 0 <= Node.val <= 9
 * 
 * - 题目数据保证列表表示的数字不含前导零
 */

/**
 * 写法一：创建新节点
 */
public class Hot028_LC2_addTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwo(l1, l2, 0);
    }

    // l1 和 l2 为当前遍历的节点，carry 为进位
    private ListNode addTwo(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) { // 递归边界
            return null;
        }

        int s = carry;
        if (l1 != null) {
            s += l1.val; // 累加进位与节点值
            l1 = l1.next;
        }
        if (l2 != null) {
            s += l2.val;
            l2 = l2.next;
        }

        // s 除以 10 的余数为当前节点值，商为进位
        return new ListNode(s % 10, addTwo(l1, l2, s / 10));
    }
}

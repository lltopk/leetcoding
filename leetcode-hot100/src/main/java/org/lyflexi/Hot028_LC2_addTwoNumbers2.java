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
 * 写法二：原地修改
 * 
 * 代码实现时，有一个简化代码的小技巧：如果递归中发现 $l_2$ 的长度比 $l_1$ 更长，那么可以交换 $l_1$ 和 $l_2$，保证 $l_1$ 不是空节点，从而简化代码逻辑。
 */
public class Hot028_LC2_addTwoNumbers2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwo(l1, l2, 0);
    }

    // l1 和 l2 为当前遍历的节点，carry 为进位
    private ListNode addTwo(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) { // 递归边界
            return carry != 0 ? new ListNode(carry) : null; // 如果进位了，就额外创建一个节点
        }
        if (l1 == null) { // 如果 l1 是空的，那么此时 l2 一定不是空节点
            l1 = l2;
            l2 = null; // 交换 l1 与 l2，保证 l1 非空，从而简化代码
        }
        int sum = carry + l1.val + (l2 != null ? l2.val : 0); // 节点值和进位加在一起
        l1.val = sum % 10; // 每个节点保存一个数位（直接修改原链表）
        l1.next = addTwo(l1.next, (l2 != null ? l2.next : null), sum / 10); // 进位
        return l1;
    }
}

package org.lyflexi;

import java.util.*;

/**
 * 21. 合并两个有序链表
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 
 * 示例 1：
 * 
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 
 * 示例 2：
 * 
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 
 * 示例 3：
 * 
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * 
 * 提示：
 * 
 * - 两个链表的节点数目范围是 [0, 50]
 * 
 * - -100 <= Node.val <= 100
 * 
 * - l1 和 l2 均按 非递减顺序 排列
 */

/**
 * 思路
 * 
 * 直接把 mergeTwoLists 当作递归函数：
 * 
 * - 递归边界：如果其中一个链表为空，直接返回另一个链表作为合并后的结果。
 * - 如果两个链表都不为空，则比较两个链表当前节点的值，选择较小的节点插在前面。
 *   - 如果 $list_1$ 的节点值更小，那么取出 $list_1$，递归调用 mergeTwoLists(list1.next, list2)，拿到递归返回的链表，把 $list_1$ 插在前面。
 *   - 如果 $list_2$ 的节点值更小，那么取出 $list_2$，递归调用 mergeTwoLists(list1, list2.next)，拿到递归返回的链表，把 $list_2$ 插在前面。
 */
public class Hot027_LC21_mergeTwoLists2 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2; // 注：如果都为空则返回空
        if (list2 == null) return list1;
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }
        list2.next = mergeTwoLists(list1, list2.next);
        return list2;
    }
}

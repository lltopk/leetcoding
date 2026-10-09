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
 * 创建一个哨兵节点，作为合并后的新链表头节点的前一个节点。这样可以避免单独处理头节点，也无需特判链表为空的情况，从而简化代码。
 * 
 * 比较 $list_1$ 和 $list_2$ 的节点值，如果 $list_1$ 的节点值小，则把 $list_1$ 加到新链表的末尾，然后把 $list_1$ 替换成它的下一个节点。如果 $list_2$ 的节点值小则同理。如果两个节点值一样，那么把谁加到新链表的末尾都是一样的，不妨规定把 $list_2$ 加到新链表末尾。
 * 
 * 重复上述过程，直到其中一个链表为空。
 * 
 * 循环结束后，其中一个链表可能还有剩余的节点，将剩余部分加到新链表的末尾。
 * 
 * 最后，返回新链表的头节点，即哨兵节点的下一个节点。
 */
public class Hot027_LC21_mergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
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

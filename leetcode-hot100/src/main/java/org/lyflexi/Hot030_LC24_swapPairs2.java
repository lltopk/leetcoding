package org.lyflexi;

import java.util.*;

/**
 * 24. 两两交换链表中的节点
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * 
 * 示例 1：
 * 
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 
 * 示例 2：
 * 
 * 输入：head = []
 * 输出：[]
 * 
 * 示例 3：
 * 
 * 输入：head = [1]
 * 输出：[1]
 * 
 * 提示：
 * 
 * - 链表中节点的数目在范围 [0, 100] 内
 * 
 * - 0 <= Node.val <= 100
 */

/**
 * 思路
 * 
 * 和方法一类似。这里直接用 swapPairs 当作递归函数：
 * 
 * - 递归边界：如果 $head$ 或者 $head.next$ 为空，说明剩余节点不足两个，无需交换，返回 $head$。
 * - 先交换以 $node_3$ 为头节点的链表，即递归调用 swapPairs(node3)。
 * - 把 $node_1$ 指向递归返回的链表头。
 * - 把 $node_2$ 指向 $node_1$。
 * - 返回 $node_2$，作为交换后的链表头节点。
 */
public class Hot030_LC24_swapPairs2 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node1 = head;
        ListNode node2 = head.next;
        ListNode node3 = node2.next;

        node1.next = swapPairs(node3); // 1 指向递归返回的链表头
        node2.next = node1; // 2 指向 1

        return node2; // 返回交换后的链表头节点
    }
}

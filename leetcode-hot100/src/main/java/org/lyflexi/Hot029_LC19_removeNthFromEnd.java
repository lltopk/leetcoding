package org.lyflexi;

import java.util.*;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 
 * 示例 1：
 * 
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 
 * 示例 2：
 * 
 * 输入：head = [1], n = 1
 * 输出：[]
 * 
 * 示例 3：
 * 
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * 
 * 提示：
 * 
 * - 链表中结点的数目为 sz
 * 
 * - 1 <= sz <= 30
 * 
 * - 0 <= Node.val <= 100
 * 
 * - 1 <= n <= sz
 * 
 * 进阶：你能尝试使用一趟扫描实现吗？
 */

/**
 * 答疑
 * 
 * 问：前后指针的做法为什么算作「一次遍历」？链表节点不是会被遍历两次吗？这个算法的优点是什么？
 * 
 * 答：链表节点是会被遍历两次。其实我觉得这题本质上想考察的是，在不知道链表长度的前提下，设计一个算法，在「到达链表末尾的瞬间」就能知道倒数第 $n$ 个节点。这个算法的优点是，当 $n$ 比较小，且链表节点分配具有一定局部性时，前后指针的做法 cache miss 更少（相比一个指针跑两趟的算法）。
 * 
 * 问：一般在做链表题时，什么时候要写 while node，什么时候要写 while node.next？
 * 
 * 答：如果要遍历到最后一个节点，需要写 while node；如果要遍历到倒数第二个节点，需要写 while node.next。
 */
public class Hot029_LC19_removeNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 由于可能会删除链表头部，用哨兵节点简化代码
        ListNode dummy = new ListNode(0, head);
        ListNode left = dummy;
        ListNode right = dummy;
        while (n-- > 0) {
            right = right.next; // 右指针先向右走 n 步
        }
        while (right.next != null) {
            left = left.next;
            right = right.next; // 左右指针一起走
        }
        left.next = left.next.next; // 左指针的下一个节点就是倒数第 n 个节点
        return dummy.next;
    }
}

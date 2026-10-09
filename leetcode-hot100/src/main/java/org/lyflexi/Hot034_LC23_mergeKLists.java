package org.lyflexi;

import java.util.*;

/**
 * 23. 合并 K 个升序链表
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 
 * 示例 1：
 * 
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 
 * 示例 2：
 * 
 * 输入：lists = []
 * 输出：[]
 * 
 * 示例 3：
 * 
 * 输入：lists = [[]]
 * 输出：[]
 * 
 * 提示：
 * 
 * - k == lists.length
 * 
 * - 0 <= k <= 10^4
 * 
 * - 0 <= lists[i].length <= 500
 * 
 * - -10^4 <= lists[i][j] <= 10^4
 * 
 * - lists[i] 按 升序 排列
 * 
 * - lists[i].length 的总和不超过 10^4
 */

/**
 * 方法一：最小堆
 * 
 * 合并后的第一个节点 $first$，一定是某个链表的头节点（因为链表已按升序排列）。
 * 
 * 合并后的第二个节点，可能是某个链表的头节点，也可能是 $first$ 的下一个节点。
 * 
 * 例如有三个链表 1->2->5, 3->4->6, 4->5->6，找到第一个节点 1 之后，第二个节点不是另一个链表的头节点，而是节点 1 的下一个节点 2。
 * 
 * 按照这个过程继续思考，每当我们找到一个节点值最小的节点 $x$，就把节点 $x.next$ 加入「可能是最小节点」的集合中。
 * 
 * 因此，我们需要一个数据结构，它支持：
 * 
 * - 从数据结构中找到并移除最小节点。
 * - 插入节点。
 * 
 * 这可以用最小堆实现。初始把所有链表的头节点入堆，然后不断弹出堆中最小节点 $x$，如果 $x.next$ 不为空就加入堆中。循环直到堆为空。把弹出的节点按顺序拼接起来，就得到了答案。
 * 
 * 实现时，可以用哨兵节点简化代码，具体请看【基础算法精讲 06】。
 */
public class Hot034_LC23_mergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode head : lists) {
            if (head != null) {
                pq.offer(head); // 把所有非空链表的头节点入堆
            }
        }

        ListNode dummy = new ListNode(); // 哨兵节点，作为合并后链表头节点的前一个节点
        ListNode cur = dummy;
        while (!pq.isEmpty()) { // 循环直到堆为空
            ListNode node = pq.poll(); // 剩余节点中的最小节点
            if (node.next != null) { // 下一个节点不为空
                pq.offer(node.next); // 下一个节点有可能是最小节点，入堆
            }
            cur.next = node; // 把 node 添加到新链表的末尾
            cur = cur.next; // 准备合并下一个节点
        }
        return dummy.next; // 哨兵节点的下一个节点就是新链表的头节点
    }
}

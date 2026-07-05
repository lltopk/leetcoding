package org.lyflexi.solutions.baseAlgorithm.utils.linked.merge;

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
 *
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
 * 示例 2：
 *
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：lists = [[]]
 * 输出：[]
 *
 *
 * 提示：
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,275,452/2M
 * 通过率
 * 63.9%
 */

import org.lyflexi.common.ListNode;

import java.util.PriorityQueue;

/**
 * K个单调序列  +  优先级队列
 */
public class LC23_mergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode head : lists) {
            if (head != null) {
                pq.offer(head); // 把所有非空链表的头节点入堆
            }
        }
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while(!pq.isEmpty()){
            ListNode peek = pq.poll();
            if(peek.next != null){
                pq.offer(peek.next);
            }
            cur.next = peek;
            cur = cur.next;
        }
        return dummy.next;
    }
}

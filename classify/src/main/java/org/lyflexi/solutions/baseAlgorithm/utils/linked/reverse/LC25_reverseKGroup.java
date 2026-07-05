package org.lyflexi.solutions.baseAlgorithm.utils.linked.reverse;

import org.lyflexi.common.ListNode;

/**
 * 25. K 个一组翻转链表
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 示例 2：
 *
 *
 *
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 *
 * 提示：
 * 链表中的节点数目为 n
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 *
 *
 * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 */



public class LC25_reverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        int n = 0;
        ListNode cur = head;
        while(cur != null){
            n++;
            cur = cur.next;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode p0 = dummy;
        //反转n/k次
        for(int i = 0; i<n/k; i++){
            ListNode pre = null;
            cur = p0.next;
            for(int j = 0; j < k ;j++){
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }

            p0.next.next = cur;
            ListNode nextP0 =  p0.next;
            p0.next = pre;
            p0 = nextP0;
        }
        return dummy.next;
    }
}

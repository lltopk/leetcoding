package org.lyflexi.solutions.baseAlgorithm.utils.linked.reverse;
import org.lyflexi.common.ListNode;

/**
 * 24. 两两交换链表中的节点
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 *
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1]
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,424,206/1.9M
 * 通过率
 * 75.0%
 */

/**
 * 先做25. K 个一组翻转链表
 *
 * 24. 两两交换链表中的节点 = return fn25(head, 2)
 */
public class LC24_swapPairs {
    public ListNode swapPairs(ListNode head) {
        return reverseKGroup(head, 2);
    }
    private ListNode reverseKGroup(ListNode head, int k) {
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

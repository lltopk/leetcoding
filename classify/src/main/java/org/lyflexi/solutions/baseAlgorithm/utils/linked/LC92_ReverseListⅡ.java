package org.lyflexi.solutions.baseAlgorithm.utils.linked;

import org.lyflexi.common.ListNode;

/**
 * 92. 反转链表 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * 示例 2：
 *
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *
 *
 * 提示：
 *
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 *
 * 进阶： 你可以使用一趟扫描完成反转吗？
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 753,593/1.3M
 * 通过率
 * 58.3%
 */


public class LC92_ReverseListⅡ {
    /**
     定义p0为left的上个节点
     中间链表反转后, p0链向pre, p0.next链向cur即可
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {

        //由于当left==1的时候, 没有p0, 姑我们定义dummy节点, 类似于前缀和的0
        ListNode dummy = new ListNode(0, head);
        ListNode p0 = dummy;
        //循环left-1次
        for(int i = 0; i<left -1 ;i++){
            p0 = p0.next;
        }

        //接下来是反转中间的链表
        ListNode pre = null;
        ListNode cur = p0.next;
        for(int i = 0; i < right - left + 1 ;i++){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        p0.next.next = cur;
        p0.next = pre;

        //这里不能返回head, 因为当left==1的时候, 头节点被反转了
        return dummy.next;
    }
}

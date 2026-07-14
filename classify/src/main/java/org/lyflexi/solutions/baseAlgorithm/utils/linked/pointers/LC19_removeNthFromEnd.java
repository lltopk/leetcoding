package org.lyflexi.solutions.baseAlgorithm.utils.linked.pointers;

import org.lyflexi.common.ListNode;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 */

/**
 * 蒙多拆二塔, 防守方到高地
 */
public class LC19_removeNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 由于可能会删除链表头部，用哨兵节点简化代码
        ListNode dummy = new ListNode(0, head);
        ListNode p0 = dummy;
        ListNode p2 = head;

        //试填法， 算固定间距
        for(int i = 0; i< n; i++){
            p2 = p2.next;
        }

        //同步匀速行驶
        while(p2 != null){
            p0 = p0.next;
            p2 = p2.next;
        }

        p0.next = p0.next.next;
        return dummy.next;
    }
}

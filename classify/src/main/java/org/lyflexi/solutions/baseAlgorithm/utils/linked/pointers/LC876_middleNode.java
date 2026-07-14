package org.lyflexi.solutions.baseAlgorithm.utils.linked.pointers;

/**
 * 876. 链表的中间结点
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
 *
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[3,4,5]
 * 解释：链表只有一个中间结点，值为 3 。
 * 示例 2：
 *
 *
 * 输入：head = [1,2,3,4,5,6]
 * 输出：[4,5,6]
 * 解释：该链表有两个中间结点，值分别为 3 和 4 ，返回第二个结点。
 *
 *
 * 提示：
 *
 * 链表的结点数范围是 [1, 100]
 * 1 <= Node.val <= 100
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 564,480/774.8K
 * 通过率
 * 72.9%
 */

import org.lyflexi.common.ListNode;

/**
 * 蒙多拆二塔, 防守方到高地
 */
public class LC876_middleNode {
    public ListNode middleNode(ListNode head) {
        ListNode p1 = head, p2 = head;
        while(p2 != null && p2.next!=null){
            p1 = p1.next;
            p2 = p2.next.next;
        }

        return p1;
    }
}

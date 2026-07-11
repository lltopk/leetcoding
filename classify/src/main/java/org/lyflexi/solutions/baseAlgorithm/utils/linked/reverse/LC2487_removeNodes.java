package org.lyflexi.solutions.baseAlgorithm.utils.linked.reverse;
import org.lyflexi.common.ListNode;
/**
 * 2487. 从链表中移除节点
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个链表的头节点 head 。
 *
 * 移除每个右侧有一个更大数值的节点。
 *
 * 返回修改后链表的头节点 head 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [5,2,13,3,8]
 * 输出：[13,8]
 * 解释：需要移除的节点是 5 ，2 和 3 。
 * - 节点 13 在节点 5 右侧。
 * - 节点 13 在节点 2 右侧。
 * - 节点 8 在节点 3 右侧。
 * 示例 2：
 *
 * 输入：head = [1,1,1,1]
 * 输出：[1,1,1,1]
 * 解释：每个节点的值都是 1 ，所以没有需要移除的节点。
 *
 *
 * 提示：
 *
 * 给定列表中的节点数目在范围 [1, 105] 内
 * 1 <= Node.val <= 105
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 51,207/67.9K
 * 通过率
 * 75.5%
 */


/**
 * 反转链表， 然后删除节点， 最后再反转链表
 */
public class LC2487_removeNodes {
    /**
     * 通过 206. 反转链表，我们可以从反转后的链表头开始，
     * 然后像 83. 删除排序链表中的重复元素 那样，删除比当前节点值小的元素。
     * 最后再次反转链表，即为答案。
     * @param head
     * @return
     */
    public ListNode removeNodes(ListNode head) {
        head = reverseList(head);
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val > cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return reverseList(head);
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
}

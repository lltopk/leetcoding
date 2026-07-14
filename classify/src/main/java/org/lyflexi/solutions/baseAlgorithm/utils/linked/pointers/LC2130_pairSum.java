package org.lyflexi.solutions.baseAlgorithm.utils.linked.pointers;

/**
 * 2130. 链表最大孪生和
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 在一个大小为 n 且 n 为 偶数 的链表中，对于 0 <= i <= (n / 2) - 1 的 i ，第 i 个节点（下标从 0 开始）的孪生节点为第 (n-1-i) 个节点 。
 *
 * 比方说，n = 4 那么节点 0 是节点 3 的孪生节点，节点 1 是节点 2 的孪生节点。这是长度为 n = 4 的链表中所有的孪生节点。
 * 孪生和 定义为一个节点和它孪生节点两者值之和。
 *
 * 给你一个长度为偶数的链表的头节点 head ，请你返回链表的 最大孪生和 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [5,4,2,1]
 * 输出：6
 * 解释：
 * 节点 0 和节点 1 分别是节点 3 和 2 的孪生节点。孪生和都为 6 。
 * 链表中没有其他孪生节点。
 * 所以，链表的最大孪生和是 6 。
 * 示例 2：
 *
 *
 *
 * 输入：head = [4,2,2,3]
 * 输出：7
 * 解释：
 * 链表中的孪生节点为：
 * - 节点 0 是节点 3 的孪生节点，孪生和为 4 + 3 = 7 。
 * - 节点 1 是节点 2 的孪生节点，孪生和为 2 + 2 = 4 。
 * 所以，最大孪生和为 max(7, 4) = 7 。
 * 示例 3：
 *
 *
 *
 * 输入：head = [1,100000]
 * 输出：100001
 * 解释：
 * 链表中只有一对孪生节点，孪生和为 1 + 100000 = 100001 。
 *
 *
 * 提示：
 *
 * 链表的节点数目是 [2, 105] 中的 偶数 。
 * 1 <= Node.val <= 105
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 51,306/63.3K
 * 通过率
 * 81.1%
 */

import org.lyflexi.common.ListNode;

/**
 * 见: 234. 回文链表
 */
public class LC2130_pairSum {
    public int pairSum(ListNode head) {
        ListNode mid = middleNode(head);
        //注意, 反转后半个列表之后, mid并没有与前半个链表断开
        ListNode head2 = reverseList(mid);
        //原链表长度为偶数的时候1->2->3->4->5->6, mid是中间偏右, 反转结果1->2->3->4<-5<-6, 此时head2的长度小于head
        //因此下面要对head2判空
        int ret = 0;
        while(head2!=null){
            ret = Math.max(ret, head.val + head2.val);
            head = head.next;
            head2 = head2.next;
        }
        return ret;
    }
    /**
     LC876. 链表的中间结点
     */
    private ListNode middleNode(ListNode head) {
        ListNode p1 = head, p2 = head;
        while(p2 != null && p2.next!=null){
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return p1;
    }

    /**
     LC206.反转列表
     */
    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while(head!=null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}

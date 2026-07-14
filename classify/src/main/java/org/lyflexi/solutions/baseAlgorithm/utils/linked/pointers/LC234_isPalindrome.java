package org.lyflexi.solutions.baseAlgorithm.utils.linked.pointers;

/**
 * 234. 回文链表
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：false
 *
 *
 * 提示：
 *
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 *
 *
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,324,023/2.3M
 * 通过率
 * 58.4%
 */

import org.lyflexi.common.ListNode;

/**
 * 综合题
 */
public class LC234_isPalindrome {
    public boolean isPalindrome(ListNode head) {
        ListNode mid = middleNode(head);
        //注意, 反转后半个列表之后, mid并没有与前半个链表断开
        ListNode head2 = reverseList(mid);
        //当原链表长度为奇数的时候1->2->3->4->5, 反转结果1->2->3<-4<-5
        //当原链表长度为偶数的时候1->2->3->4->5->6, mid是中间偏右, 反转结果1->2->3->4<-5<-6, 此时head2的长度小于head
        //因此下面要对head2判空
        while(head2!=null){
            if(head.val != head2.val){
                return false;
            }
            head = head.next;
            head2 = head2.next;
        }
        return true;
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
     反转列表
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

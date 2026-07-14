package org.lyflexi.solutions.baseAlgorithm.utils.linked.pointers;

import org.lyflexi.common.ListNode;

/**
 * 143. 重排链表
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [1,2,3,4]
 * 输出：[1,4,2,3]
 * 示例 2：
 *
 *
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[1,5,2,4,3]
 *
 *
 * 提示：
 *
 * 链表的长度范围为 [1, 5 * 104]
 * 1 <= node.val <= 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 438,432/645.1K
 * 通过率
 * 68.0%
 */


/**
 * 见: 234. 回文链表
 */
public class LC143_reorderList {
    public void reorderList(ListNode head) {
        ListNode mid = middleNode(head);
        //注意, 反转后半个列表之后, mid并没有与前半个链表断开
        ListNode head2 = reverseList(mid);
        //当原链表长度为奇数的时候1->2->3->4->5, 反转结果1->2->3<-4<-5
        //当原链表长度为偶数的时候1->2->3->4->5->6, mid是中间偏右, 反转结果1->2->3->4<-5<-6, 此时head2的长度小于head

        //head2.next !=null, 这道题不要最后的那一次计算(head2!=null), 否则当原链长度为偶数时候导致mid自身成环: head2(4).next = next1(4);
        while(head2.next !=null){
            ListNode next1 = head.next;
            ListNode next2 = head2.next;
            //当前轮次
            head.next = head2;
            head2.next = next1;

            head = next1;
            head2 = next2;
        }
        //代码修改了链表内部节点的顺序，评测机会在调用完 reorderList(head) 后，再次遍历链表 head，判断修改后的链表是否等于答案。
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

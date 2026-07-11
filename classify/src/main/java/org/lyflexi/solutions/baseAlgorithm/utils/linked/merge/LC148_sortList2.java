package org.lyflexi.solutions.baseAlgorithm.utils.linked.merge;

import org.lyflexi.common.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 148. 排序链表
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 *
 *
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 *
 *
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 964,420/1.4M
 * 通过率
 * 67.7%
 */


/**
 * 归并
 */
public class LC148_sortList2 {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        // 找到中间节点 head2，并断开 head2 与其前一个节点的连接
        // 比如 head=[4,2,1,3]，那么 middleNode 调用结束后 head=[4,2] head2=[1,3]
        ListNode head2 = findMid(head);
        head = sortList(head);
        head2 = sortList(head2);
        return mergeTwoSorted(head, head2);
    }

    private ListNode findMid(ListNode head){
        ListNode p1 = head, p2 = head;
        //最后要将链表断开
        ListNode pre = new ListNode(0, head);
        while(p2!= null && p2.next!=null){
            p1 = p1.next;
            pre = pre.next;
            p2 = p2.next.next;
        }
        pre.next = null;//断开
        return p1;
    }

    private ListNode mergeTwoSorted(ListNode h1, ListNode h2){
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while(h1!= null && h2!=null){
            if(h1.val < h2.val){
                cur.next = h1;//修改新链表结构
                h1 = h1.next;
            }else{
                cur.next = h2;//修改新链表结构
                h2 = h2.next;
            }
            cur = cur.next;
        }

        cur.next = h1==null? h2: h1;
        return dummy.next;
    }
}

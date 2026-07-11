package org.lyflexi.solutions.baseAlgorithm.utils.linked.merge;

/**
 * 2816. 翻倍以链表形式表示的数字
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个 非空 链表的头节点 head ，表示一个不含前导零的非负数整数。
 *
 * 将链表 翻倍 后，返回头节点 head 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,8,9]
 * 输出：[3,7,8]
 * 解释：上图中给出的链表，表示数字 189 。返回的链表表示数字 189 * 2 = 378 。
 * 示例 2：
 *
 *
 * 输入：head = [9,9,9]
 * 输出：[1,9,9,8]
 * 解释：上图中给出的链表，表示数字 999 。返回的链表表示数字 999 * 2 = 1998 。
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [1, 104] 内
 * 0 <= Node.val <= 9
 * 生成的输入满足：链表表示一个不含前导零的数字，除了数字 0 本身。
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 19,788/29.8K
 * 通过率
 * 66.4%
 */

import org.lyflexi.common.ListNode;

/**
 * 合并链表题单， 见LC02_addTwoNumbers， LC445_addTwoNumbers
 */
public class LC2816_doubleIt {
    public ListNode doubleIt(ListNode head) {
        head = reverse(head);
        ListNode ret = addTwoNumbers0(head, head);
        return reverse(ret);
    }

    //LC206. 反转链表
    private ListNode reverse(ListNode head){
        ListNode pre = null;
        while(head!=null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    //LC2. 两数相加
    private ListNode addTwoNumbers0(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0, l1), cur = dummy;
        ListNode p1 = l1;
        ListNode p2 = l2;
        int carry = 0;
        while(p1!=null || p2!=null){
            int newV = 0;
            if(p1 == null){
                newV = p2.val+carry;
            }else if(p2 == null){
                newV = p1.val+carry;
            }else{
                newV = p1.val+p2.val+carry;
            }
            carry = 0;//这里必须置0，只有置0才能够证明当前的carry是作用于当前轮数的， 而不是历史的carry
            if(newV >= 10){
                newV -=  10;
                carry = 1;
            }
            cur.next = new ListNode(newV);
            cur = cur.next;
            if(p1!=null){
                p1 = p1.next;
            }
            if(p2!=null){
                p2 = p2.next;
            }
        }

        if(carry > 0){
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }
}

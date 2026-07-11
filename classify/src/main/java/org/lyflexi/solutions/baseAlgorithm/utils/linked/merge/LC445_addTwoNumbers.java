package org.lyflexi.solutions.baseAlgorithm.utils.linked.merge;

/**
 * 445. 两数相加 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 *
 *
 * 示例1：
 *
 *
 *
 * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 * 输出：[7,8,0,7]
 * 示例2：
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[8,0,7]
 * 示例3：
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 *
 * 提示：
 *
 * 链表的长度范围为 [1, 100]
 * 0 <= node.val <= 9
 * 输入数据保证链表代表的数字无前导 0
 *
 *
 * 进阶：如果输入链表不能翻转该如何解决？
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 193,899/312.7K
 * 通过率
 * 62.0%
 */

import org.lyflexi.common.ListNode;

/**
 * 合并链表题单， 见LC02_addTwoNumbers， LC445_addTwoNumbers
 */
public class LC445_addTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        ListNode ret = addTwoNumbers0(l1, l2);
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

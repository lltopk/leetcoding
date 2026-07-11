package org.lyflexi.solutions.baseAlgorithm.utils.linked.merge;

/**
 * 2. 两数相加
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 *
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 *
 * 提示：
 *
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 2,888,331/6.1M
 * 通过率
 * 47.0%
 */

import org.lyflexi.common.ListNode;

/**
 * 合并链表题单
 */
public class LC02_addTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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

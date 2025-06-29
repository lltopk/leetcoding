package org.lyflexi.solutions.iteration;

import org.lyflexi.structDef.ListNode;

/**
 * @Author: ly
 * @Date: 2024/2/16 16:33
 */

/*
* 21. 合并两个有序链表
将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
* */
public class Solution07_MergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummy = new ListNode(-1);

        ListNode cur = dummy;

        while(list1!=null&&list2!=null){
            ListNode node;
            if (list1.val>list2.val){
                node = new ListNode(list2.val);
                list2 = list2.next;

            }else{
                node = new ListNode(list1.val);
                list1 = list1.next;
            }
            cur.next = node;
            cur = cur.next;
        }

        if (list1==null&&list2!=null){
            cur.next = list2;
        }

        if (list2==null&&list1!=null){
            cur.next = list1;
        }

        return dummy.next;

    }
}

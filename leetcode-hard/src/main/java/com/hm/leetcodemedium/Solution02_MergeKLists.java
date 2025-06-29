package com.hm.leetcodemedium;


import com.hm.leetcodemedium.structDef.ListNode;

import java.util.PriorityQueue;

/**
 * @Author: ly
 * @Date: 2024/2/16 17:21
 */

/*
* 23. 合并 K 个升序链表,topk算法的应用
给你一个链表数组，每个链表都已经按升序排列。请你将所有链表合并到一个升序链表中，返回合并后的链表。
*
*
*
* */
public class Solution02_MergeKLists {


    /*题目很显然需要用到topk算法，也即是优先队列PriorityQueue*/

    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;

        if (n==1){
            return lists[0];
        }

        if (n>=2){

            PriorityQueue<ListNode> queue = new PriorityQueue<>(
                    (node1,node2)->{return node1.val-node2.val;}//小顶堆
            );


            for (int i = 0; i < n; i++) {
                ListNode node = lists[i];
                if (node!=null){//防止优先队列PriorityQueue底层排序的时候出现空指针异常
                    queue.offer(lists[i]);
                }

            }


            ListNode dummy = new ListNode();
            ListNode cur = dummy;

            while(!queue.isEmpty()){
                ListNode poll = queue.poll();
                cur.next = poll;
                cur = cur.next;
                if (poll.next!=null){
                    queue.offer(poll.next);
                }


            }

            return dummy.next;
        }

        return null;


    }



//    我们可以想到一种最朴素的方法：循环调用mergeTwoLists，返回值即是合并后的结果
/*    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;

        if (n==1){
            return lists[0];
        }

        if (n>=2){
            ListNode node = mergeTwoLists(lists[0], lists[1]);

            for (int i = 2; i < n; i++) {
                node = mergeTwoLists(node, lists[i]);
            }
            return node;
        }

        return null;
    }

    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {

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

    }*/
}

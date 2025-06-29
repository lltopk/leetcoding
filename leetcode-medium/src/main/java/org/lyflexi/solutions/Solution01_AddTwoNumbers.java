package org.lyflexi.solutions;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/2/3 21:40
 */

/*2.两数相加
* 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
请你将两个数相加，并以相同形式返回一个表示和的链表。
你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
*
*
*
* 示例1：输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.
*
* 示例 2：输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]
*
* */
public class Solution01_AddTwoNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums1 = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[] nums2 = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        int m = nums1.length;
        ListNode pre1 = new ListNode();
        ListNode cur1 = pre1;
        for (int i = 0; i < m; i++) {
            ListNode listNode = new ListNode(nums1[i]);
            cur1.next = listNode;
            cur1 = cur1.next;
        }

        int n = nums2.length;
        ListNode pre2 = new ListNode();
        ListNode cur2 = pre2;
        for (int i = 0; i < n; i++) {
            ListNode listNode = new ListNode(nums2[i]);
            cur2.next = listNode;
            cur2 = cur2.next;
        }

        ListNode listNode = addTwoNumbers(pre1.next, pre2.next);
        printNum(listNode);

    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //初始化假头节点，便于后续统一节点操作
        ListNode dummy = new ListNode();
        //固定ansNode的头节点不动，派出手下去建立索引
        ListNode cur = dummy;

        int carry = 0;

        while(l1!=null&&l2!=null){

            int sum = l1.val+l2.val+carry;

            carry = sum/10;

            int value = sum%10;

            ListNode node = new ListNode(value);

            cur.next = node;
            cur = cur.next;

            l1 = l1.next;
            l2 = l2.next;
        }

        if (l1==null&&l2!=null){
            while(l2!=null){
                int sum = l2.val+carry;

                carry = sum/10;

                int value = sum%10;

                ListNode node = new ListNode(value);

                cur.next = node;
                cur = cur.next;

                l2 = l2.next;
            }

        }

        if (l2==null&&l1!=null){
            while(l1!=null){
                int sum = l1.val+carry;

                carry = sum/10;

                int value = sum%10;

                ListNode node = new ListNode(value);

                cur.next = node;
                cur = cur.next;

                l1 = l1.next;
            }

        }

        if (carry==1){
            cur.next = new ListNode(1);
        }

        return dummy.next;
    }




    private static void printNum(ListNode trueList){
        while (trueList!=null){
            System.out.println(trueList.val);
            trueList = trueList.next;
        }
    }
}

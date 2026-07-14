package org.lyflexi.solutions.baseAlgorithm.utils.linked.pointers;

import org.lyflexi.common.ListNode;

/**
 * 61. 旋转链表
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * 示例 2：
 *
 *
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 522,556/1.3M
 * 通过率
 * 41.6%
 */
public class LC61_rotateRight {
    //1 2 3 4 5 ,k =2, 旋转K次, 等价于首尾相接然后断开(3->4)即正数第n - k个节点断开即可
    //4 5 1 2 3
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null){
            return null;
        }
        //因为下面不能让tail遍历到null, 但这样长度就少1了, 所以初始为1
        int len = 1;
        //先收尾相接
        ListNode tail = head;
        while(tail.next!=null){
            len++;
            tail = tail.next;
        }

        k %= len;
        tail.next = head;
        ListNode cur = head;
        for(int i = 0; i< len-k - 1; i++){
            cur = cur.next;
        }
        //求新头
        ListNode newHead = cur.next;
        //断开第n - k个节点
        cur.next = null;
        return newHead;
    }
}

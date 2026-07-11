package org.lyflexi.solutions.baseAlgorithm.utils.linked;

/**
 * 82. 删除排序链表中的重复元素 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 * 示例 2：
 *
 *
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 *
 *
 * 提示：
 *
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 625,603/1.1M
 * 通过率
 * 55.4%
 */


import org.lyflexi.common.ListNode;

/**
 * 删除链表节点
 */
public class LC82_deleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while(cur != null){
            while(cur.next!= null && cur.val == cur.next.val){
                cur = cur.next;
            }

            if(pre.next == cur){//上面没有进入while循环
                pre = pre.next;
            }else{//整段删除
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}

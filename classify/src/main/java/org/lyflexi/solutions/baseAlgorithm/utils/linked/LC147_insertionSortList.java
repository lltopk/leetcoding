package org.lyflexi.solutions.baseAlgorithm.utils.linked;

import org.lyflexi.common.ListNode;

/**
 * 147. 对链表进行插入排序
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回 排序后链表的头 。
 *
 * 插入排序 算法的步骤:
 *
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * 下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。
 *
 * 对链表进行插入排序。
 *
 *
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入: head = [4,2,1,3]
 * 输出: [1,2,3,4]
 * 示例 2：
 *
 *
 *
 * 输入: head = [-1,5,3,4,0]
 * 输出: [-1,0,3,4,5]
 *
 *
 * 提示：
 *
 * 列表中的节点数在 [1, 5000]范围内
 * -5000 <= Node.val <= 5000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 200,926/284.5K
 * 通过率
 * 70.6%
 */
public class LC147_insertionSortList {
    public ListNode insertionSortList(ListNode head) {
        //因为插入排序有可能改变头节点， 所以需要定义dummy统一代码
        ListNode dummy = new ListNode(0, head);
        ListNode cur = head.next, last = head;
        while(cur != null){
            if(last.val <= cur.val){
                last = last.next;
            }else{//需要查找然后插入

                last.next = cur.next;//先把cur摘除， 避免最后成环
                ListNode pre = dummy;
                while(pre.next!=null && pre.next.val <= cur.val){
                    pre = pre.next;
                }

                cur.next = pre.next;
                pre.next = cur;
            }

            //插入排序后的cur = cur.next会成环
            cur = last.next;
        }
        return dummy.next;
    }
}

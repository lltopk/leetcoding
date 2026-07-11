package org.lyflexi.solutions.baseAlgorithm.utils.linked;

/**
 * 1669. 合并两个链表
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
 *
 * 请你将 list1 中下标从 a 到 b 的全部节点都删除，并将list2 接在被删除节点的位置。
 *
 * 下图中蓝色边和节点展示了操作后的结果：
 *
 *
 * 请你返回结果链表的头指针。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：list1 = [10,1,13,6,9,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
 * 输出：[10,1,13,1000000,1000001,1000002,5]
 * 解释：我们删除 list1 中下标为 3 和 4 的两个节点，并将 list2 接在该位置。上图中蓝色的边和节点为答案链表。
 * 示例 2：
 *
 *
 * 输入：list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
 * 输出：[0,1,1000000,1000001,1000002,1000003,1000004,6]
 * 解释：上图中蓝色的边和节点为答案链表。
 *
 *
 * 提示：
 *
 * 3 <= list1.length <= 104
 * 1 <= a <= b < list1.length - 1
 * 1 <= list2.length <= 104
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 67,870/86.9K
 * 通过率
 * 78.1%
 */

import org.lyflexi.common.ListNode;

/**
 * 删除节点
 */
public class LC1669_mergeInBetween {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode tail = list2;
        while(tail.next != null){
            tail = tail.next;
        }

        ListNode dummy = new ListNode(0, list1);
        ListNode cur = list1;
        int idx = 0;
        while(cur != null){
            if(idx == a - 1){
                ListNode next = cur.next;
                cur.next = list2;
                //这里要重新指向提前保存下来的next， 避免cur去遍历list2
                cur = next;
            }

            if(idx == b){
                tail.next = cur;
                break;
            }
            idx++;
            cur = cur.next;
        }
        return list1;
    }
}

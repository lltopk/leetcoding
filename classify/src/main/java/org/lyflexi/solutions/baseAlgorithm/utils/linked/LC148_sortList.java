package org.lyflexi.solutions.baseAlgorithm.utils.linked;

import org.lyflexi.common.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 148. 排序链表
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 *
 *
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 *
 *
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 964,420/1.4M
 * 通过率
 * 67.7%
 */


public class LC148_sortList {
    public ListNode sortList(ListNode head) {
        List<Integer> collect = new ArrayList<>();
        while(head != null){
            collect.add(head.val);
            head = head.next;
        }
        Collections.sort(collect);
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        for(int x: collect){
            cur.next = new ListNode(x);
            cur = cur.next;
        }
        return dummy.next;
    }
}

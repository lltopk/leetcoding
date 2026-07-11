package org.lyflexi.solutions.baseAlgorithm.utils.linked;

/**
 * 3217. 从链表中移除在数组中存在的节点
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums 和一个链表的头节点 head。从链表中移除所有存在于 nums 中的节点后，返回修改后的链表的头节点。
 *
 *
 *
 * 示例 1：
 *
 * 输入： nums = [1,2,3], head = [1,2,3,4,5]
 *
 * 输出： [4,5]
 *
 * 解释：
 *
 *
 *
 * 移除数值为 1, 2 和 3 的节点。
 *
 * 示例 2：
 *
 * 输入： nums = [1], head = [1,2,1,2,1,2]
 *
 * 输出： [2,2,2]
 *
 * 解释：
 *
 *
 *
 * 移除数值为 1 的节点。
 *
 * 示例 3：
 *
 * 输入： nums = [5], head = [1,2,3,4]
 *
 * 输出： [1,2,3,4]
 *
 * 解释：
 *
 *
 *
 * 链表中不存在值为 5 的节点。
 *
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * nums 中的所有元素都是唯一的。
 * 链表中的节点数在 [1, 105] 的范围内。
 * 1 <= Node.val <= 105
 * 输入保证链表中至少有一个值没有在 nums 中出现过。
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 34,895/51.7K
 * 通过率
 * 67.5%
 */

import org.lyflexi.common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 删除节点
 */
public class LC3217_modifiedList {
    public ListNode modifiedList(int[] nums, ListNode head) {
        //利用set判断存在性， 复杂度O（1）
        Set<Integer> mySet = new HashSet<>();
        for (int x : nums) {
            mySet.add(x);
        }
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while(cur != null){
            if(mySet.contains(cur.val)){
                //删除节点
                pre.next = cur.next;
            }else{
                pre = pre.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}

package org.lyflexi.solutions.baseAlgorithm.utils.linked;

import org.lyflexi.common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 817. 链表组件
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个链表的头节点 head，该链表包含唯一的整数值，以及一个整数数组 nums，它是链表值的一个子集。
 *
 * 返回列表 nums 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 nums 中）构成的集合。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入: head = [0,1,2,3], nums = [0,1,3]
 * 输出: 2
 * 解释: 链表中,0 和 1 是相连接的，且 nums 中不包含 2，所以 [0, 1] 是 nums 的一个组件，同理 [3] 也是一个组件，故返回 2。
 * 示例 2：
 *
 *
 *
 * 输入: head = [0,1,2,3,4], nums = [0,3,1,4]
 * 输出: 2
 * 解释: 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。
 *
 *
 * 提示：
 *
 * 链表中节点数为n
 * 1 <= n <= 104
 * 0 <= Node.val < n
 * Node.val 中所有值 不同
 * 1 <= nums.length <= n
 * 0 <= nums[i] < n
 * nums 中所有值 不同
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 60,381/98.7K
 * 通过率
 * 61.2%
 */

/**
 * 题目求组件个数， 转换成求起点个数
 */
public class LC817_numComponents {
    //求的是组件个数， 求起点个数就行了
    public int numComponents(ListNode head, int[] nums) {
        int ret = 0;
        //利用set判断存在性， 复杂度O（1）
        Set<Integer> mySet = new HashSet<>();
        for (int x : nums) {
            mySet.add(x);
        }
        //合法起点个数定义为： 前一个节点不在set中， 但当前节点在set中
        boolean pre = false;
        while(head!=null){
            if(!pre && mySet.contains(head.val)){
                ret++;
                pre = true;
            }

            if(! mySet.contains(head.val)){
                pre = false;
            }
            head = head.next;
        }
        return ret;
    }
}

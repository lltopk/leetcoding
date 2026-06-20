package org.lyflexi.solutions.baseAlgorithm.utils.stack.monotonic_stack.right;

/**
 * 456. 132 模式
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 *
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 * 示例 2：
 *
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 * 示例 3：
 *
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 2 * 105
 * -109 <= nums[i] <= 109
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 96,445/260.1K
 * 通过率
 * 37.1%
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 单调栈, 从右往左维护, 得right
 */
public class LC456_find132pattern {
    public boolean find132pattern(int[] nums) {
        boolean ret = false;
        int two = Integer.MIN_VALUE;
        Deque<Integer> sk = new ArrayDeque<>();
        for(int i = nums.length -1; i>=0; i--){
            int x = nums[i];
            if(x < two){
                return true;
            }
            while(! sk.isEmpty() && sk.peek() < x){
                two = sk.pop();
            }

            //right[i] = sk.peek()//这行代码不需要了, 我们不需要3右侧存在较大的4了, 只要找到右侧小于3的最大值就好了

            sk.push(x);
        }
        return false;
    }
}

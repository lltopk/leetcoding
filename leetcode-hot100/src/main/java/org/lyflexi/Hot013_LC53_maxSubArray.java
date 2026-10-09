package org.lyflexi;

import java.util.*;

/**
 * 53. 最大子数组和
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 
 * 子数组 是数组中的一个连续部分。
 * 
 * 示例 1：
 * 
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 
 * 示例 2：
 * 
 * 输入：nums = [1]
 * 输出：1
 * 
 * 示例 3：
 * 
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 * 
 * 提示：
 * 
 * - 1 <= nums.length <= 10^5
 * 
 * - -10^4 <= nums[i] <= 10^4
 * 
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 */

/**
 * 答疑
 * 
 * 问：为什么不能直接计算出最大前缀和与最小前缀和，二者相减不就是答案吗？
 * 
 * 答：这是错的。子数组的和必须是右边的前缀和减去左边的前缀和。如果最大前缀和在左边，最小前缀和在右边，就不符合要求。例如 $nums=[1,-2]$，最大子数组和是 $1$，如果用最大前缀和 $1$ 减去最小前缀和 $-1$，结果是错误的 $2$。
 */
public class Hot013_LC53_maxSubArray {
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int minPreSum = 0;
        int preSum = 0;
        for (int x : nums) {
            preSum += x; // 当前的前缀和
            ans = Math.max(ans, preSum - minPreSum); // 减去前缀和的最小值
            minPreSum = Math.min(minPreSum, preSum); // 维护前缀和的最小值
        }
        return ans;
    }
}

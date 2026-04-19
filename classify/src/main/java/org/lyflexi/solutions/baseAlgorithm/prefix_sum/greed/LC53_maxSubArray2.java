package org.lyflexi.solutions.baseAlgorithm.prefix_sum.greed;

/**
 * 53. 最大子数组和
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组是数组中的一个连续部分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 *
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 *
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解
 */

/**
 * 前缀和空间优化, 贪心计算
 */
public class LC53_maxSubArray2 {
    public int maxSubArray(int[] nums) {
        int n = nums.length, ans = Integer.MIN_VALUE;
        int minPreS = 0;//贪心
        int preS = 0;//前缀和空间优化
        for(int i =0; i<n; i++){
            preS += nums[i];
            //以preS为基, 计算121.买卖股票的最佳时机
            ans = Math.max(ans, preS - minPreS);
            minPreS = Math.min(minPreS, preS);
        }
        return ans;
    }
}

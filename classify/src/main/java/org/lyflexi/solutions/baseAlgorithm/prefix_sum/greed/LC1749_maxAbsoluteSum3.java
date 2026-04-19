package org.lyflexi.solutions.baseAlgorithm.prefix_sum.greed;

/**
 * 1749. 任意子数组和的绝对值的最大值
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums 。一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。
 *
 * 请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
 *
 * abs(x) 定义如下：
 *
 * 如果 x 是负整数，那么 abs(x) = -x 。
 * 如果 x 是非负整数，那么 abs(x) = x 。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,-3,2,3,-4]
 * 输出：5
 * 解释：子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。
 * 示例 2：
 *
 * 输入：nums = [2,-5,1,-4,3,-2]
 * 输出：8
 * 解释：子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */

/**
 * 前缀和空间优化, 数学特性
 *
 * 给你一组数据, 计算连续子数组和绝对值的最大值:
 *
 * 答案就是: max(preS)−min(preS), 无需考虑正负数, 原因如下
 *
 * - 如果最大前缀和出现在最小前缀和的右边，那么上式算的是最大子数组和。
 * - 如果最大前缀和出现在最小前缀和的左边，那么上式恰好算的是最小子数组和的绝对值。
 *
 * 因此我们只要求出max(preS)以及min(preS), 就能得出答案
 */
public class LC1749_maxAbsoluteSum3 {
    public int maxAbsoluteSum(int[] nums) {
        //迭代计算
        int n = nums.length;
        //最大前缀和 与 最小前缀和
        int preMx = 0, preMn = 0;
        int preS = 0;
        for(int i = 0; i<n; i++){
            preS += nums[i];
            preMx = Math.max(preMx, preS);
            preMn = Math.min(preMn, preS);
        }
        return preMx - preMn;
    }
}

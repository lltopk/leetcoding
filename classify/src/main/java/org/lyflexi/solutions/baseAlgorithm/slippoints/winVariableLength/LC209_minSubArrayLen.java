package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableLength;

/**
 * 209. 长度最小的子数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 *
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 *
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 *
 */

/**
 * 不定长滑动窗口
 */
public class LC209_minSubArrayLen {
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int ans = nums.length+1;//求最小值
        int l = 0;
        //虽然是二重循环, 但由于滑动窗口, 内层循环平均移动1次, 因此最终时间复杂度为n*1 = O(n)
        for(int i = 0; i< nums.length; i ++){
            sum +=nums[i];
            while(sum>=target){
                ans = Math.min(ans, i-l+1);
                sum-=nums[l];
                l++;
            }
        }

        return ans==nums.length+1? 0:ans;
    }
}

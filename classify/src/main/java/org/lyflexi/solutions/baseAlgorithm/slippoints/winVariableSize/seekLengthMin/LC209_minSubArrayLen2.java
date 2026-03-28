package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekLengthMin;

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
 * 不定长滑动窗口求长度, 求最小
 */
public class LC209_minSubArrayLen2 {
    public int minSubArrayLen(int target, int[] nums) {
        //不定长滑动窗口求最小， 越长越合法
        int n = nums.length;
        int ans = n+1;//求最小
        int l = 0;
        int helper = 0;
        for(int r  =0; r<n; r++){
            helper+=nums[r];

            //窗口收缩条件， 如果用小于那么窗口无法收缩
            while(helper - nums[l] >= target){//编程技巧：这里提前减去helper-nums[l], 恰好实现省去最后一次循环的效果，避免最终窗口左边界l多1
                helper-=nums[l];
                l++;
            }

            // 因为是求最小，所以最后需要加额外的判断当满足题意helper >= target迭代解， 否则会求解错误
            if(helper >= target){
                ans = Math.min(ans, r - l + 1);//while循环外迭代迭代答案
            }
        }

        return ans==n+1?0:ans;
    }
}

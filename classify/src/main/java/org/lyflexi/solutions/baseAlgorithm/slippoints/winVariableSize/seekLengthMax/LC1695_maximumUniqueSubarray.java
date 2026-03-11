package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekLengthMax;

/**
 * 1695. 删除子数组的最大得分
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。删除子数组的 得分 就是子数组各元素之 和 。
 *
 * 返回 只删除一个 子数组可获得的 最大得分 。
 *
 * 如果数组 b 是数组 a 的一个连续子序列，即如果它等于 a[l],a[l+1],...,a[r] ，那么它就是 a 的一个子数组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,2,4,5,6]
 * 输出：17
 * 解释：最优子数组是 [2,4,5,6]
 * 示例 2：
 *
 * 输入：nums = [5,2,1,2,5,2,1,2,5]
 * 输出：8
 * 解释：最优子数组是 [5,2,1] 或 [1,2,5]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 */

/**
 * 不定长滑动窗口求最大
 */
public class LC1695_maximumUniqueSubarray {
    public int maximumUniqueSubarray(int[] nums) {
        //不定尺寸滑动窗口求最大和
        int n = nums.length;
        int ans = 0, l = 0;
        int[] dict = new int[10001];
        int helper = 0;
        for(int r = 0; r<n; r++){
            dict[nums[r]]++;
            helper+=nums[r];
            while(dict[nums[r]] > 1){
                helper-=nums[l];
                dict[nums[l]]--;
                l++;
            }

            ans = Math.max(ans, helper);
        }

        return ans;
    }
}

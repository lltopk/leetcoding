package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekLengthMax;

/**
 * 2958. 最多 K 个重复元素的最长子数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums 和一个整数 k 。
 *
 * 一个元素 x 在数组中的 频率 指的是它在数组中的出现次数。
 *
 * 如果一个数组中所有元素的频率都 小于等于 k ，那么我们称这个数组是 好 数组。
 *
 * 请你返回 nums 中 最长好 子数组的长度。
 *
 * 子数组 指的是一个数组中一段连续非空的元素序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1,2,3,1,2], k = 2
 * 输出：6
 * 解释：最长好子数组是 [1,2,3,1,2,3] ，值 1 ，2 和 3 在子数组中的频率都没有超过 k = 2 。[2,3,1,2,3,1] 和 [3,1,2,3,1,2] 也是好子数组。
 * 最长好子数组的长度为 6 。
 * 示例 2：
 *
 * 输入：nums = [1,2,1,2,1,2,1,2], k = 1
 * 输出：2
 * 解释：最长好子数组是 [1,2] ，值 1 和 2 在子数组中的频率都没有超过 k = 1 。[2,1] 也是好子数组。
 * 最长好子数组的长度为 2 。
 * 示例 3：
 *
 * 输入：nums = [5,5,5,5,5,5,5], k = 4
 * 输出：4
 * 解释：最长好子数组是 [5,5,5,5] ，值 5 在子数组中的频率没有超过 k = 4 。
 * 最长好子数组的长度为 4 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= k <= nums.length
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 不定长滑动窗口求最大
 */
public class LC2958_maxSubarrayLength {
    public int maxSubarrayLength(int[] nums, int k) {
        //不定长滑动窗口求最大
        int n = nums.length;
        int ans = 0, l = 0;
        // int[] dict = new int[1000000001]; OOM
        Map<Integer, Integer> helper = new HashMap<>();
        for(int i = 0; i<n; i++){
            helper.merge(nums[i], 1, Integer::sum);
            while(helper.get(nums[i]) > k){
                if(helper.get(nums[l]) == 1){
                    helper.remove(nums[l]);
                }else{
                    helper.merge(nums[l], -1, Integer::sum);
                }

                l++;
            }
            ans = Math.max(ans, i - l +1);
        }

        return ans;
    }
}

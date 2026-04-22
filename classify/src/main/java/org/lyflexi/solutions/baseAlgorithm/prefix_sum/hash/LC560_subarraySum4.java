package org.lyflexi.solutions.baseAlgorithm.prefix_sum.hash;

/**
 * 560. 和为 K 的子数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 *
 * 子数组是数组中元素的连续非空序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀和与哈希表
 *
 * 改成计算元素和等于 k 的最长子数组，要怎么做？
 */
public class LC560_subarraySum4 {
    public int longestSubarraySumK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int preS = 0;
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            preS += nums[i];

            if (map.containsKey(preS - k)) {
                ans = Math.max(ans, i - map.get(preS - k));
            }

            // 只存第一次（关键）
            map.putIfAbsent(preS, i);
        }

        return ans;
    }
}

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
import java.util.TreeMap;

/**
 * 前缀和与哈希表
 *
 * 改成元素和至多为 k，要怎么做？
 *
 * 即preS - k >= s1, 用红黑树哈希, 天然有序
 */
public class LC560_subarraySum6 {
    public int subarraySumAtMostK(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1);

        int preS = 0;
        int ans = 0;

        for (int num : nums) {
            preS += num;

            // 找 >= preS - k 的所有前缀
            for (int cnt : map.tailMap(preS - k).values()) {
                ans += cnt;
            }

            map.merge(preS, 1, Integer::sum);
        }

        return ans;
    }
}

package org.lyflexi;

import java.util.*;

/**
 * 560. 和为 K 的子数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 
 * 子数组是数组中元素的连续非空序列。
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 
 * 示例 2：
 * 
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * 
 * 提示：
 * 
 * - 1 <= nums.length <= 2 * 10^4
 * 
 * - -1000 <= nums[i] <= 1000
 * 
 * - -10^7 <= k <= 10^7
 */

/**
 * 写法一：两次遍历
 */
public class Hot010_LC560_subarraySum {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }

        Map<Integer, Integer> cnt = new HashMap<>(n + 1, 1); // 预分配空间
        int ans = 0;
        for (int sj : s) {
            ans += cnt.getOrDefault(sj - k, 0);
            cnt.merge(sj, 1, Integer::sum); // cnt[sj]++
        }
        return ans;
    }
}

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

import java.util.TreeMap;

/**
 * 前缀和与哈希表
 *
 * 改成计算元素和为奇数的子数组个数，要怎么做？
 *
 * 哈希表的key不存具体某个s的个数，哈希的长度为2仅存奇和偶的个数，
 *
 * 遇到偶加奇数个, 偶数加1
 * 遇到奇加偶数个, 奇数加1
 */
public class LC560_subarraySum7 {
    public int numOfSubarrays(int[] nums) {
        //偶数初始1(适配前缀和本身就是子数组, 且前缀和为奇数的情况), 奇数初始0
        int even = 1, odd = 0;
        int preS = 0;
        int ans = 0;

        for (int num : nums) {
            preS += num;

            if (preS % 2 == 0) {
                ans += odd;
                even++;
            } else {
                ans += even;
                odd++;
            }
        }

        return ans;
    }
}

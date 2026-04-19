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
 * 两次循环
 */
public class LC560_subarraySum {
    public int subarraySum(int[] nums, int k) {
        //前缀和, 并且因为是两个变量用哈希
        int n = nums.length, ans = 0;
        int[] preS = new int[n+1];
        for(int i = 1; i< n+1; i++){
            preS[i] = preS[i-1] + nums[i-1];
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< n+1; i++){
            //s2 - s1 = k
            ans += map.getOrDefault(preS[i] - k, 0);
            //会初始化preS[0]的次数为1, 这很关键, 能够满足s2本身就是答案的情况
            map.merge(preS[i], 1 ,Integer::sum);
        }

        return ans;
    }
}

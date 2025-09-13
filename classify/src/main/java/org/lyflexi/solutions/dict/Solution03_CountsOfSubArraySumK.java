package org.lyflexi.solutions.dict;

import java.util.HashMap;

/**
 * @Author: ly
 * @Date: 2024/3/24 14:35
 */
/*974. 和可被 K 整除的子数组
给定一个整数数组 nums 和一个整数 k ，返回其中元素之和可被 k 整除的（连续、非空） 子数组 的数目。
子数组 是数组的 连续 部分。

示例 1：
输入：nums = [4,5,0,-2,-3,1], k = 5
输出：7
解释：
有 7 个子数组满足其元素之和可被 k = 5 整除：
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

示例 2:
输入: nums = [5], k = 9
输出: 0
 */
public class Solution03_CountsOfSubArraySumK {
    public int subarraysDivByK(int[] nums, int k) {
        int answer = 0;
        int n = nums.length;
        int preSum = 0;
        //以前缀和模 k 的值为键,出现次数为值
        HashMap<Integer, Integer> map = new HashMap<>();
        //考虑当前前缀正好等于k的情况
        map.put(0,1);
        for (int i = 0; i < n; i++) {
            preSum+=nums[i];

            // 注意 Java 取模的特殊性，当被除数为负数时取模结果为负数，需要纠正
            // int modulus = (preSum) % k;
            int modulus = (preSum % k + k) % k;
            int same = map.getOrDefault(modulus, 0);
            answer += same;

            map.put(modulus,map.getOrDefault(modulus, 0)+1);
        }

        return answer;
    }
}

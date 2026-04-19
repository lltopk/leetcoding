package org.lyflexi.solutions.baseAlgorithm.prefix_sum.hash;

/**
 * 930. 和相同的二元子数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
 *
 * 子数组 是数组的一段连续部分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,0,1,0,1], goal = 2
 * 输出：4
 * 解释：
 * 有 4 个满足题目要求的子数组：[1,0,1]、[1,0,1,0]、[0,1,0,1]、[1,0,1]
 * 示例 2：
 *
 * 输入：nums = [0,0,0,0,0], goal = 0
 * 输出：15
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * nums[i] 不是 0 就是 1
 * 0 <= goal <= nums.length
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀和与哈希表
 */
public class LC930_numSubarraysWithSum {
    public int numSubarraysWithSum(int[] nums, int goal) {
        //前缀和, 并且因为是两个变量用哈希
        int n = nums.length, ans = 0;
        int preS = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);//空前缀和的次数

        //下面循环了n次,  少循环一次就是对(preS[0]=0, 1)的map统计, 因为我们在循环外提前初始化了map.put(0, 1)
        for(int r = 0; r< n; r++){
            //不包含
            preS += nums[r];
            //s2 - s1 = k
            ans += map.getOrDefault(preS - goal, 0);
            map.merge(preS, 1 ,Integer::sum);
        }
        return ans;
    }
}

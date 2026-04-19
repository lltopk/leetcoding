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
 * 一次循环
 */
public class LC560_subarraySum2 {
    public int subarraySum(int[] nums, int k) {
        //前缀和, 并且因为是两个变量用哈希
        int n = nums.length, ans = 0;
        int preS = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);//空前缀和的次数

        //下面循环了n次,  少循环一次就是对preS[0]=0的map统计, 因为初始化map已经补充了map.put(0, 1)而不是map.put(0, 0)
        for(int r = 0; r< n; r++){
            //不包含
            preS += nums[r];
            //s2 - s1 = k
            ans += map.getOrDefault(preS - k, 0);
            map.merge(preS, 1 ,Integer::sum);
        }
        return ans;
    }
}

package org.lyflexi.solutions.baseAlgorithm.prefix_sum.hash;

/**
 * 3026. 最大好子数组和
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个长度为 n 的数组 nums 和一个 正 整数 k 。
 *
 * 如果 nums 的一个子数组中，第一个元素和最后一个元素 差的绝对值恰好 为 k ，我们称这个子数组为 好 的。换句话说，如果子数组 nums[i..j] 满足 |nums[i] - nums[j]| == k ，那么它是一个好子数组。
 *
 * 请你返回 nums 中 好 子数组的 最大 和，如果没有好子数组，返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4,5,6], k = 1
 * 输出：11
 * 解释：好子数组中第一个元素和最后一个元素的差的绝对值必须为 1 。好子数组有 [1,2] ，[2,3] ，[3,4] ，[4,5] 和 [5,6] 。最大子数组和为 11 ，对应的子数组为 [5,6] 。
 * 示例 2：
 *
 * 输入：nums = [-1,3,2,4,5], k = 3
 * 输出：11
 * 解释：好子数组中第一个元素和最后一个元素的差的绝对值必须为 3 。好子数组有 [-1,3,2] 和 [2,4,5] 。最大子数组和为 11 ，对应的子数组为 [2,4,5] 。
 * 示例 3：
 *
 * 输入：nums = [-1,-2,-3,-4], k = 2
 * 输出：-6
 * 解释：好子数组中第一个元素和最后一个元素的差的绝对值必须为 2 。好子数组有 [-1,-2,-3] 和 [-2,-3,-4] 。最大子数组和为 -6 ，对应的子数组为 [-1,-2,-3] 。
 *
 *
 * 提示：
 *
 * 2 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 1 <= k <= 109
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 哈希前缀和
 */
public class LC3026_maximumSubarraySum {
    public long maximumSubarraySum(int[] nums, int k) {
        //求preS[j+1] - min(preS[i])
        //且|nums[j] - nums[i]| == k, 想象成坐标轴, 将nums[j]视为中心点, 即nums[i] = nums[j]+k 或者 nums[i] = nums[j] - k

        //设哈希的key为nums[i], value为以nums[i]结尾的最小前缀和
        int n = nums.length;
        long ans = Long.MIN_VALUE, preS = 0;
        Map<Integer, Long> map = new HashMap<>();
        for(int r = 0; r<n; r++){
            preS += nums[r];
            int a = nums[r] + k;
            if(map.get(a)!=null){
                ans = Math.max(ans, preS - map.get(a));
            }
            // ans = Math.max(ans, preS - map.getOrDefault(a, Long.valueOf(Integer.MAX_VALUE)));//容易越界

            a = nums[r] - k;
            // ans = Math.max(ans, preS - map.getOrDefault(a, Long.valueOf(Integer.MAX_VALUE)));//容易越界
            if(map.get(a)!=null){
                ans = Math.max(ans, preS - map.get(a));
            }
            //维护左, 值为以任意元素结尾的最小前缀和
            Long oldS = map.get(nums[r]);
            Long newS = preS-nums[r];//preS[i]: 左闭右开区间, preS[r]不包含nums[r]
            if(oldS==null){
                map.put(nums[r], newS);
            }else{
                map.put(nums[r], Math.min(newS, oldS));
            }
        }
        return ans  == Long.MIN_VALUE?0:ans;
    }
}

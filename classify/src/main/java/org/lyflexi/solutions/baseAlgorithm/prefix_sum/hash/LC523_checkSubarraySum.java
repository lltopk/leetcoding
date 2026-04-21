package org.lyflexi.solutions.baseAlgorithm.prefix_sum.hash;

/**
 * 523. 连续的子数组和
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums 和一个整数 k ，如果 nums 有一个 好的子数组 返回 true ，否则返回 false：
 *
 * 一个 好的子数组 是：
 *
 * 长度 至少为 2 ，且
 * 子数组元素总和为 k 的倍数。
 * 注意：
 *
 * 子数组 是数组中 连续 的部分。
 * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。0 始终 视为 k 的一个倍数。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [23,2,4,6,7], k = 6
 * 输出：true
 * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
 * 示例 2：
 *
 * 输入：nums = [23,2,6,4,7], k = 6
 * 输出：true
 * 解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。
 * 42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
 * 示例 3：
 *
 * 输入：nums = [23,2,6,4,7], k = 13
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 * 0 <= sum(nums[i]) <= 231 - 1
 * 1 <= k <= 231 - 1
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀和+哈希
 */
public class LC523_checkSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        //(s2 - s1) % k ==0
        //等价于s2%k == s1 %2, 这是两变量问题, 可以用哈希
        int n = nums.length;
        //存储每个s%k最早出现的位置
        Map<Integer, Integer> map = new HashMap<>();
        //-1表示支持前缀和本身是答案的情况, 此时r - (-1) 恰好代表 前缀和实际len
        map.put(0, -1);
        int preS = 0;
        for(int r = 0; r<n; r++){
            preS += nums[r];
            if(map.containsKey(preS%k)){
                if(r - map.getOrDefault(preS%k, 0) > 1){
                    return true;
                }
            }else{
                map.put(preS%k, r);
            }

        }

        return false;
    }
}

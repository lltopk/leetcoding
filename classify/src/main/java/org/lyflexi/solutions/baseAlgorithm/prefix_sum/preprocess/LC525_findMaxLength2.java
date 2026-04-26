package org.lyflexi.solutions.baseAlgorithm.prefix_sum.preprocess;

/**
 * 525. 连续数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [0,1]
 * 输出：2
 * 说明：[0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
 * 示例 2：
 *
 * 输入：nums = [0,1,0]
 * 输出：2
 * 说明：[0, 1] (或 [1, 0]) 是具有相同数量 0 和 1 的最长连续子数组。
 * 示例 3：
 *
 * 输入：nums = [0,1,1,1,1,1,0,0,0]
 * 输出：6
 * 解释：[1,1,1,0,0,0] 是具有相同数量 0 和 1 的最长连续子数组。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 预处理前缀和 + 哈希
 *
 * 前缀和数组空间优化
 */
public class LC525_findMaxLength2 {
    public int findMaxLength(int[] nums) {
        //把 0 看成 −1，计算和为 0 的最长子数组。这样就有了子数组和的比较对象target == 0
        int n = nums.length;

        int ans = 0, preS = 0;//前缀和空间优化
        //存储最早出现的下标
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);//适配前缀和本身就是子数组的情况
        for(int r = 0; r< n; r++){
            //s2 - s1 == 0, 即s2 == s1, 求len(s2 -s1)
            preS += nums[r] == 0 ? -1 : 1;
            if(map.containsKey(preS)){
                ans = Math.max(ans, r - map.get(preS));
            }else{
                //store earliest idx
                map.put(preS, r);
            }

        }

        return ans;
    }
}

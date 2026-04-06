package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_middle._01_init_suf_and_roll_pre;

/**
 * 2909. 元素和最小的山形三元组 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始的整数数组 nums 。
 *
 * 如果下标三元组 (i, j, k) 满足下述全部条件，则认为它是一个 山形三元组 ：
 *
 * i < j < k
 * nums[i] < nums[j] 且 nums[k] < nums[j]
 * 请你找出 nums 中 元素和最小 的山形三元组，并返回其 元素和 。如果不存在满足条件的三元组，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [8,6,1,5,3]
 * 输出：9
 * 解释：三元组 (2, 3, 4) 是一个元素和等于 9 的山形三元组，因为：
 * - 2 < 3 < 4
 * - nums[2] < nums[3] 且 nums[4] < nums[3]
 * 这个三元组的元素和等于 nums[2] + nums[3] + nums[4] = 9 。可以证明不存在元素和小于 9 的山形三元组。
 * 示例 2：
 *
 * 输入：nums = [5,4,8,7,10,2]
 * 输出：13
 * 解释：三元组 (1, 3, 5) 是一个元素和等于 13 的山形三元组，因为：
 * - 1 < 3 < 5
 * - nums[1] < nums[3] 且 nums[5] < nums[3]
 * 这个三元组的元素和等于 nums[1] + nums[3] + nums[5] = 13 。可以证明不存在元素和小于 13 的山形三元组。
 * 示例 3：
 *
 * 输入：nums = [6,5,4,3,4,5]
 * 输出：-1
 * 解释：可以证明 nums 中不存在山形三元组。
 *
 *
 * 提示：
 *
 * 3 <= nums.length <= 105
 * 1 <= nums[i] <= 108
 */

/**
 * 三变量 枚举中间
 */
public class LC2909_minimumSum {
    public int minimumSum(int[] nums) {
        int ans = Integer.MAX_VALUE, n = nums.length;

        int preMin = nums[0];//滚动计算
        int sufMin[] = new int[n];//代表nums[j]的后缀最小值, 包括nums[j]在内, 是个动态的数组
        //预计算 j: [2, n-1]
        sufMin[n-1] = nums[n-1];
        //后缀最小公式: sufMin[j] = Math.min(nums[j], sufMin[j+1]);
        for(int j = n-2; j>=2; j--){
            sufMin[j] = Math.min(nums[j], sufMin[j+1]);
        }

        //三元素, 枚举中间
        for(int j = 0; j< n-1; j++){

            if(nums[j] > preMin && nums[j] > sufMin[j+1]){
                ans = Math.min(ans, preMin+nums[j]+sufMin[j+1]);
            }
            preMin = Math.min(preMin, nums[j]);
        }

        return ans==Integer.MAX_VALUE?-1:ans;
    }
}

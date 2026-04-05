package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_middle;

/**
 * 2873. 有序三元组中的最大值 I
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始的整数数组 nums 。
 *
 * 请你从所有满足 i < j < k 的下标三元组 (i, j, k) 中，找出并返回下标三元组的最大值。如果所有满足条件的三元组的值都是负数，则返回 0 。
 *
 * 下标三元组 (i, j, k) 的值等于 (nums[i] - nums[j]) * nums[k] 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [12,6,1,2,7]
 * 输出：77
 * 解释：下标三元组 (0, 2, 4) 的值是 (nums[0] - nums[2]) * nums[4] = 77 。
 * 可以证明不存在值大于 77 的有序下标三元组。
 * 示例 2：
 *
 * 输入：nums = [1,10,3,4,19]
 * 输出：133
 * 解释：下标三元组 (1, 2, 4) 的值是 (nums[1] - nums[2]) * nums[4] = 133 。
 * 可以证明不存在值大于 133 的有序下标三元组。
 * 示例 3：
 *
 * 输入：nums = [1,2,3]
 * 输出：0
 * 解释：唯一的下标三元组 (0, 1, 2) 的值是一个负数，(nums[0] - nums[1]) * nums[2] = -3 。因此，答案是 0 。
 *
 *
 * 提示：
 *
 * 3 <= nums.length <= 100
 * 1 <= nums[i] <= 106
 */

/**
 * 三变量, 枚举中间
 */
public class LC2873_maximumTripletValue {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long ans = 0;//求最大,  最大是0, 最大值不存在负值
        //前缀要最大, 后缀也要最大
        int pre = nums[0];
        int[] suf = new int[n];//表示当前j后续的所有元素中的最大值, 包括当前j
        suf[n-1] = nums[n-1];
        for(int j = n-2; j>=2; j--){
            suf[j] = Math.max(nums[j], suf[j+1]);
        }

        for(int j = 1; j<n-1; j++){
            ans = Math.max(ans, (long)(pre - nums[j]) * (suf[j+1]));
            pre = Math.max(pre, nums[j]);
        }

        return ans;
    }
}

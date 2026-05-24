package org.lyflexi.solutions.baseAlgorithm.diff.two_arr;

/**
 * 3229. 使数组等于目标数组所需的最少操作次数
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你两个长度相同的正整数数组 nums 和 target。
 *
 * 在一次操作中，你可以选择 nums 的任何子数组，并将该子数组内的每个元素的值增加或减少 1。
 *
 * 返回使 nums 数组变为 target 数组所需的 最少 操作次数。
 *
 *
 *
 * 示例 1：
 *
 * 输入： nums = [3,5,1,2], target = [4,6,2,4]
 *
 * 输出： 2
 *
 * 解释：
 *
 * 执行以下操作可以使 nums 等于 target：
 * - nums[0..3] 增加 1，nums = [4,6,2,3]。
 * - nums[3..3] 增加 1，nums = [4,6,2,4]。
 *
 * 示例 2：
 *
 * 输入： nums = [1,3,2], target = [2,1,4]
 *
 * 输出： 5
 *
 * 解释：
 *
 * 执行以下操作可以使 nums 等于 target：
 * - nums[0..0] 增加 1，nums = [2,3,2]。
 * - nums[1..1] 减少 1，nums = [2,2,2]。
 * - nums[1..1] 减少 1，nums = [2,1,2]。
 * - nums[2..2] 增加 1，nums = [2,1,3]。
 * - nums[2..2] 增加 1，nums = [2,1,4]。
 *
 *
 *
 * 提示：
 *
 * 1 <= nums.length == target.length <= 105
 * 1 <= nums[i], target[i] <= 108
 */

/**
 * 对双数组求差分
 */
public class LC3229_minimumOperations {
    /**
     * 对双数组的差值数组, 对其求差分数组
     * @param nums
     * @param target
     * @return
     */
    public long minimumOperations(int[] nums, int[] target) {
        //最小操作次数 = min(posSum - negSum) + |posSum - negSum|
        //上式子等价于max(posSum, negSum)
        long posSum = 0, negSum = 0;
        for(int i = 0; i<nums.length; i++){
            int d = 0;
            if(i==0){
                d = nums[i] - target[i];
            }else{
                d = (nums[i] - target[i]) - (nums[i-1] - target[i-1]);
            }

            if(d>0){
                posSum += d;
            }else{
                negSum += Math.abs(d);
            }
        }
        return Math.max(posSum, negSum);
    }
}

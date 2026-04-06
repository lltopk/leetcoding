package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_right_and_maintain_left._01_not_cover_rfactor_priority_update_ans;

/**
 * 2342. 数位和相等数对的最大和
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始的数组 nums ，数组中的元素都是 正 整数。请你选出两个下标 i 和 j（i != j），且 nums[i] 的数位和 与  nums[j] 的数位和相等。
 *
 * 请你找出所有满足条件的下标 i 和 j ，找出并返回 nums[i] + nums[j] 可以得到的 最大值。如果不存在这样的下标对，返回 -1。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [18,43,36,13,7]
 * 输出：54
 * 解释：满足条件的数对 (i, j) 为：
 * - (0, 2) ，两个数字的数位和都是 9 ，相加得到 18 + 36 = 54 。
 * - (1, 4) ，两个数字的数位和都是 7 ，相加得到 43 + 7 = 50 。
 * 所以可以获得的最大和是 54 。
 * 示例 2：
 *
 * 输入：nums = [10,12,19,14]
 * 输出：-1
 * 解释：不存在满足条件的数对，返回 -1 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 */

/**
 * 枚举技巧： 枚举右维护左
 */
public class LC2342_maximumSum {
    public int maximumSum(int[] nums) {
        int n = nums.length, ans = -1;
        //数位和最多为9个9
        int[] dict = new int[82];//统计数位和 对应 实际元素最大值
        //初始化MIN_VALUE
        for(int s = 0; s <=81; s++){
            dict[s] = Integer.MIN_VALUE;
        }
        for(int r = 0; r< n; r++){
            int sumBit = 0, num = nums[r];
            while(num!=0){
                sumBit+=num%10;
                num/=10;
            }
            //由于初始化了MIN_VALUE， 因此若dict[sumBit]不存在， 恰好也满足ans为-1
            ans = Math.max(ans, nums[r] + dict[sumBit]);
            //维护左
            dict[sumBit] = Math.max(dict[sumBit], nums[r]);
        }

        return ans;
    }
}

package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_right_and_maintain_left._01_not_cover_rfactor_priority_update_ans;

/**
 * 2815. 数组中的最大数对和
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始的整数数组 nums 。请你从 nums 中找出和 最大 的一对数，且这两个数数位上最大的数字相等。
 *
 * 返回最大和，如果不存在满足题意的数字对，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [51,71,17,24,42]
 * 输出：88
 * 解释：
 * i = 1 和 j = 2 ，nums[i] 和 nums[j] 数位上最大的数字相等，且这一对的总和 71 + 17 = 88 。
 * i = 3 和 j = 4 ，nums[i] 和 nums[j] 数位上最大的数字相等，且这一对的总和 24 + 42 = 66 。
 * 可以证明不存在其他数对满足数位上最大的数字相等，所以答案是 88 。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：-1
 * 解释：不存在数对满足数位上最大的数字相等。
 *
 *
 * 提示：
 *
 * 2 <= nums.length <= 100
 * 1 <= nums[i] <= 104
 */

/**
 * 枚举技巧; 枚举右, 维护左
 */
public class LC2815_maxSum {
    public int maxSum(int[] nums) {
        int n = nums.length, ans= -1;
        // Map<Integer, Integer> map = new HashMap<>();//维护任意元素最大数位, 对应的最大实际值
        //字典大小确定, 最大数位是9
        int[] dict = new int[10];
        for(int i = 0; i<=9; i++){
            dict[i] = Integer.MIN_VALUE;
        }
        for(int r = 0; r<n; r++){
            int maxBit = 0, num = nums[r];
            while(num>0){
                maxBit=Math.max(maxBit, num%10);
                num/=10;
            }
            ans = Math.max(ans, dict[maxBit] + nums[r]);
            dict[maxBit] = Math.max(dict[maxBit], nums[r]);
        }

        return ans;
    }
}

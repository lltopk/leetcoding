package org.lyflexi;

import java.util.*;

/**
 * 152. 乘积最大子数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 
 * 测试用例的答案是一个 32-位 整数。
 * 
 * 请注意，一个只包含一个元素的数组的乘积是这个元素的值。
 * 
 * 示例 1:
 * 
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 
 * 示例 2:
 * 
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * 
 * 提示:
 * 
 * - 1 <= nums.length <= 2 * 10^4
 * 
 * - -10 <= nums[i] <= 10
 * 
 * - nums 的任何子数组的乘积都 保证 是一个 32-位 整数
 */

/**
 * 写法一
 */
public class Hot088_LC152_maxProduct {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] fMax = new int[n];
        int[] fMin = new int[n];
        fMax[0] = fMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            int x = nums[i];
            // 把 x 加到右端点为 i-1 的（乘积最大/最小）子数组后面，
            // 或者单独组成一个子数组，只有 x 一个元素
            fMax[i] = Math.max(Math.max(fMax[i - 1] * x, fMin[i - 1] * x), x);
            fMin[i] = Math.min(Math.min(fMax[i - 1] * x, fMin[i - 1] * x), x);
        }
        return Arrays.stream(fMax).max().getAsInt();
    }
}

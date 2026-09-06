package org.lyflexi.solutions.baseAlgorithm.prefix_sum;

/**
 * 238. 除了自身以外数组的乘积
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除了 nums[i] 之外其余各元素的乘积 。
 *
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 *
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * 示例 2:
 *
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 *
 *
 * 提示：
 *
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * 输入 保证 数组 answer[i] 在  32 位 整数范围内
 *
 *
 * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组 不被视为 额外空间。）
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,088,221/1.4M
 * 通过率
 * 77.7%
 */
public class LC238_productExceptSelf2 {
    //前后缀分解, 不使用额外空间(答案数组不算空间)
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length, pre = 1;
        int[] sufS = new int[n];//左开右闭
        sufS[n - 1] = 1;
        for(int i = n - 2; i >=0; i--){
            sufS[i] = sufS[i + 1] * nums[ i+ 1];
        }

        for(int i = 0; i< n; i++){
            //不怕丢失数据, 下次循环只会使用sufS[i+1]
            sufS[i] = pre * sufS[i];
            pre *= nums[i];
        }
        return sufS;
    }
}

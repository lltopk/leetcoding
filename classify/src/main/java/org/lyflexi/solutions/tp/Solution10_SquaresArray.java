package org.lyflexi.solutions.tp;

/**
 * @Author: ly
 * @Date: 2024/3/24 15:06
 */

/*
*
* 977. 有序数组的平方
给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。

示例 1：
输入：nums = [-4,-1,0,3,10]
输出：[0,1,9,16,100]
解释：平方后，数组变为 [16,1,0,9,100]
排序后，数组变为 [0,1,9,16,100]
*
示例 2：
输入：nums = [-7,-3,2,3,11]
输出：[4,9,9,49,121]
*
*
* 请你设计时间复杂度为 O(n) 的算法解决本问题
* */
public class Solution10_SquaresArray {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        //双指针
        int left = 0, right = n-1;
        //原数组递增，新数组要逆序设置
        for (int i =n-1; i<n&&left<=right; i--) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                answer[i] = nums[left] * nums[left];
                ++left;
            } else {
                answer[i] = nums[right] * nums[right];
                --right;
            }
        }
        return answer;
    }
}

package org.lyflexi.solutions.tp;

/**
 * @Author: ly
 * @Date: 2024/3/24 16:16
 */

/*
* 905. 按奇偶排序数组  双指针
已解答
简单
相关标签
相关企业
给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。

返回满足此条件的 任一数组 作为答案。



示例 1：

输入：nums = [3,1,2,4]
输出：[2,4,3,1]
解释：[4,2,3,1]、[2,4,1,3] 和 [4,2,1,3] 也会被视作正确答案。
示例 2：

输入：nums = [0]
输出：[0]

* */
public class Solution11_OddEvenSorting {
    public int[] sortArrayByParity(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        int left = 0, right = n - 1;
        for (int num : nums) {
            if (num % 2 == 0) {
                answer[left++] = num;
            } else {
                answer[right--] = num;
            }
        }
        return answer;
    }
}

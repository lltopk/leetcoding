package org.lyflexi.solutions.tp;

/**
 * @Author: ly
 * @Date: 2024/3/24 16:16
 */

/*
* 922. 按奇偶排序数组 II
给定一个非负整数数组 nums，  nums 中一半整数是 奇数 ，一半整数是 偶数 。
对数组进行排序，以便当 nums[i] 为奇数时，i 也是 奇数 ；当 nums[i] 为偶数时， i 也是 偶数 。
你可以返回 任何满足上述条件的数组作为答案 。

示例 1：
输入：nums = [4,2,5,7]
输出：[4,5,2,7]
解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
*
示例 2：
输入：nums = [2,3]
输出：[2,3]
* */
public class Solution12_OddEvenSortingⅡ {
    //双指针
    public int[] sortArrayByParityII(int[] nums) {
        int n = nums.length;
        //奇数索引指针j
        int j = 1;
        //偶数索引指针i , 0也是偶数索引
        for (int i = 0; i < n; i += 2) {
            if (nums[i] % 2 == 1) {
                while (nums[j] % 2 == 1) {
                    j += 2;
                }
                //如果偶数索引位置是基数，又找到一个基数索引位置是偶数，直接交换
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        return nums;
    }
}

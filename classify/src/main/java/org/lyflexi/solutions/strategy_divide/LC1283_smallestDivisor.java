/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.lyflexi.solutions.strategy_divide;

import java.util.Arrays;

/**
 *
 * @author hasee
 * 1283. 使结果不超过阈值的最小除数
已解答
中等
相关标签
premium lock icon
相关企业
提示
给你一个整数数组 nums 和一个正整数 threshold  ，你需要选择一个正整数作为除数，然后将数组里每个数都除以它，并对除法结果求和。

请你找出能够使上述结果小于等于阈值 threshold 的除数中 最小 的那个。

每个数除以除数后都向上取整，比方说 7/3 = 3 ， 10/2 = 5 。

题目保证一定有解。

 

示例 1：

输入：nums = [1,2,5,9], threshold = 6
输出：5
解释：如果除数为 1 ，我们可以得到和为 17 （1+2+5+9）。
如果除数为 4 ，我们可以得到和为 7 (1+1+2+3) 。如果除数为 5 ，和为 5 (1+1+1+2)。
示例 2：

输入：nums = [2,3,5,7,11], threshold = 11
输出：3
示例 3：

输入：nums = [19], threshold = 5
输出：4
 

提示：

1 <= nums.length <= 5 * 10^4
1 <= nums[i] <= 10^6
nums.length <= threshold <= 10^6
 */

/**
 * 二分答案
 */
public class LC1283_smallestDivisor {
    public int smallestDivisor(int[] nums, int threshold) {
        Arrays.sort(nums);
        int l = 0, n = nums.length;
        int r = Integer.MIN_VALUE;

        //计算r的右界
        for(int i = 0; i< n; i++){
            r = Math.max(r , nums[i]);
        }

        while(l+1<r){
            int mid = l + ((r-l)>>1);
            if(checkIncrease(nums, mid, threshold)){
                l = mid;
            }else{
                r = mid;
            }
        }

        return r;
    }

    private boolean checkIncrease(int[] nums, int mid, int threshold){
        int n = nums.length;
        int sum =  0;
        for(int i = 0; i<n ;i++){
            sum += (nums[i]+mid-1)/mid;//a/b的向上取整公式为(a+b-1)/b
        }
        return sum > threshold;//大于则需要增大除数, l右移
    }
}

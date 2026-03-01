/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.lyflexi.solutions.strategy_binary;

import java.util.Arrays;

/**
 *
 * @author hasee
 * 2563. 统计公平数对的数目
已解答
中等
相关标签
premium lock icon
相关企业
提示
给你一个下标从 0 开始、长度为 n 的整数数组 nums ，和两个整数 lower 和 upper ，返回 公平数对的数目 。

如果 (i, j) 数对满足以下情况，则认为它是一个 公平数对 ：

0 <= i < j < n，且
lower <= nums[i] + nums[j] <= upper
 

示例 1：

输入：nums = [0,1,7,4,4,5], lower = 3, upper = 6
输出：6
解释：共计 6 个公平数对：(0,3)、(0,4)、(0,5)、(1,3)、(1,4) 和 (1,5) 。
示例 2：

输入：nums = [1,7,9,2,5], lower = 11, upper = 11
输出：1
解释：只有单个公平数对：(2,3) 。
 

提示：

1 <= nums.length <= 105
nums.length == n
-109 <= nums[i] <= 109
-109 <= lower <= upper <= 109
 */
public class LC2563_countFairPairs {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long rs= 0;
        for(int i =0;i<nums.length;i++){
            int first = nums[i];
            // 约束条件0 <= i < j < n, 否则会计算重复
            // 下面每次从右区间寻找第二个值的索引范围[l,r)
            int l = lowerBound(nums, i+1, lower-first);
            int r = lowerBound(nums, i+1, upper-first+1);
            rs += r-l;
        }

        return rs;

    }

    private int lowerBound(int[] nums, int start , int target){
        int n = nums.length;
        int l = start, r = n;
        while(l<r){
            int mid = l + ((r-l)>>1);
            if(nums[mid]<target){
                l = mid+1;
            }else{
                r = mid;
            }
        }
        return l;
    }
}

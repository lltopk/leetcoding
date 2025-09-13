package org.lyflexi.solutions.dp.package0or1;

/**
 * @Author: ly
 * @Date: 2024/2/20 15:26
 */


/*
* 377. 组合总和 Ⅳ
给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
。*/
public class Solution04_CoinChangeComposition {

    // f(0) = 1;
    // 组合数f(target) = Sum(f(target-coins[0]),f(target-coins[1])...,f(target-coins[n-1]))
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

}

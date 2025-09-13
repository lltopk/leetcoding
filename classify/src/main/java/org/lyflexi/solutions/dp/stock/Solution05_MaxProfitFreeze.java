package org.lyflexi.solutions.dp.stock;

/**
 * @Author: ly
 * @Date: 2024/2/21 10:45
 */

/*
* 309. 买卖股票的最佳时机含冷冻期
给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。
设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
*
*
* 示例 1:
输入: prices = [1,2,3,0,2]
输出: 3
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
*
示例 2:
输入: prices = [1]
输出: 0
* */
public class Solution05_MaxProfitFreeze {
    public int maxProfit(int[] prices) {

        int n = prices.length;
        //第一维的i代表第几天
        //第二维大小为3：0代表持有股票，1代表未持有股票但是冷冻期，2代表未持有股票也未冷冻期
        int[][] dp = new int[n+1][3];


        //初始值
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;



        for (int i=1; i<=n; ++i){//为了保证dp数组下标大于0，i要从1开始
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] - prices[i-1]);
            dp[i][1] = dp[i-1][0] + prices[i-1];
            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1]);
        }

        return Math.max(dp[n][1],dp[n][2]);

    }
}

package org.lyflexi.solutions.dp.stock;

/**
 * @Author: ly
 * @Date: 2024/2/20 17:08
 */

/*
* 188. 买卖股票的最佳时机 IV
给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。


示例 1：
输入：k = 2, prices = [2,4,1]
*
输出：2
解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
*
示例 2：
输入：k = 2, prices = [3,2,6,5,0,3]
输出：7
解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。

* */
public class Solution04_MaxProfitⅣ_LimitKThreeDimension {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        //j代表恰好进行j次交易(完整的一次买卖才算有效的一次)
        //dp[i][j][0]代表当天交易完手里没有股票（新卖出），dp[i][j][0] = max(dp[i-1][j][0],dp[i-1][j][1]+prices[i])
        //dp[i][j][1]代表当天交易完手里还剩1张股票（新买入）,dp[i][j][1] = max(dp[i-1][j][1],dp[i-1][j-1][0]-prices[i])
        int[][][] dp = new int[n+1][k+1][2];
        
        
        //初始值，只要交易次数j为0，则dp数组就为0
        for (int i = 0; i < n+1; i++) {
            for (int s = 0; s < 2; s++) {
                dp[i][0][s] = 0;
            }
        }
        //若第一天进行交易的初始值，交易次数j为1~k
        for (int j = 1; j <=k; j++) {
            dp[0][j][0] = 0;//当天买 当天卖了
            dp[0][j][1] = -prices[0];//当天的最终状态是买入1次
        }


        for (int i=1; i<=n; ++i){//为了兼容数组下标，i要从1开始
            for (int j=1; j<=k; ++j){//为了兼容数组下标，k要从1开始
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i-1]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i-1]);
            }
        }
        return dp[n][k][0];

    }
}

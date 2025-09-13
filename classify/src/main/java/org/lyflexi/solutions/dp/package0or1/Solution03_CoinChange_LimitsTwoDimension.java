package org.lyflexi.solutions.dp.package0or1;



import java.util.Arrays;

/**
 * @Author: ly
 * @Date: 2024/2/21 10:38
 */

/*
 * 若题目除了给出coins外，再给出一个count数组，表示每种金币的数量，那么这种情况下如何求出组成amount金额所需的最少金币数量呢。
 * */
public class Solution03_CoinChange_LimitsTwoDimension {

//将dp表示成一个二维数组，行为金币的种类，列为amount。

//第一行表示，如果现在没有金币，组成amount所需的金币最少数量，
//第二行表示，如果只有一种金币，组成amount所需的金币的最少数量
//.....以此递推，求出dp[coins.length][amount]。即为正确答案。

    //TODO  此题还有待研究
    public int coinChange(int[] coins, int[] count, int amount) {
        //因为涉及最小值比较，初始值必须设置为amount以上的数值，本例选取了amount+1
        int max = amount + 1;
        //下标ij具有实际意义，00赋初值即可不做dp计算，所以数组长度应当多1
        int[][] dp = new int[coins.length + 1][amount + 1];
        //初始化数组
        for (int i = 0; i < coins.length + 1; i++) {

            Arrays.fill(dp[i], max);
            dp[i][0] = 0;
        }
        for (int i = 1; i <= coins.length; ++i) {//横坐标从1开始是为了兼容dp[i-1]和coins[i - 1]
            for (int j = 1; j <= amount; ++j) {//纵坐标为0的情况外面已经赋好了
                if (j < coins[i - 1]) {//此时dp公式不可推
                    dp[i][j] = dp[i - 1][j];
                } else {
                    int top = Math.min(count[i - 1], j / coins[i - 1]);
                    int min = amount + 1;
                    for (int k = 0; k <= top; ++k) {
                        min = Math.min(min, dp[i - 1][j - k * coins[i - 1]] + k);
                    }
                    dp[i][j] = min;
                }
            }
        }

        //为什么要返回dp[coins.length][amount]？  因为dp[0][0]是无效的，当coins的数量为0，目标值amount也未0的时候是没必要计算的
        return dp[coins.length][amount] > amount ? -1 : dp[coins.length][amount];
    }
}

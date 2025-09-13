package org.lyflexi.solutions.dp.package0or1;

import java.util.Arrays;

/**
 * @Author: ly
 * @Date: 2024/2/20 14:37
 */

/*硬币只可以使用1次,求凑成总金额所需的最少的硬币个数
*
*
* 转移方程不变：与Solution01一样*/
public class Solution02_CoinChangeOnlyOnce {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        //此题coin限定为只使用一次，所以dp数值不会超过coins.length
        Arrays.fill(dp, coins.length + 1);
        //凑满0不需要金币，因此是0
        dp[0] = 0;
        //如果硬币每次只能使用1次，那么，再将金币的循环放置在第二层显然是不可取的
        for (int i = 0; i < coins.length; ++i) {
            for (int j = amount; j >= coins[i] ; --j) {
                // TODO ?同时，第二层循环必须是倒序，可以看到当前dp计算依赖于coins[i]，倒序避免硬币被重复使用
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }
        return dp[amount] > coins.length ? -1 : dp[amount];
    }

}

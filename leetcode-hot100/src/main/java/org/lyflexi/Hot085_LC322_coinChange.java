package org.lyflexi;

import java.util.*;

/**
 * 322. 零钱兑换
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount，表示总金额。
 * 
 * 计算并返回可以凑成总金额所需的 最少 的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 
 * 你可以认为每种硬币的数量是无限的。
 * 
 * 示例 1：
 * 
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 
 * 示例 2：
 * 
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 
 * 示例 3：
 * 
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 
 * 提示：
 * 
 * - 1 <= coins.length <= 12
 * 
 * - 1 <= coins[i] <= 2^31 - 1
 * 
 * - 0 <= amount <= 10^4
 */

/**
 * 答疑
 * 
 * 问：为什么不需要写 $dfs(i - 1, c - coins[i])$ 呢？选了一个就不再选了，这样不行吗？
 * 
 * 答：其实我们已经考虑这种情况了，先「选一个」，递归到 $dfs(i, c - coins[i])$，在这个递归中再「不选」，就能递归到 $dfs(i - 1, c - coins[i])$ 了。也就是说，递归两次，我们就能表达出「选了一个就不再选」的逻辑。从这个例子，相信读者能体会到递归所蕴含的强大表达能力。
 */
public class Hot085_LC322_coinChange {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] memo = new int[n][amount + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }

        int ans = dfs(n - 1, amount, coins, memo);
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }

    private int dfs(int i, int c, int[] coins, int[][] memo) {
        if (i < 0) {
            return c == 0 ? 0 : Integer.MAX_VALUE / 2; // 除 2 防止下面 + 1 溢出
        }
        if (memo[i][c] != -1) { // 之前计算过
            return memo[i][c];
        }
        if (c < coins[i]) { // 只能不选
            return memo[i][c] = dfs(i - 1, c, coins, memo);
        }
        // 不选 vs 继续选
        return memo[i][c] = Math.min(dfs(i - 1, c, coins, memo), dfs(i, c - coins[i], coins, memo) + 1);
    }
}

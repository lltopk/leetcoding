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
 * 四、空间优化：一个数组
 * 
 * 好比在一面墙上画画，原来这面墙画的是 $f[i]$，现在要画一副新的画，把原来的画覆盖掉，新的画叫做 $f[i+1]$。
 * 
 * 在循环的过程中：
 * 
 * - 对于 $c<x$ 的状态，转移方程是 $f[i+1][c] = f[i][c]$，这说明原来画的内容保持不变，空间优化后是 $f[c] = f[c]$，这个赋值是多余的。所以可以从 $c=x$ 开始循环。
 * - 对于 $c>= x$ 的状态，转移方程是 $f[i+1][c] = min(f[i][c], f[i+1][c-x]+1)$，其中 $f[i][c]$ 就地取材，$f[i+1][c-x]$ 是新画的内容，从这面墙的下标 $c-x$ 处取到，所以空间优化后就是 $f[c] = min(f[c], f[c - x] + 1)$。
 * 
 * 关于先枚举物品还是先枚举体积的讨论，见 377. 组合总和 Ⅳ 我的题解 中的「答疑」。
 */
public class Hot085_LC322_coinChange4 {
    public int coinChange(int[] coins, int amount) {
        int[] f = new int[amount + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;
        for (int x : coins) {
            for (int c = x; c <= amount; c++) {
                f[c] = Math.min(f[c], f[c - x] + 1);
            }
        }
        int ans = f[amount];
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }
}

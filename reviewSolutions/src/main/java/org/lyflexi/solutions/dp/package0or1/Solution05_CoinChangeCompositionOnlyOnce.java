package org.lyflexi.solutions.dp.package0or1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/2/20 14:05
 */

/*
518. 零钱兑换 II
给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。

假设每一种面额的硬币有无限个。
*
*
*
* 转移方程不变：与Solution03一样
* */
public class Solution05_CoinChangeCompositionOnlyOnce {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int amount = Integer.parseInt(scanner.nextLine());

        int[] coins = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(change(amount, coins));
    }

    // f(0) = 1;
    // 组合数f(target) = Sum(f(target-coins[0]),f(target-coins[1])...,f(target-coins[n-1]))

    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {//由于外层coins顺序确定，因此不会重复计算排列。
            for (int i = coin; i <= amount; i++) {
                dp[i] = dp[i]+dp[i - coin];
            }
        }
        return dp[amount];
    }
}

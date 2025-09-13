package org.lyflexi.solutions.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/2/5 13:51
 */



/*使用最小花费爬楼梯

给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
请你计算并返回达到楼梯顶部的最低花费。


示例 1：
输入：cost = [10,15,20]
输出：15
解释：你将从下标为 1 的台阶开始。
- 支付 15 ，向上爬两个台阶，到达楼梯顶部。
总花费为 15 。

示例 2：
输入：cost = [1,100,1,1,1,100,1,1,100,1]
输出：6
解释：你将从下标为 0 的台阶开始。
- 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
- 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
- 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
- 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
- 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
- 支付 1 ，向上爬一个台阶，到达楼梯顶部。
总花费为 6 。*/


/*

 *
 * 题目要求的是爬出楼梯，因此目标对象是n+1
 *
 * 所以分析出初始值f(1)=0，f(2)=Math.min(cost[0],cost[1])
 *
 * */
public class Solution04_MinCostClimbingStairs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] costs = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(minCostClimbingStairs(costs));
    }
    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n+2];
        dp[1] = 0;
        dp[2] = 0;


        //数学公式：f(x)=Math.min(f(x-1)+cost[x-1],f(x-2)+cost[x-2])，此处的f(x)即目标值，代表到第x阶的最小花费
        for(int i = 3;i<n+1;i++){
            //代码公式：
            dp[i] = Math.min(dp[i-1]+cost[i-2],dp[i-2]+cost[i-3]);
        }

        dp[n+1] = Math.min(dp[n]+cost[n-1],dp[n-1]+cost[n-2]);

        return dp[n+1];
    }
}

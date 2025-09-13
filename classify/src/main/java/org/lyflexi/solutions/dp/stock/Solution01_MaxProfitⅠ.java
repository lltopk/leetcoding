package org.lyflexi.solutions.dp.stock;

/**
 * @Author: ly
 * @Date: 2024/2/20 16:08
 */

/*

121. 买卖股票的最佳时机
给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。




思路：朴素的想法是n^2的复杂度肯定不可取*/
public class Solution01_MaxProfitⅠ {

    //如何去掉二重循环？
    //一次遍历，内部进行if条件判断避免二次遍历

    public int maxProfit(int[] prices) {

        int n = prices.length;
        int minV = Integer.MAX_VALUE;//下面比较最小值，上面初始化为大值

        int answer = Integer.MIN_VALUE;//下面求最大值，上面初始化为小值

        for (int i = 0; i < n; i++) {



            minV = Math.min(minV,prices[i]);//保存低点


            //Math函数就相当于if判断
            answer = Math.max(answer,prices[i]-minV);//求最大值



        }
        return answer;
    }
}

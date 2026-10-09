package org.lyflexi;

import java.util.*;

/**
 * 121. 买卖股票的最佳时机
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * 
 * 示例 1：
 * 
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 
 * 示例 2：
 * 
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 * 
 * 提示：
 * 
 * - 1 <= prices.length <= 10^5
 * 
 * - 0 <= prices[i] <= 10^4
 */

/**
 * 低价买入，高价卖出。
 * 
 * 从左到右枚举卖出价格 $prices[i]$。要想获得最大利润，哪天买入最好？在股票价格最低的那天买入。
 * 
 * 注意，买入日期必须在卖出日期之前，所以我们求的是从 $prices[0]$ 到 $prices[i-1]$ 的最小值，这可以用一个变量 $minPrice$ 维护。
 * 
 * 由于只能买卖一次，所以在遍历中，计算 $prices[i]-minPrice$ 的最大值，就是答案。
 * 
 * 注：下面代码中，我们先更新 $ans$，再更新 $minPrice$，以保证 $minPrice$ 在 $prices[i]$ 之前。请读者思考：更新顺序能否交换？如果一定要交易一次，即使亏钱也要交易，更新顺序能否交换？
 */
public class Hot077_LC121_maxProfit {
    public int maxProfit(int[] prices) {
        int ans = 0;
        int minPrice = prices[0];
        for (int p : prices) {
            ans = Math.max(ans, p - minPrice);
            minPrice = Math.min(minPrice, p);
        }
        return ans;
    }
}

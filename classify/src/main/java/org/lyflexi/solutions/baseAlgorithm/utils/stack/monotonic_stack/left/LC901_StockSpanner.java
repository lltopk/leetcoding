package org.lyflexi.solutions.baseAlgorithm.utils.stack.monotonic_stack.left;

/**
 * 901. 股票价格跨度
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 设计一个算法收集某些股票的每日报价，并返回该股票当日价格的 跨度 。
 *
 * 当日股票价格的 跨度 被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 *
 * 例如，如果未来 7 天股票的价格是 [100,80,60,70,60,75,85]，那么股票跨度将是 [1,1,1,2,1,4,6] 。
 *
 * 实现 StockSpanner 类：
 *
 * StockSpanner() 初始化类对象。
 * int next(int price) 给出今天的股价 price ，返回该股票当日价格的 跨度 。
 *
 *
 * 示例：
 *
 * 输入：
 * ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
 * [[], [100], [80], [60], [70], [60], [75], [85]]
 * 输出：
 * [null, 1, 1, 1, 2, 1, 4, 6]
 *
 * 解释：
 * StockSpanner stockSpanner = new StockSpanner();
 * stockSpanner.next(100); // 返回 1
 * stockSpanner.next(80);  // 返回 1
 * stockSpanner.next(60);  // 返回 1
 * stockSpanner.next(70);  // 返回 2
 * stockSpanner.next(60);  // 返回 1
 * stockSpanner.next(75);  // 返回 4 ，因为截至今天的最后 4 个股价 (包括今天的股价 75) 都小于或等于今天的股价。
 * stockSpanner.next(85);  // 返回 6
 *
 * 提示：
 *
 * 1 <= price <= 105
 * 最多调用 next 方法 104 次
 */


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 单调栈
 */
public class LC901_StockSpanner {
    Deque<int[]> sk;
    public LC901_StockSpanner() {
        sk = new ArrayDeque<>();
    }

    /**
     单调递减栈求left[], 但我们只计算比当前小的(出栈次数)

     但栈被动态调整之后, 出栈次数会不准(肯定偏小)

     根据前缀和思想, 前面的跨度会累加后再次放入栈中, 无论累加值是否是栈顶, 不会丢失答案
     */
    public int next(int price) {
        int ret = 1;//包括自己
        while(! sk.isEmpty() && sk.peek()[0] <= price){
            //前缀和思想
            ret += sk.pop()[1];
        }
        sk.push(new int[]{price, ret});
        return ret;
    }
}

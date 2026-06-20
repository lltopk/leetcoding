package org.lyflexi.solutions.baseAlgorithm.utils.stack.monotonic_stack.right;

/**
 * 739. 每日温度
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 *
 *
 * 示例 1:
 *
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 *
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 *
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *
 *
 * 提示：
 *
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,014,158/1.5M
 * 通过率
 * 69.7%
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 单调栈, 从右向左遍历, 目的是输出的right[]数组
 */
public class LC739_dailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ret = new int[temperatures.length];
        Deque<Integer> sk = new ArrayDeque<>();
        for(int i = temperatures.length - 1; i>=0; i--){
            while(! sk.isEmpty() && temperatures[sk.peek()] <= temperatures[i]){
                sk.pop();
            }
            if(!sk.isEmpty()){
                ret[i] = sk.peek() - i;
            }
            sk.push(i);
        }
        return ret;
    }
}

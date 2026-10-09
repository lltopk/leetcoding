package org.lyflexi;

import java.util.*;

/**
 * 739. 每日温度
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 
 * 示例 1:
 * 
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 
 * 示例 2:
 * 
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 
 * 示例 3:
 * 
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 * 
 * 提示：
 * 
 * - 1 <= temperatures.length <= 10^5
 * 
 * - 30 <= temperatures[i] <= 100
 */

/**
 * 写法二：从左到右
 * 
 * 栈中记录还没算出下一个更大元素的那些数的下标。
 * 
 * 相当于栈是一个 todolist，在循环的过程中，现在还不知道答案是多少，在后面的循环中会算出答案。
 */
public class Hot072_LC739_dailyTemperatures3 {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        int[] st = new int[n]; // 数组模拟，效率更高
        int top = -1;
        for (int i = 0; i < n; i++) {
            int t = temperatures[i];
            while (top >= 0 && t > temperatures[st[top]]) {
                int j = st[top--];
                ans[j] = i - j;
            }
            st[++top] = i;
        }
        return ans;
    }
}

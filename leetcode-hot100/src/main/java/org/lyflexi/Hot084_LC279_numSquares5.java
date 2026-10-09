package org.lyflexi;

import java.util.*;

/**
 * 279. 完全平方数
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * 
 * 示例 1：
 * 
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 
 * 示例 2：
 * 
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 * 
 * 提示：
 * 
 * - 1 <= n <= 10^4
 */

/**
 * 四、BFS 最短路
 * 
 * 设当前组成的完全平方数之和为 $s$。添加一个完全平方数 $x^2$ 后，$s$ 变成了 $s + x^2$。
 * 
 * 把完全平方数之和当作节点编号，从 $s$ 到 $s + x^2$ 连一条有向边，我们可以得到一张有向图。
 * 
 * 本题相当于：
 * 
 * - 计算从起点 $0$ 到终点 $n$ 的最短路长度。
 * 
 * 这可以用 BFS 解决。
 * 
 * 下面代码用双数组实现 BFS，原理请看【基础算法精讲 13】。
 */
public class Hot084_LC279_numSquares5 {
    public int numSquares(int n) {
        List<Integer> q = Arrays.asList(0);
        boolean[] vis = new boolean[n + 1];
        vis[0] = true;

        for (int step = 0; ; step++) {
            List<Integer> nxt = new ArrayList<>();
            for (int s : q) {
                if (s == n) {
                    return step;
                }
                for (int i = 1; s + i * i <= n; i++) {
                    int t = s + i * i;
                    if (!vis[t]) { // 之前没有访问过
                        vis[t] = true; // 避免重复访问
                        nxt.add(t);
                    }
                }
            }
            q = nxt;
        }
    }
}

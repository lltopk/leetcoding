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
 * 二、1:1 翻译成递推
 * 
 * 按照视频中的方法，我们可以去掉递归中的「递」，只保留「归」的部分，即自底向上计算。
 * 
 * 具体来说，$f[i][j]$ 的定义和 $dfs(i,j)$ 的定义是一样的，都表示从前 $i$ 个完全平方数中选一些数（可以重复选），满足元素和恰好等于 $j$，最少要选的数字个数。
 * 
 * 相应的递推式（状态转移方程）也和 $dfs$ 一样：
 * 
 * $$
 * f[i][j] =
 * \begin{cases}
 * f[i - 1][j], & j < i^2     \\
 * min(f[i - 1][j], f[i][j - i^2] + 1), & j>= i^2     \\
 * \end{cases}
 * $$
 * 
 * 初始值 $f[0][0]=0,\ f[0][j]=\infty\ (j>0)$，翻译自递归边界 $dfs(0,0)=0$ 和 $dfs(0,j) = \infty\ (j>0)$。
 * 
 * 答案为 $f[<=ft\lfloor\sqrt n\right\rfloor][n]$，翻译自递归入口 $dfs(<=ft\lfloor\sqrt n\right\rfloor, n)$。
 */
public class Hot084_LC279_numSquares2 {
    private static final int MX = 10001;
    private static final int[][] f = new int[101][MX];

    static {
        Arrays.fill(f[0], Integer.MAX_VALUE);
        f[0][0] = 0;
        for (int i = 1; i * i < MX; i++) {
            for (int j = 0; j < MX; j++) {
                if (j < i * i) {
                    f[i][j] = f[i - 1][j]; // 只能不选
                } else {
                    f[i][j] = Math.min(f[i - 1][j], f[i][j - i * i] + 1); // 不选 vs 选
                }
            }
        }
    }

    public int numSquares(int n) {
        return f[(int) Math.sqrt(n)][n]; // 也可以写 f[100][n]
    }
}

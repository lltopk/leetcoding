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
 * 答疑
 * 
 * 问：为什么本题的递归边界是 $i=0$？我之前做的那些 DP 题的递归边界都是 $i<0$。
 * 
 * 答：本题最小的完全平方数是 $1^2$，递归到 $i=0$ 就说明所有完全平方数都考虑完了。其他题目最小的数一般是下标为 $0$ 的数，递归到 $i<0$ 就说明所有的数都考虑完了。
 * 
 * 问：在 Java 等语言中，为什么可以返回 Integer.MAX_VALUE，这不会导致加法溢出吗？
 * 
 * 答：通常来说要返回 Integer.MAX_VALUE / 2。如果从非法状态转移过来，这样写可以避免加法溢出。但本题比较特殊，由于 $1$ 是完全平方数，一个数一定可以分解为若干完全平方数的和。顺着 dfs(i, j - i * i) 往下递归，一定可以递归到 $j=0$ 的合法状态。如果实在无法理解，写 Integer.MAX_VALUE / 2 也没问题。
 */
public class Hot084_LC279_numSquares {
    private static final int[][] memo = new int[101][10001];

    static {
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
    }

    private static int dfs(int i, int j) {
        if (i == 0) {
            return j == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (memo[i][j] != -1) { // 之前计算过
            return memo[i][j];
        }
        if (j < i * i) {
            return memo[i][j] = dfs(i - 1, j); // 只能不选
        }
        return memo[i][j] = Math.min(dfs(i - 1, j), dfs(i, j - i * i) + 1); // 不选 vs 选
    }

    public int numSquares(int n) {
        return dfs((int) Math.sqrt(n), n);
    }
}

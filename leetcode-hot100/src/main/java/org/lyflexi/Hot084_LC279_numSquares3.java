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
 * 三、空间优化
 * 
 * 观察上面的状态转移方程，在计算 $f[i]$ 时，只会用到 $f[i-1]$，不会用到比 $i-1$ 更早的状态。
 * 
 * 因此可以去掉第一个维度，反复利用同一个长为 $N+1$ 的一维数组。
 * 
 * 递推式简化为，当 $j>= i^2$ 时，计算
 * 
 * $$
 * f[j] = min(f[j], f[j - i^2] + 1)
 * $$
 * 
 * 注意 $j<i^2$ 的递推式简化为 $f[j]=f[j]$，无需计算。
 * 
 * 初始值 $f[0]=0,\ f[j]=\infty\ (j>0)$。
 * 
 * 答案为 $f[n]$。
 * 
 * 关于循环的顺序，见 视频讲解。
 */
public class Hot084_LC279_numSquares3 {
    private static final int MX = 10001;
    private static final int[] f = new int[MX];
    private static boolean initialized = false;

    // 这样写比 static block 更快
    public Hot084_LC279_numSquares3() {
        if (initialized) {
            return;
        }
        initialized = true;

        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for (int i = 1; i * i < MX; i++) {
            for (int j = i * i; j < MX; j++) {
                f[j] = Math.min(f[j], f[j - i * i] + 1); // 不选 vs 选
            }
        }
    }

    public int numSquares(int n) {
        return f[n];
    }
}

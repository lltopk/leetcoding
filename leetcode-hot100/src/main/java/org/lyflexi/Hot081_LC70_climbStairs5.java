package org.lyflexi;

import java.util.*;

/**
 * 70. 爬楼梯
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 
 * 示例 1：
 * 
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 
 * 示例 2：
 * 
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * 
 * 提示：
 * 
 * - 1 <= n <= 45
 */

/**
 * 六、矩阵快速幂优化
 * 
 * 把状态转移方程用矩阵乘法表示，即
 * 
 * $$
 * \begin{bmatrix}
 * f[i] \\
 * f[i-1] \\
 * \end{bmatrix}
 * = \begin{bmatrix}
 * 1 & 1 \\
 * 1 & 0 \\
 * \end{bmatrix}
 * \begin{bmatrix}
 * f[i-1] \\
 * f[i-2] \\
 * \end{bmatrix}
 * $$
 * 
 * 把上式中的三个矩阵分别记作 $F[i],M,F[i-1]$，即
 * 
 * $$
 * F[i] = M× F[i-1]
 * $$
 * 
 * 那么有
 * 
 * $$
 * \begin{aligned}
 * F[n] &= M× F[n-1]      \\
 * &= M× M× F[n-2]        \\
 * &= M× M× M×  F[n-3]        \\
 * &\ \ \vdots  \\
 * &= M^n× F[0] \\
 * \end{aligned}
 * $$
 * 
 * 其中 $M^n$ 可以用快速幂计算，原理请看【图解】一张图秒懂快速幂。
 * 
 * 初始值
 * 
 * $$
 * F[0] = \begin{bmatrix}
 * f[0] \\
 * f[-1] \\
 * \end{bmatrix}
 * = \begin{bmatrix}
 * 1 \\
 * 0 \\
 * \end{bmatrix}
 * $$
 * 
 * 答案为 $f[n]$，即 $F[n]$ 的第一项。
 */
public class Hot081_LC70_climbStairs5 {
    public int climbStairs(int n) {
        int[][] m = {
            {1, 1},
            {1, 0},
        };
        int[][] f0 = {{1}, {0}};
        int[][] fn = powMul(m, n, f0);
        return fn[0][0];
    }

    // a^n * f0
    private int[][] powMul(int[][] a, int n, int[][] f0) {
        int[][] res = f0;
        while (n > 0) {
            if ((n & 1) > 0) {
                res = mul(a, res);
            }
            a = mul(a, a);
            n >>= 1;
        }
        return res;
    }

    // 返回矩阵 a 和矩阵 b 相乘的结果
    private int[][] mul(int[][] a, int[][] b) {
        int[][] c = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int k = 0; k < a[i].length; k++) {
                if (a[i][k] == 0) {
                    continue;
                }
                for (int j = 0; j < b[k].length; j++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }
}

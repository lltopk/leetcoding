package org.lyflexi.solutions.baseAlgorithm.recursive.recursion_sub_conquer_question;

/**
 * 50. Pow(x, n)
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 *
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 *
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 *
 *
 * 提示：
 *
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * n 是一个整数
 * 要么 x 不为零，要么 n > 0 。
 * -104 <= xn <= 104
 *
 */
public class LC50_myPow2 {
    /**
     * 递归
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        //x^(-n) = (1/x)^n
        if (n < 0) {
            return pow(1 / x, -(long) n);
        }
        return pow(x, n);
    }

    /**
     * 当 n & 1 == 0 , pow(x, n) = (pow(x, n/2))^2
     * 当 n & 1 == 1 , pow(x, n) = (pow(x, n/2))^2 * x
     * @param x
     * @param n
     * @return
     */
    private double pow(double x, long n) {
        if (n == 0) {
            return 1;
        }
        double res = pow(x, n / 2);
        res *= res;
        if (n % 2 != 0) {
            res *= x;
        }
        return res;
    }
}

package org.lyflexi.solutions.baseAlgorithm.mod;

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
 */

/**
 * 快速幂
 */
public class LC50_myPow {
    public double myPow(double x, int N) {
        double ans = 1;
        long n = N;
        //x^(-n) = (1/x)^n
        if(n<0){
            x = 1/x;
            n = -n;
        }

        //快速幂, 将n用二进制表示, 例如求pow(x, 13), 13表示为二进制是1101
        //计算过程为, 从低到高枚举二进制的1位, 并以该位代表的实际二进制值作为幂, 累乘即x * x^4 * x^8
        while(n!=0){
            if((n & 1) == 1){
                ans *= x;
            }
            //以任意位代表的实际二进制值作为幂, 如1, 2, 4, 8
            x *=x;
            n >>= 1;
        }

        return ans;
    }
}

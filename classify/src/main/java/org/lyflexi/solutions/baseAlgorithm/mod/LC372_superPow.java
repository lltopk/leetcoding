package org.lyflexi.solutions.baseAlgorithm.mod;

/**
 * 372. 超级次方
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
 *
 *
 *
 * 示例 1：
 *
 * 输入：a = 2, b = [3]
 * 输出：8
 * 示例 2：
 *
 * 输入：a = 2, b = [1,0]
 * 输出：1024
 * 示例 3：
 *
 * 输入：a = 1, b = [4,3,3,8,5,2]
 * 输出：1
 * 示例 4：
 *
 * 输入：a = 2147483647, b = [2,0,0]
 * 输出：1198
 *
 *
 * 提示：
 *
 * 1 <= a <= 231 - 1
 * 1 <= b.length <= 2000
 * 0 <= b[i] <= 9
 * b 不含前导 0
 */

/**
 * 快速幂+取模
 */
public class LC372_superPow {
    static final int MOD = 1337;
    public int superPow(int a, int[] b) {
        int ans = 1;
        for(int i = b.length - 1; i>=0 ;i--){
            ans = ans * qpow(a, b[i]) % MOD;
            //逆序变量b[i]的过程中, 新的指数会扩大10倍
            a = qpow(a, 10);
        }

        return ans;
    }

    private int qpow(long x, int n ){
        long ans = 1;
        while(n!=0){
            if((n & 1) ==1){
                ans = ans * x % MOD;
            }


            x = x * x % MOD;
            n = n>>1;
        }

        return (int)ans;
    }
}

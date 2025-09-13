package org.lyflexi.solutions.recursion;

/**
 * @Description:
 * @Author: lyflexi
 * @project: leetcode-java
 * @Date: 2024/12/7 16:46
 */

/**
 * 求解最大公约数, 同时计算出满足如下等式的系数x,y
 * ax+by = gcd
 *
 * 递归+递推！光有递归还不行，还要赋予其数学推导的语义
 */
public class Solution02_HSBC_ExtendedGCD {
    public static void main(String[] args) {
        int[] gcdArray = extendedGCD(21, 14);
        for (int v : gcdArray) {
            System.out.println(v);
        }
    }

    /**
     * 默认a>b
     * @param a
     * @param b
     * @return 三元组 g,x,y
     *
     * 递归形式为int[] gcdR = extendedGCD(b, a % b)，假设我们已经找到了整数x,y满足bx + (a%b)y = g(b,a%b),
     * 求新一轮的g`,x`,y`, 数学推导如下：
     * 上述递归公式bx + (a%b)y = g(b,a%b)恒等于g(a,b)，记为常量g
     * bx + (a-|a/b|*b)y = g,其中|a/b|是整除
     * ay - b(x-|a/b|*y) = g
     *
     * 因此，新一轮的g`等于g
     * 新一轮的x = 上一轮的y = gcdR[2]
     * 新一轮的y = 上一轮的x-|a/b|*上一轮的y = gcdR[1] - |a/b|*gcdR[2]
     *
     * tips: 递归三要素：
     * 1. 终止条件
     * 2. 递归调用(递)
     * 3. 返回结果(归)
     */
    private static int[] extendedGCD(int a, int b) {
        //1. 终止条件
        if (b == 0) {
            //虽然作为递归出口，但是{a,1,0}并不具备一般意义，因此下文递归之后仍然需要继续推导
            return new int[]{a,1,0};
        }
        //2. 递归调用
        int[] gcdR = extendedGCD(b, a % b);

        //新一轮的g恒等于常量 gcdR[0];
        int g = gcdR[0];
        //新一轮的x = 上一轮的y = gcdR[2]
        int x = gcdR[2];
        //新一轮的y = 上一轮的x-|a/b|*上一轮的y = gcdR[1] - |a/b|*gcdR[2]
        int y = gcdR[1] - (a/b)*gcdR[2];

        //3. 返回结果
        return new int[]{g,x, y};
    }
}

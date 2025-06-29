package com.lyflexi.foreigncompanyinterview.coding;

/**
 * @Description:
 * @Author: lyflexi
 * @project: leetcode-java
 * @Date: 2024/12/7 16:46
 */

/**
 * 求解最大公约数
 * 辗转相除法
 */
public class Q1_GCD {
    public static void main(String[] args) {
        int gcd = gcdV2(4, 2);
        System.out.println(gcd);

    }

    /**
     * 辗转相除法
     * 默认a>b
     * @param a
     * @param b
     * @return
     */
    private static int gcdV1(int a, int b) {
        if (b == 0) {
            return a;
        }
        while (b != 0) {
            int mod = a % b;
            a = b;
            b = mod;
        }
        return a;
    }

    /**
     * 递归求gcd
     * @param a
     * @param b
     * @return
     *
     * 递归三要素：
     * 1. 终止条件
     * 2. 递归调用（递）
     * 3. 返回结果（归）
     */
    public static int gcdV2(int a, int b) {
        //1. 终止条件
        if (b == 0) {
            return a;
        } else {//2. 递归调用  //3. 返回结果
            return gcdV2(b, a % b);
        }
    }
}

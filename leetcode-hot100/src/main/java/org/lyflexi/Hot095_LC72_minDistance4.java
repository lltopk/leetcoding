package org.lyflexi;

import java.util.*;

/**
 * 72. 编辑距离
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 
 * 你可以对一个单词进行如下三种操作：
 * 
 * - 插入一个字符
 * 
 * - 删除一个字符
 * 
 * - 替换一个字符
 * 
 * 示例 1：
 * 
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 
 * 示例 2：
 * 
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * 
 * 提示：
 * 
 * - 0 <= word1.length, word2.length <= 500
 * 
 * - word1 和 word2 由小写英文字母组成
 */

/**
 * 四、空间优化：一个数组
 */
public class Hot095_LC72_minDistance4 {
    public int minDistance(String text1, String text2) {
        char[] t = text2.toCharArray();
        int m = t.length;
        int[] f = new int[m + 1];
        for (int j = 0; j < m; j++) {
            f[j + 1] = j + 1;
        }
        for (char x : text1.toCharArray()) {
            int pre = f[0];
            f[0]++; // f[0] = i + 1
            for (int j = 0; j < m; j++) {
                int tmp = f[j + 1];
                f[j + 1] = x == t[j] ? pre : Math.min(Math.min(f[j + 1], f[j]), pre) + 1;
                pre = tmp;
            }
        }
        return f[m];
    }
}

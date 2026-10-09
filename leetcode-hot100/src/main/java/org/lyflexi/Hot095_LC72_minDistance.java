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
 * 一、递归搜索 + 保存计算结果 = 记忆化搜索
 */
public class Hot095_LC72_minDistance {
    private char[] s, t;
    private int[][] memo;

    public int minDistance(String text1, String text2) {
        s = text1.toCharArray();
        t = text2.toCharArray();
        int n = s.length;
        int m = t.length;
        memo = new int[n][m];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示还没有计算过
        }
        return dfs(n - 1, m - 1);
    }

    private int dfs(int i, int j) {
        if (i < 0) {
            return j + 1;
        }
        if (j < 0) {
            return i + 1;
        }
        if (memo[i][j] != -1) { // 之前算过了
            return memo[i][j];
        }
        if (s[i] == t[j]) {
            return memo[i][j] = dfs(i - 1, j - 1);
        }
        return memo[i][j] = Math.min(Math.min(dfs(i - 1, j), dfs(i, j - 1)), dfs(i - 1, j - 1)) + 1;
    }
}

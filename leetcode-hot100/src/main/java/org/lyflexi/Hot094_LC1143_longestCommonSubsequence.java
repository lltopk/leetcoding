package org.lyflexi;

import java.util.*;

/**
 * 1143. 最长公共子序列
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 
 * - 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * 
 * 示例 1：
 * 
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 
 * 示例 2：
 * 
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 
 * 示例 3：
 * 
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 * 
 * 提示：
 * 
 * - 1 <= text1.length, text2.length <= 1000
 * 
 * - text1 和 text2 仅由小写英文字符组成。
 */

/**
 * 一、递归搜索 + 保存计算结果 = 记忆化搜索
 */
public class Hot094_LC1143_longestCommonSubsequence {
    private char[] s, t;
    private int[][] memo;

    public int longestCommonSubsequence(String text1, String text2) {
        s = text1.toCharArray();
        t = text2.toCharArray();
        int n = s.length;
        int m = t.length;
        memo = new int[n][m];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        return dfs(n - 1, m - 1);
    }

    private int dfs(int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j]; // 之前计算过
        }
        if (s[i] == t[j]) {
            return memo[i][j] = dfs(i - 1, j - 1) + 1;
        }
        return memo[i][j] = Math.max(dfs(i - 1, j), dfs(i, j - 1));
    }
}

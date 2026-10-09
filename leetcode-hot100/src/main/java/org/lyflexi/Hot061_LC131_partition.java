package org.lyflexi;

import java.util.*;

/**
 * 131. 分割回文串
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 
 * 示例 1：
 * 
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 
 * 示例 2：
 * 
 * 输入：s = "a"
 * 输出：[["a"]]
 * 
 * 提示：
 * 
 * - 1 <= s.length <= 16
 * 
 * - s 仅由小写英文字母组成
 */

/**
 * 答疑
 * 
 * 问：代码遇到回文串就加入 $path$，如何保证这种分割方案一定合法？
 * 
 * 答：如果最后一个字符串不是回文串，我们不会递归到 $i=n$ 的边界，不会把不合法的分割加入答案。
 */
public class Hot061_LC131_partition {
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs(0, 0, s, path, ans);
        return ans;
    }

    // 现在 s 未被分割的部分为 [start, n-1]
    // 当前位于下标 i，讨论是否在 i 和 i+1 之间切一刀
    private void dfs(int i, int start, String s, List<String> path, List<List<String>> ans) {
        if (i == s.length()) { // s 分割完毕
            ans.add(new ArrayList<>(path)); // 复制 path
            return;
        }

        // 不分割
        if (i < s.length() - 1) { // i=n-1 时必须分割（这是最后一段），i<n-1 时才可以不分割
            dfs(i + 1, start, s, path, ans);
        }

        // 分割，那么得到子串 [start, i]
        if (isPalindrome(s, start, i)) { // 判断子串 [start, i] 是不是回文串
            path.add(s.substring(start, i + 1));
            // 现在 s 未被分割的部分为 [i+1, n-1]
            dfs(i + 1, i + 1, s, path, ans);
            path.remove(path.size() - 1); // path.remove(path.size() - 1);
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}

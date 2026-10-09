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
 * 方法二：答案的视角（枚举子串结束位置）
 */
public class Hot061_LC131_partition2 {
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs(0, s, path, ans);
        return ans;
    }

    // 现在 s 未被分割的部分为 [i, n-1]
    // 枚举下一刀切在哪
    private void dfs(int i, String s, List<String> path, List<List<String>> ans) {
        if (i == s.length()) { // s 分割完毕
            ans.add(new ArrayList<>(path)); // 复制 path
            return;
        }
        for (int j = i; j < s.length(); j++) { // 枚举子串的结束位置
            if (isPalindrome(s, i, j)) { // 判断 [i, j] 是不是回文串
                path.add(s.substring(i, j + 1)); // 分割！
                // 现在 s 未被分割的部分为 [j+1, n-1]
                dfs(j + 1, s, path, ans);
                path.remove(path.size() - 1); // path.remove(path.size() - 1);
            }
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

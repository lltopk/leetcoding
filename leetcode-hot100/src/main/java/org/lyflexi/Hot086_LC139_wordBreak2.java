package org.lyflexi;

import java.util.*;

/**
 * 139. 单词拆分
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 * 
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * 
 * 示例 1：
 * 
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * 
 * 示例 2：
 * 
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 *      注意，你可以重复使用字典中的单词。
 * 
 * 示例 3：
 * 
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * 
 * 提示：
 * 
 * - 1 <= s.length <= 300
 * 
 * - 1 <= wordDict.length <= 1000
 * 
 * - 1 <= wordDict[i].length <= 20
 * 
 * - s 和 wordDict[i] 仅由小写英文字母组成
 * 
 * - wordDict 中的所有字符串 互不相同
 */

/**
 * 答疑
 * 
 * 问：能不能外层循环枚举 $words$，内层循环枚举长度？类似完全背包的写法。
 * 
 * 答：不能。完全背包是同一个物品连续选择，然后就再也不选这个物品了。本题可以交替选。比如 $s$ 是 ABA 型，如果用完全背包的写法，只能枚举 AAB、ABB 这类连续的字符串组合，无法枚举到 ABA 这样的字符串组合。
 */
public class Hot086_LC139_wordBreak2 {
    public boolean wordBreak(String s, List<String> wordDict) {
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }
        Set<String> words = new HashSet<>(wordDict);

        int n = s.length();
        boolean[] f = new boolean[n + 1];
        f[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= Math.max(i - maxLen, 0); j--) {
                if (f[j] && words.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }
}

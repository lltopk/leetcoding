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
 * 三、递归搜索 + 保存递归返回值 = 记忆化搜索
 * 
 * 考虑到整个递归过程中有大量重复递归调用（递归入参相同）。由于递归函数没有副作用，同样的入参无论计算多少次，算出来的结果都是一样的，因此可以用记忆化搜索来优化：
 * 
 * - 如果一个状态（递归入参）是第一次遇到，那么可以在返回前，把状态及其结果记到一个 $memo$ 数组中。
 * - 如果一个状态不是第一次遇到（$memo$ 中保存的结果不等于 $memo$ 的初始值），那么可以直接返回 $memo$ 中保存的结果。
 * 
 * 注意：$memo$ 数组的初始值一定不能等于要记忆化的值！例如初始值设置为 $0$（表示 $false$），并且要记忆化的 $dfs(i)$ 也等于 $0$（表示 $false$），那就没法判断 $0$ 到底表示第一次遇到这个状态，还是表示之前遇到过了，从而导致记忆化失效。一般把初始值设置为 $-1$。
 * Python 用户可以无视上面这段，直接用 @cache 装饰器。
 * 
 * 具体请看视频讲解 动态规划入门：从记忆化搜索到递推，其中包含把记忆化搜索 1:1 翻译成递推的技巧。
 */
public class Hot086_LC139_wordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }
        Set<String> words = new HashSet<>(wordDict);

        int n = s.length();
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1); // -1 表示没有计算过
        return dfs(n, maxLen, s, words, memo) == 1;
    }

    private int dfs(int i, int maxLen, String s, Set<String> words, int[] memo) {
        if (i == 0) { // 成功拆分！
            return 1;
        }
        if (memo[i] != -1) { // 之前计算过
            return memo[i];
        }
        for (int j = i - 1; j >= Math.max(i - maxLen, 0); j--) {
            if (words.contains(s.substring(j, i)) && dfs(j, maxLen, s, words, memo) == 1) {
                return memo[i] = 1; // 记忆化
            }
        }
        return memo[i] = 0; // 记忆化
    }
}

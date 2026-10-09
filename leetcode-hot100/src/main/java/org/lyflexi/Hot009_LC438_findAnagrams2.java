package org.lyflexi;

import java.util.*;

/**
 * 438. 找到字符串中所有字母异位词
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 
 * 示例 1:
 * 
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * 
 *  示例 2:
 * 
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * 
 * 提示:
 * 
 * - 1 <= s.length, p.length <= 3 * 10^4
 * 
 * - s 和 p 仅包含小写字母
 */

/**
 * 答疑
 * 
 * 问：为什么内层循环只判断了字母 $c$ 的出现次数，而不是每种字母的出现次数？
 * 
 * 答：如果字母 $c$ 进入窗口后，窗口不合法（某个 $cnt[x] < 0$），那么罪魁祸首是谁？由于在之前的循环中，我们已经把窗口变成合法的了，所以只能是刚进入窗口的字母 $c$ 导致窗口不合法，其余字母都满足 $cnt[x] >= 0$，所以只需判断字母 $c$ 的出现次数。
 */
public class Hot009_LC438_findAnagrams2 {
    public List<Integer> findAnagrams(String s, String p) {
        // 统计 p 的每种字母的出现次数
        int[] cnt = new int[26]; 
        for (char c : p.toCharArray()) {
            cnt[c - 'a']++;
        }

        List<Integer> ans = new ArrayList<>();
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            int c = s.charAt(right) - 'a';
            cnt[c]--; // 右端点字母进入窗口
            while (cnt[c] < 0) { // 字母 c 太多了
                cnt[s.charAt(left) - 'a']++; // 左端点字母离开窗口
                left++;
            }
            if (right - left + 1 == p.length()) { // t 和 p 的每种字母的出现次数都相同（证明见上）
                ans.add(left); // t 左端点下标加入答案
            }
        }
        return ans;
    }
}

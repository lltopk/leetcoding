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
 * 方法一：定长滑窗
 * 
 * 原理请看【套路】教你解决定长滑窗！适用于所有定长滑窗题目！。
 * 
 * 用滑动窗口枚举 $s$ 的所有长为 $n$ 的子串 $t$。在滑的同时，维护 $t$ 的每种字母的出现次数。
 * 
 * 如果 $t$ 的每种字母的出现次数，和 $p$ 的每种字母的出现次数都相同，那么 $t$ 是 $p$ 的异位词，把 $t$ 左端点下标加入答案。
 */
public class Hot009_LC438_findAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        // 统计 p 的每种字母的出现次数
        int[] cntP = new int[26];
        for (char c : p.toCharArray()) {
            cntP[c - 'a']++; // 统计 p 的字母
        }

        List<Integer> ans = new ArrayList<>();
        int[] cntS = new int[26]; // 统计 s 的长为 p.length() 的子串 t 的每种字母的出现次数
        for (int right = 0; right < s.length(); right++) {
            cntS[s.charAt(right) - 'a']++; // 右端点字母进入窗口
            int left = right - p.length() + 1;
            if (left < 0) { // 窗口长度不足 p.length()
                continue;
            }
            if (Arrays.equals(cntS, cntP)) { // t 和 p 的每种字母的出现次数都相同
                ans.add(left); // t 左端点下标加入答案
            }
            cntS[s.charAt(left) - 'a']--; // 左端点字母离开窗口
        }
        return ans;
    }
}

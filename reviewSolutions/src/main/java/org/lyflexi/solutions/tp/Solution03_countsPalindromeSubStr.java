package org.lyflexi.solutions.tp;

/**
 * @Author: ly
 * @Date: 2024/3/23 22:18
 */

/*
* 647. 回文子串 双指针（中心扩散法）
给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
回文字符串 是正着读和倒过来读一样的字符串。
子字符串 是字符串中的由连续字符组成的一个序列。
具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

示例 1：
输入：s = "abc"
输出：3
解释：三个回文子串: "a", "b", "c"
*
示例 2：
输入：s = "aaa"
输出：6
解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
* */
public class Solution03_countsPalindromeSubStr {
    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += countPalindromes(s, i, i); // 以当前字符为中心的回文串（奇数长度）
            count += countPalindromes(s, i, i + 1); // 以当前字符和下一个字符之间的空隙为中心的回文串（偶数长度）
        }
        return count;
    }
    // 计算以指定位置为中心的回文串数量
    private int countPalindromes(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }
}

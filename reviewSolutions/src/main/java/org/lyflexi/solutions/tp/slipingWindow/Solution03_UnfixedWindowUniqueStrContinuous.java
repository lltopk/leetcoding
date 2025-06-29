package org.lyflexi.solutions.tp.slipingWindow;

import java.util.*;

/**
 * @Author: ly
 * @Date: 2024/2/4 10:19
 */

/*
3. 无重复字符的最长子串


给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:
输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

示例 2:
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。*/
public class Solution03_UnfixedWindowUniqueStrContinuous {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(lengthOfLongestSubstring(s));
    }
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occupy = new HashSet<>();

        int left = 0, right = 1, ans = 0;
        occupy.add(s.charAt(0));
        while(right<n){
            if (occupy.contains(s.charAt(right))) {
                //occupy清空为下一轮比较做准备
                occupy.clear();
                //左指针移动1位
                left ++;
                //右指针回来
                right = left;
            }

            // 正常情况下，不断地移动右指针
            occupy.add(s.charAt(right));
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }
}

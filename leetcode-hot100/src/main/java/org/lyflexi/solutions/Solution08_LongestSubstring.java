package org.lyflexi.solutions;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/2/4 10:19
 */

/*无重复字符的最长子串

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
public class Solution08_LongestSubstring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(lengthOfLongestSubstring(s));
    }
    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();

        HashMap<Character, Integer> map = new HashMap<>();

        int left = 0;//右指针不用定义，把右指针当作i即可
        int ans = 1;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            if (map.containsKey(c)){
                //字串必须连续，left要跳跃，map要清空
                left=i;
                map.clear();
            }

            map.put(c,i);
            ans = Math.max(ans,i-left+1);

        }
        return ans;

    }
}

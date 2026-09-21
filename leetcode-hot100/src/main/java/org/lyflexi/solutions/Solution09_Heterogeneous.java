package org.lyflexi.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/1/19 10:55
 */


/*
* 438. 找到字符串中所有字母异位词
给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。


示例 1:

输入: s = "cbaebabacd", p = "abc"
输出: [0,6]
解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 示例 2:

输入: s = "abab", p = "ab"
输出: [0,1,2]
解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
* */
public class Solution09_Heterogeneous {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String p = scanner.nextLine();

        System.out.println(findAnagrams(s,p).toString());
    }

    public static List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> answer = new ArrayList<>();
        int[] sDict = new int[26];
        int[] pDict = new int[26];
        //初始化双方字典
        for (int i = 0; i < p.length() ; i++) {
            sDict[s.charAt(i)-'a']++;
            pDict[p.charAt(i)-'a']++;
        }
//        //初始化比较
//        if (Arrays.equals(sDict,pDict))
//            answer.add(0);
        //开始滑动窗口，左边界从0到sLen-pLen
        for (int i = 0; i < s.length()-p.length(); i++) {
            if (Arrays.equals(sDict,pDict)){
                answer.add(i);
            }
            //滑动左窗口
            sDict[s.charAt(i)-'a']--;
            //滑动右窗口
            sDict[s.charAt(i+p.length())-'a']++;

        }
        if (Arrays.equals(sDict, pDict))
            answer.add(s.length()-p.length());
        return answer;
    }
}

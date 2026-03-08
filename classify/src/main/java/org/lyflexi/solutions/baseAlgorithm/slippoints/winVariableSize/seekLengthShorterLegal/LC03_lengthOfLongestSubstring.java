package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekLengthShorterLegal;

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
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
*/


/**
 * 不定长滑动窗口求长度, 越短越合法
 */
public class LC03_lengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        //s 由英文字母、数字、符号和空格组成, 可见字符总供是95个阿斯克码
        int[] dict = new int[95];
        int l = 0;
        int sum = 0;//求最大长度
        for(int i = 0; i<s.length(); i++){
            //可见字符的最小值是空格'space'而不是'a', 十进制是32
            dict[s.charAt(i) - ' '] +=1;
            //重复元素一定是右边新加入的, 此时需要收缩左边界. 否则可以跳过while直接求max
            while(dict[s.charAt(i) - ' '] > 1){
                dict[s.charAt(l) - ' ']--;
                l++;
            }
            sum = Math.max(sum, i-l+1);
        }
        return sum;
    }
}

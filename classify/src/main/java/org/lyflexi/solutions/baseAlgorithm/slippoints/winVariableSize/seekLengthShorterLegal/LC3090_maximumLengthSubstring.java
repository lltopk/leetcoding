package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekLengthShorterLegal;

/**
 * 3090. 每个字符最多出现两次的最长子字符串
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个字符串 s ，请找出满足每个字符最多出现两次的最长子字符串，并返回该子字符串的 最大 长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入： s = "bcbbbcba"
 *
 * 输出： 4
 *
 * 解释：
 *
 * 以下子字符串长度为 4，并且每个字符最多出现两次："bcbbbcba"。
 *
 * 示例 2：
 *
 * 输入： s = "aaaa"
 *
 * 输出： 2
 *
 * 解释：
 *
 * 以下子字符串长度为 2，并且每个字符最多出现两次："aaaa"。
 *
 *
 *
 * 提示：
 *
 * 2 <= s.length <= 100
 * s 仅由小写英文字母组成。
 */

/**
 * 不定长滑动窗口, 求长度, 越短越合法
 */
public class LC3090_maximumLengthSubstring {
    public int maximumLengthSubstring(String s) {
        //不定长滑动窗口, 越短越合法
        int n = s.length();
        int ans = 0;//求最大
        int[] dict = new int[26];
        int l = 0;
        for(int i = 0; i < n ;i++){
            dict[s.charAt(i) - 'a']++;
            while(dict[s.charAt(i) - 'a'] > 2){
                dict[s.charAt(l) - 'a']--;
                l++;
            }
            ans = Math.max(ans, i - l + 1);
        }

        return ans;
    }
}

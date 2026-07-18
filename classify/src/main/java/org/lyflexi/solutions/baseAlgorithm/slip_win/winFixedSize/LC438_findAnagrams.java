package org.lyflexi.solutions.baseAlgorithm.slip_win.winFixedSize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *  示例 2:
 *
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 *
 * 提示:
 *
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,093,320/2M
 * 通过率
 * 54.6%
 */
public class LC438_findAnagrams {
    /**
     * 定长滑动窗口
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        int k = p.length(), l = 0;
        List<Integer> ret = new ArrayList<>();

        // 窗口内s 的每种字母出现的次数
        int[] cntS = new int[26];
        // 统计 p 的每种字母的出现次数
        int[] cntP = new int[26];
        for (char c : p.toCharArray()) {
            cntP[c - 'a']++;
        }
        for(int r =0; r<s.length(); r++){
            char c = s.charAt(r);
            cntS[c - 'a']++;
            if(r - l + 1 == k){
                //由于存的都是字母次数, 所以这里不用担心顺序问题, cnt下标就代表字母顺序
                if(Arrays.equals(cntP, cntS)){
                    ret.add(l);
                }
                cntS[s.charAt(l) - 'a']--;
                l++;
            }
        }
        return ret;
    }
}

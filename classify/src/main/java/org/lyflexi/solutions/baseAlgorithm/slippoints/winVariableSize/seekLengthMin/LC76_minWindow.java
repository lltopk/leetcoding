package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekLengthMin;

/**
 * 76. 最小覆盖子串
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。
 *
 * 测试用例保证答案唯一。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * 示例 3:
 *
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *
 *
 * 提示：
 *
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s 和 t 由英文字母组成
 *
 *
 * 进阶：你能设计一个在 O(m + n) 时间内解决此问题的算法吗？
 */

/**
 * 不定长滑动窗口求最小
 */
public class LC76_minWindow {
    public String minWindow(String s, String t) {
        char[] sm = s.toCharArray();
        char[] st = t.toCharArray();
        int m = sm.length;
        int n = st.length;

        String ans = s + " ";
        int l = 0;
        int[] dictS = new int[95];
        int[] dictT = new int[95];

        for(int i = 0; i<n; i++){
            dictT[st[i] - ' ']++;
        }

        for(int r = 0; r<m ;r++){
            dictS[sm[r] - ' ']++;

            //窗口收缩条件, dictS每个字符出现次数都大于dictT
            while(shrink(dictS, dictT)){
                //不优雅迭代
                String sub = s.substring(l, r+1);
                if(sub.length() < ans.length()){
                    ans = sub;
                }

                dictS[sm[l] - ' ']--;
                l++;
            }

        }

        return ans.equals(s+" ")? "":ans ;
    }

    private boolean shrink(int[] dictS, int[] dictT){
        for(int i = 0; i<95; i++){
            //窗口但凡有一个元素频率dictS[i]小于dictT[i], 则无法收缩窗口
            if(dictS[i] < dictT[i]){
                return false;
            }
        }
        return true;
    }
}

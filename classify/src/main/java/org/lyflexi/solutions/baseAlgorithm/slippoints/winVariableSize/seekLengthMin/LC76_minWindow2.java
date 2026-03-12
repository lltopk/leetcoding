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
 *
 * 时间复杂度从O(95*m + n) 优化为O(n + m)
 */
public class LC76_minWindow2 {
    public String minWindow(String s, String t) {
        char[] sm = s.toCharArray();
        char[] st = t.toCharArray();
        int m = sm.length;
        int n = st.length;

        String ans = s + " ";
        int l = 0;
        //方法一每次收缩窗口的时候都要循环dict来判断是否覆盖, 总的时间复杂度为O(95*m + n), 其中95是字典字符集的大小
        //判断覆盖能否优化呢?
        //其实如果我们有一个变量geCnt代表窗口内共有geCnt种字符的次数大于等于t中的, 那么当geCnt == kinds即为覆盖, 其中kinds为t的字符种类
        // int[] dictS = new int[95];
        // int[] dictT = new int[95];
        // 这样我们可以把两个字典合并为一个
        int geCnt = 0;
        int[] diff = new int[95];

        int kinds = 0;
        for(int i = 0 ;i<n; i++){
            if(diff[st[i] - ' '] == 0){
                kinds++;
            }
            diff[st[i] - ' ']--;
        }

        for(int r = 0; r<m; r++){
            diff[sm[r] - ' ']++;
            if(diff[sm[r] - ' '] == 0){//注意不能是>=0, 否则会重复计算geCnt
                geCnt++;
            }

            //O(1)判断
            while(geCnt == kinds){
                String sub = s.substring(l, r+1);
                if(sub.length()<ans.length()){
                    ans = sub;
                }


                if(diff[sm[l] - ' '] == 0){// 同理, 只有==0的时候, 才意味着sm[l]小于t中字符的出现频率
                    geCnt--;
                }
                diff[sm[l] - ' ']--;
                l++;
            }
        }

        return ans.equals(s+" ")? "":ans;

    }

}

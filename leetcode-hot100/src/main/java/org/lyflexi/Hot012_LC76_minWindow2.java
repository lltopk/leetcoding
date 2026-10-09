package org.lyflexi;

import java.util.*;

/**
 * 76. 最小覆盖子串
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。
 * 
 * 测试用例保证答案唯一。
 * 
 * 示例 1：
 * 
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * 
 * 示例 2：
 * 
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * 
 * 示例 3:
 * 
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 * 
 * 提示：
 * 
 * - m == s.length
 * 
 * - n == t.length
 * 
 * - 1 <= m, n <= 10^5
 * 
 * - s 和 t 由英文字母组成
 * 
 * 进阶：你能设计一个在 O(m + n) 时间内解决此问题的算法吗？
 */

/**
 * 优化
 * 
 * 上面的代码每次都要花费 $O(|\Sigma|)$ 的时间去判断是否涵盖，能不能优化到 $O(1)$ 呢？
 * 
 * 可以。用一个变量 $geCnt$ 维护目前子串（窗口）中有 $geCnt$ 种字母的出现次数大于等于 $t$ 中相应字母的出现次数。
 * 
 * 设 $t$ 有 $kinds$ 个不同的字母，那么「子串每种字母的出现次数都大于等于 $t$ 中相应字母的出现次数」等价于 $geCnt = kinds$。
 * 
 * 如何维护 $geCnt$ 呢？
 * 
 * 为了方便实现，把 $cntS$ 和 $cntT$ 合并成一个 $diff$，定义 $diff[x] = cntS[x] - cntT[x]$。如果 $diff[x] = 0$，就意味着窗口内字母 $x$ 的出现次数和 $t$ 的一样多。
 * 
 * - 如果字母 $x$ 进入窗口后，$diff[x] = 0$，这意味着 $x$ 在子串和 $t$ 中的出现次数从 $<$ 变成了 $>=$，那么把 $geCnt$ 增加一。
 * - 如果字母 $x$ 离开窗口前，$diff[x] = 0$，这意味着 $x$ 离开窗口后，$x$ 在子串和 $t$ 中的出现次数从 $>=$ 变成了 $<$，那么把 $geCnt$ 减少一。
 * 
 * ⚠注意：不能在 $diff[x] >= 0$ 的时候就把 $geCnt$ 增加一。这样写的话，对于同一个字母 $x$，$diff[x]$ 等于 $0,1,2,\ldots$ 的时候都会让 $geCnt$ 增加一，这就重复统计了。
 */
public class Hot012_LC76_minWindow2 {
    public String minWindow(String S, String t) {
        int[] diff = new int[128]; // 窗口每种字母个数 - t 每种字母个数
        int kinds = 0;
        for (char c : t.toCharArray()) {
            if (diff[c] == 0) {
                kinds++; // 统计 t 有多少个不同的字母
            }
            diff[c]--;
        }

        char[] s = S.toCharArray();
        int m = s.length;
        int ansLeft = -1;
        int ansRight = m;
        int geCnt = 0; // 窗口内有 geCnt 种字母的出现次数 >= t 中相应字母的出现次数
        int left = 0;

        for (int right = 0; right < m; right++) { // 移动子串右端点
            char c = s[right]; // 右端点字母
            diff[c]++; // 右端点字母移入子串
            if (diff[c] == 0) { // 原来窗口内 c 的出现次数比 t 的少，现在一样多
                geCnt++; // 从 < 变成 >=
            }

            while (geCnt == kinds) { // 涵盖：所有字母的出现次数都是 >=
                if (right - left < ansRight - ansLeft) { // 找到更短的子串
                    ansLeft = left; // 记录此时的左右端点
                    ansRight = right;
                }

                char x = s[left]; // 左端点字母
                if (diff[x] == 0) {
                    // x 移出窗口之前，检查出现次数，
                    // 如果窗口内 x 的出现次数和 t 一样，
                    // 那么 x 移出窗口后，窗口内 x 的出现次数比 t 的少
                    geCnt--; // 从 >= 变成 <
                }
                diff[x]--; // 左端点字母移出子串
                left++;
            }
        }

        return ansLeft < 0 ? "" : S.substring(ansLeft, ansRight + 1);
    }
}

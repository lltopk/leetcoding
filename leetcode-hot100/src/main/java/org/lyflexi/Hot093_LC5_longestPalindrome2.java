package org.lyflexi;

import java.util.*;

/**
 * 5. 最长回文子串
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个字符串 s，找到 s 中最长的 回文 子串。
 * 
 * 示例 1：
 * 
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 
 * 示例 2：
 * 
 * 输入：s = "cbbd"
 * 输出："bb"
 * 
 * 提示：
 * 
 * - 1 <= s.length <= 1000
 * 
 * - s 仅由数字和英文字母组成
 */

/**
 * 写法二：合二为一
 * 
 * 枚举 $i=0,1,2,\ldots, 2n-2$。
 * 
 * - 规定当 $i$ 是偶数时，使用枚举奇回文串的规则，即初始化 $l=r=\dfrac{i}{2}$。比如 $i=2$ 时 $l=r=1$。
 * - 规定当 $i$ 是奇数时，使用枚举偶回文串的规则，即初始化 $l=<=ft\lfloor\dfrac{i}{2}\right\rfloor$，$r=<=ft\lceil\dfrac{i}{2}\right\rceil$。比如 $i=1$ 时 $l=0$，$r=1$。
 * 
 * 两种情况可以合并为：
 * 
 * - 初始化 $l=<=ft\lfloor\dfrac{i}{2}\right\rfloor$，$r=<=ft\lceil\dfrac{i}{2}\right\rceil = <=ft\lfloor\dfrac{i+1}{2}\right\rfloor$。
 * 
 * 按照这个规则，可以恰好枚举到所有的奇回文串和偶回文串。
 */
public class Hot093_LC5_longestPalindrome2 {
    public String longestPalindrome(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        int ansLeft = 0;
        int ansRight = 0;

        for (int i = 0; i < 2 * n - 1; i++) {
            int l = i / 2;
            int r = (i + 1) / 2;
            while (l >= 0 && r < n && s[l] == s[r]) {
                l--;
                r++;
            }
            // 循环结束后，s[l+1] 到 s[r-1] 是回文串
            if (r - l - 1 > ansRight - ansLeft) {
                ansLeft = l + 1;
                ansRight = r; // 左闭右开区间
            }
        }

        return S.substring(ansLeft, ansRight);
    }
}

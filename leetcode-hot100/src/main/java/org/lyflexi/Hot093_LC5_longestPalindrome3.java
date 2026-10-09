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
 * 方法二：Manacher 算法
 * 
 * 具体请看 视频讲解，欢迎点赞关注~
 * 
 * 本题需要输出具体的最长回文子串，这需要我们在跑 Manacher 算法的过程中，维护最大的 $halfLen[i]$ 对应的下标 $maxI$。最后根据下标转换关系，算出 $t$ 中的最长回文子串在 $s$ 中的下标。具体见视频讲解和代码注释。
 */
public class Hot093_LC5_longestPalindrome3 {
    public String longestPalindrome(String s) {
        // Manacher 模板
        // 将 s 改造为 t，这样就不需要讨论 s.length() 的奇偶性，因为新串 t 的每个回文子串都是奇回文串（都有回文中心）
        // s 和 t 的下标转换关系：
        // (si+1)*2 = ti
        // ti/2-1 = si
        // ti 为偶数，对应奇回文串（从 2 开始）
        // ti 为奇数，对应偶回文串（从 3 开始）
        int n = s.length();
        char[] t = new char[n * 2 + 3];
        Arrays.fill(t, '#');
        t[0] = '^';
        for (int i = 0; i < n; i++) {
            t[i * 2 + 2] = s.charAt(i);
        }
        t[n * 2 + 2] = '$';

        // 定义一个奇回文串的回文半径=(长度+1)/2，即保留回文中心，去掉一侧后的剩余字符串的长度
        // halfLen[i] 表示在 t 上的以 t[i] 为回文中心的最长回文子串的回文半径
        // 即 [i-halfLen[i]+1, i+halfLen[i]-1] 是 t 上的一个回文子串
        int[] halfLen = new int[t.length - 2];
        halfLen[1] = 1;

        // maxI 记录最长回文子串在 halfLen 中的下标
        int maxI = 0;
        // boxR 表示当前右边界下标最大的回文子串的右边界下标+1
        // boxM 为该回文子串的中心位置
        // 二者的关系为 boxR = boxM + halfLen[boxM]
        int boxM = 0;
        int boxR = 0;
        for (int i = 2; i < halfLen.length; i++) {
            int hl = 1;
            if (i < boxR) {
                // 记 i 关于 boxM 的对称位置 i'=boxM*2-i
                // 若以 i' 为中心的最长回文子串范围超出了以 boxM 为中心的回文串的范围
                // 则 halfLen[i] 应先初始化为已知的回文半径 boxR-i，然后再继续暴力匹配
                // 否则 halfLen[i] 与 halfLen[i'] 相等
                hl = Math.min(boxR - i, halfLen[boxM * 2 - i]);
            }

            // 暴力扩展
            // 算法的复杂度取决于这部分执行的次数
            // 由于扩展之后 boxR 必然会更新（右移），且扩展的的次数就是 boxR 右移的次数
            // 因此算法的复杂度 = O(t.length) = O(n)
            while (t[i - hl] == t[i + hl]) {
                hl++;
                boxM = i;
                boxR = i + hl;
            }

            halfLen[i] = hl;
            if (hl > halfLen[maxI]) {
                maxI = i;
            }
        }

        int hl = halfLen[maxI];
        // 注意 t 上的最长回文子串的最左边和最右边都是 '#'
        // 所以要对应到 s，最长回文子串的下标是从 (maxI-hl)/2 到 (maxI+hl)/2-2
        return s.substring((maxI - hl) / 2, (maxI + hl) / 2 - 1);
    }
}

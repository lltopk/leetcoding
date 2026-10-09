package org.lyflexi;

import java.util.*;

/**
 * 394. 字符串解码
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 
 * 测试用例保证输出的长度不会超过 10^5。
 * 
 * 示例 1：
 * 
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 
 * 示例 2：
 * 
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 
 * 示例 3：
 * 
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 
 * 示例 4：
 * 
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 * 
 * 提示：
 * 
 * - 1 <= s.length <= 30
 * 
 * - s 由小写英文字母、数字和方括号 '[]' 组成
 * 
 * - s 保证是一个 有效 的输入。
 * 
 * - s 中所有整数的取值范围为 [1, 300]
 */

/**
 * 细节
 * 
 * 问：递归过程中，怎么知道当前遍历到哪个字符了？
 * 
 * 答：可以用一个在递归函数外的变量 $i$ 表示当前下标，每遍历到一个字符，就把 $i$ 加一。
 * 
 * 问：如果 $s=123[a]$，怎么把字符串 $123$ 变成数字 $k=123$？
 * 
 * 答：利用式子 $k = k\cdot 10 + int(s_i)$ 计算。
 * 
 * - 初始化 $k=0$。
 * - 更新 $k$ 为 $k\cdot 10 + 1 = 0 + 1 = 1$。
 * - 更新 $k$ 为 $k\cdot 10 + 2 = 10 + 2 = 12$。
 * - 更新 $k$ 为 $k\cdot 10 + 3 = 120 + 3 = 123$。
 */
public class Hot071_LC394_decodeString2 {
    public String decodeString(String s) {
        return decode(s.toCharArray());
    }

    private int i = 0;

    private String decode(char[] s) {
        StringBuilder res = new StringBuilder();
        int k = 0;
        while (i < s.length) {
            char c = s[i];
            i++;
            if (Character.isLetter(c)) {
                res.append(c);
            } else if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
            } else if (c == '[') { // 递
                String t = decode(s);
                for (int x = 0; x < k; x++) { res.append(t); } // 把括号内的字符串重复 k 次
                k = 0; // 重置 k，若不重置，2[a]3[b] 后面的 3 会算出 k = 23
            } else { // ']' 归
                break;
            }
        }
        return res.toString();
    }
}

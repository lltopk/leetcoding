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
 * 第一种递归写法
 * 
 * 分类讨论：
 * 
 * - 如果 $s$ 是空串，返回空串。
 * - 如果 $s_0$ 是字母，我们可以递归解码 $s_1$ 到 $s_{n-1}$。
 *    - 如果 $s$ 是基础类型，例如 $s=abc$，那么 $a + bc$ 仍然是 $abc$。
 *    - 如果 $s$ 是组合类型，例如 $s=ab2[cd]e$，那么答案等于 $a + b2[cd]e$，后者可以继续递归解码。
 * - 否则，$s_0$ 一定是数字。这意味着 $s$ 至少包含一对括号。
 *    - 找第一个左括号的下标 $i$。
 *    - 找与 $s_i$ 匹配的右括号的下标 $j$。⚠注意：对于 $s=2[3[ab]]$ 这种嵌套类型，我们需要跳过内层的括号。可以用一个变量 $balance$ 表示左括号减去右括号的个数，在遍历 $s$ 的过程中维护 $balance$，一旦 $balance=0$ 就表示我们找到了与第一个左括号匹配的右括号。
 *    - 把 $s$ 分成三部分：
 *       - $s_0$ 到 $s_{i-1}$ 转成数字 $k$。
 *       - $s_{i+1}$ 到 $s_{j-1}$ 继续递归解码，得到字符串 $a$。
 *       - $s_{j+1}$ 到 $s_{n-1}$ 继续递归解码，得到字符串 $b$。
 *    - 把 $a$ 重复 $k$ 次，再与 $b$ 拼接，得到答案 $a\cdot k + b$。
 */
public class Hot071_LC394_decodeString {
    public String decodeString(String s) {
        if (s.isEmpty()) {
            return s;
        }

        // s[0] 是字母
        if (Character.isLetter(s.charAt(0))) {
            // 分离出 s[0]，解码剩下的
            return s.charAt(0) + decodeString(s.substring(1));
        }

        // s[0] 是数字，后面至少有一对括号
        int i = s.indexOf('['); // 找左括号
        int balance = 1; // 左括号个数减去右括号个数
        for (int j = i + 1; ; j++) {
            char c = s.charAt(j);
            if (c == '[') {
                balance++;
            } else if (c == ']') {
                balance--;
                if (balance == 0) { // 找到与 s[i] 匹配的右括号 s[j]
                    int k = Integer.parseInt(s.substring(0, i));
                    String t = decodeString(s.substring(i + 1, j));
                    StringBuilder sb = new StringBuilder();
                    for (int x = 0; x < k; x++) {
                        sb.append(t);
                    }
                    sb.append(decodeString(s.substring(j + 1)));
                    return sb.toString();
                }
            }
        }
    }
}

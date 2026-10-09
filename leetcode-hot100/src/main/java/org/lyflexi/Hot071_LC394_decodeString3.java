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
 * 用栈模拟递归
 * 
 * 把第二种递归改写成非递归写法。
 * 
 * 首先你要理解计算机底层是如何实现递归的，请看视频 深刻理解递归【基础算法精讲 09】。
 * 
 * 简单来说，在往下「递」的时候，计算机会把当前函数中的局部变量保存到栈中（这个栈由计算机管理）；下层递归函数「归」之后，再从栈中把保存的变量取出。我们可以手动模拟这个过程，具体实现如下。
 */
public class Hot071_LC394_decodeString3 {
    private static class Pair { String s; int k; Pair(String s, int k) { this.s = s; this.k = k; } }

    public String decodeString(String s) {
        Deque<Pair> stack = new ArrayDeque<>(); // 用于模拟计算机的递归
        StringBuilder res = new StringBuilder();
        int k = 0;

        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                res.append(c);
            } else if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                // 模拟递归
                stack.push(new Pair(res.toString(), k)); // 递归前，把局部变量 res 和 k 保存到栈中
                res.setLength(0); // 递归，初始化 res 和 k
                k = 0;
            } else { // ']'
                Pair p = stack.pop(); // 递归结束，从栈中恢复递归之前保存的局部变量
                // 此时 res 是下层递归的返回值，将其重复 p.k 次，拼接到递归前的 p.s 之后
                StringBuilder tmp = new StringBuilder(p.s);
                for (int x = 0; x < p.k; x++) { tmp.append(res); }
                res = tmp;
            }
        }

        return res.toString();
    }
}

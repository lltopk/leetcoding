package org.lyflexi;

import java.util.*;

/**
 * 32. 最长有效括号
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号 子串 的长度。
 * 
 * 左右括号匹配，即每个左括号都有对应的右括号将其闭合的字符串是格式正确的，比如 "(()())"。
 * 
 * 示例 1：
 * 
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 
 * 示例 2：
 * 
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 
 * 示例 3：
 * 
 * 输入：s = ""
 * 输出：0
 * 
 * 提示：
 * 
 * - 0 <= s.length <= 3 * 10^4
 * 
 * - s[i] 为 '(' 或 ')'
 */

/**
 * 栈 + 配对标记
 * 
 * 从左到右遍历字符串 $s$，对于右括号，找左侧最近的未配对的左括号，然后把这对括号都标记为「已配对」。
 * 
 * 为了方便找到左侧最近的未配对的左括号，我们可以用一个栈保存遍历过的左括号的下标：
 * 
 * - 遇到左括号，把其下标入栈。
 * - 遇到右括号，且栈非空，那么右括号与栈顶左括号匹配，弹出栈顶。这样遍历到下一个右括号时，栈顶总是最近的未配对的左括号的下标。
 * 
 * 示例 1 的标记情况为 $(\color{red}()$。红色为我们标记的括号。
 * 
 * 示例 2 的标记情况为 $)\color{red()()})$。
 * 
 * $s = ()))((())(($ 的标记情况为 $\color{red()}))(\color{red(())}(($。
 * 
 * 最后，最长连续标记的长度，就是最长有效括号的长度。做法同 485. 最大连续 1 的个数，我的题解。
 */
public class Hot090_LC32_longestValidParentheses2 {
    public int longestValidParentheses(String s) {
        int n = s.length();
        boolean[] isValid = new boolean[n];
        int[] st = new int[n]; // 未配对的左括号的下标
        int top = -1; // 栈顶下标

        // 标记哪些括号是配对的
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                st[++top] = i; // 保存左括号的下标
            } else if (top >= 0) { // 右括号与栈顶的左括号配对
                isValid[i] = isValid[st[top--]] = true;
            }
        }

        // 最长有效括号即为 isValid 中的最长连续 true
        int ans = 0;
        int cnt = 0;
        for (boolean b : isValid) {
            if (b) {
                cnt++; // 连续 true 的个数
                ans = Math.max(ans, cnt);
            } else {
                cnt = 0; // 重置计数器
            }
        }
        return ans;
    }
}

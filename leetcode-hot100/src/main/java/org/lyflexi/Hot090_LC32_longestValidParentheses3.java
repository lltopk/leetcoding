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
 * 优化：一次遍历
 * 
 * 既然栈中保存的是未配对的下标，那么从栈顶加一的位置到 $i$，就是已配对的连续括号，长度为 $i$ 减去栈顶，更新答案的最大值。
 * 
 * 特殊情况：
 * 
 * 1. 例如 $s = )()$，其中 $s[0]$ 是永远无法配对的右括号。把这种括号的下标入栈，从而保证「栈顶加一」是有效括号的左端点。
 * 2. 如果栈为空呢？此时有效括号的左端点是 $0$。我们可以在一开始，往栈中添加一个 $-1$，这样可以兼容「栈顶加一」是有效括号的左端点，从而简化代码逻辑。
 */
public class Hot090_LC32_longestValidParentheses3 {
    public int longestValidParentheses(String s) {
        // ArrayDeque 比较慢，更快的写法见【Java 数组】
        Deque<Integer> st = new ArrayDeque<>(); // 未配对括号的下标
        st.push(-1); // 栈底元素表示永远无法配对的括号下标
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(i); // 保存左括号的下标
            } else if (st.size() > 1) {
                st.pop(); // 右括号与栈顶的左括号配对
                ans = Math.max(ans, i - st.peek()); // 从 st.peek()+1 到 i 都已配对，长为 i - st.peek()
            } else { // s[i] 是永远无法配对的右括号
                st.pop();
                st.push(i); // 替换栈底
            }
        }

        return ans;
    }
}

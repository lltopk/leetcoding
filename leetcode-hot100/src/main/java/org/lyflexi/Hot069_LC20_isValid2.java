package org.lyflexi;

import java.util.*;

/**
 * 20. 有效的括号
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 
 * 有效字符串需满足：
 * 
 * - 左括号必须用相同类型的右括号闭合。
 * 
 * - 左括号必须以正确的顺序闭合。
 * 
 * - 每个右括号都有一个对应的相同类型的左括号。
 * 
 * 示例 1：
 * 
 * 输入：s = "()"
 * 
 * 输出：true
 * 
 * 示例 2：
 * 
 * 输入：s = "()[]{}"
 * 
 * 输出：true
 * 
 * 示例 3：
 * 
 * 输入：s = "(]"
 * 
 * 输出：false
 * 
 * 示例 4：
 * 
 * 输入：s = "([])"
 * 
 * 输出：true
 * 
 * 示例 5：
 * 
 * 输入：s = "([)]"
 * 
 * 输出：false
 * 
 * 提示：
 * 
 * - 1 <= s.length <= 10^4
 * 
 * - s 仅由括号 '()[]{}' 组成
 */

/**
 * 写法二
 * 
 * 也可以在哈希表/数组中保存每个左括号对应的右括号。在遍历到左括号时，把对应的右括号入栈。这样遍历到右括号时，只需看栈顶括号是否一样即可。
 */
public class Hot069_LC20_isValid2 {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) { // s 长度必须是偶数
            return false;
        }
        Map<Character, Character> mp = new HashMap<Character, Character>() {{
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};
        Deque<Character> st = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (mp.containsKey(c)) { // c 是左括号
                st.push(mp.get(c)); // 入栈
            } else if (st.isEmpty() || st.pop() != c) { // c 是右括号
                return false; // 没有左括号，或者左括号类型不对
            }
        }
        return st.isEmpty(); // 所有左括号必须匹配完毕
    }
}

package org.lyflexi.solutions.baseAlgorithm.utils.stack.list_simulation;

/**
 * 20. 有效的括号
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *
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
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 简单栈
 */
public class LC20_isValid {
    public boolean isValid(String s) {
        List<Character> stack = new ArrayList<>();
        char[] sa = s.toCharArray();
        for(char c: sa){
            if(c == ')' && !stack.isEmpty() && stack.get(stack.size()-1) == '('){
                stack.remove(stack.size()-1);
                continue;
            }
            if(c == '}' && !stack.isEmpty() && stack.get(stack.size()-1) == '{'){
                stack.remove(stack.size()-1);
                continue;
            }
            if(c == ']' && !stack.isEmpty() && stack.get(stack.size()-1) == '['){
                stack.remove(stack.size()-1);
                continue;
            }
            stack.add(c);
        }

        return stack.size() == 0;
    }
}

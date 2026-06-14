package org.lyflexi.solutions.baseAlgorithm.utils.stack;

/**
 * 678. 有效的括号字符串
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个只包含三种字符的字符串，支持的字符类型分别是 '('、')' 和 '*'。请你检验这个字符串是否为有效字符串，如果是 有效 字符串返回 true 。
 *
 * 有效 字符串符合如下规则：
 *
 * 任何左括号 '(' 必须有相应的右括号 ')'。
 * 任何右括号 ')' 必须有相应的左括号 '(' 。
 * 左括号 '(' 必须在对应的右括号之前 ')'。
 * '*' 可以被视为单个右括号 ')' ，或单个左括号 '(' ，或一个空字符串 ""。
 *
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "(*)"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "(*))"
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 100
 * s[i] 为 '('、')' 或 '*'
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 91,004/224.6K
 * 通过率
 * 40.5%
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 栈, 正反两次贪心遍历
 */
public class LC678_checkValidString {
    //由于星号是万能牌, 可以将星号都当作'('进行正向遍历入栈, 保证栈不为空
    //然后将星号都当作')'进行反向遍历入栈, 保证栈不为空
    public boolean checkValidString(String s) {
        //在两次方向遍历的过程中, 如果栈为空, 说明彻底拦不住, 则返回false
        Deque<Character> stack = new ArrayDeque<>();

        for(char c: s.toCharArray()){
            if(stack.isEmpty() && c == ')'){
                return false;
            }
            if(! stack.isEmpty() && c == ')'){
                stack.pop();
                continue;
            }
            //'(' || '*'入栈
            stack.push(c);
        }

        int n  = s.length();
        stack.clear();
        for(int i = n-1; i>=0; i--){
            char c = s.charAt(i);
            if(stack.isEmpty() && c == '('){
                return false;
            }
            if(! stack.isEmpty() && c == '('){
                stack.pop();
                continue;
            }
            //'*' || ')'入栈
            stack.push(c);
        }

        return true;
    }
}

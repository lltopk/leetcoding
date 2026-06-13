package org.lyflexi.solutions.baseAlgorithm.utils.stack.list_simulation;

/**
 * 1021. 删除最外层的括号
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 有效括号字符串为空 ""、"(" + A + ")" 或 A + B ，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。
 *
 * 例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
 * 如果有效字符串 s 非空，且不存在将其拆分为 s = A + B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。
 *
 * 给出一个非空有效字符串 s，考虑将其进行原语化分解，使得：s = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
 *
 * 对 s 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 s 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "(()())(())"
 * 输出："()()()"
 * 解释：
 * 输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
 * 删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。
 * 示例 2：
 *
 * 输入：s = "(()())(())(()(()))"
 * 输出："()()()()(())"
 * 解释：
 * 输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
 * 删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
 * 示例 3：
 *
 * 输入：s = "()()"
 * 输出：""
 * 解释：
 * 输入字符串为 "()()"，原语化分解得到 "()" + "()"，
 * 删除每个部分中的最外层括号后得到 "" + "" = ""。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s[i] 为 '(' 或 ')'
 * s 是一个有效括号字符串
 */

/**
 * pair栈
 */
public class LC1021_removeOuterParentheses2 {
    //当栈为空的时候说明要么是第一个'('不加入答案, 要么是最后一个')'也不加入答案
    public String removeOuterParentheses(String s) {
        //栈中只存在'(',
        StringBuilder stack = new StringBuilder();
        StringBuilder ret = new StringBuilder();
        for(char c: s.toCharArray()){
            if(! stack.isEmpty() && c == ')' && stack.charAt(stack.length()-1) == '('){
                stack.setLength(stack.length() - 1);
                if(stack.length() != 0){
                    ret.append(c);//答案加')'
                }
                continue;
            }

            //入栈的一定是'('
            if(stack.length() != 0){
                ret.append(c);//答案加'('
            }
            stack.append(c);

        }

        return ret.toString();
    }
}

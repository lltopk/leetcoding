package org.lyflexi.solutions.baseAlgorithm.utils.stack.list_simulation;

/**
 * 921. 使括号有效的最少添加
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 只有满足下面几点之一，括号字符串才是有效的：
 *
 * 它是一个空字符串，或者
 * 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
 * 它可以被写作 (A)，其中 A 是有效字符串。
 * 给定一个括号字符串 s ，在每一次操作中，你都可以在字符串的任何位置插入一个括号
 *
 * 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))" 。
 * 返回 为使结果字符串 s 有效而必须添加的最少括号数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "())"
 * 输出：1
 * 示例 2：
 *
 * 输入：s = "((("
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 只包含 '(' 和 ')' 字符。
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 84,638/115.4K
 * 通过率
 * 73.3%
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 列表模拟栈
 */
public class LC921_minAddToMakeValid {
    public int minAddToMakeValid(String s) {
        //列表模拟栈
        List<Character> stack = new ArrayList<>();
        for(char c: s.toCharArray()){
            if(! stack.isEmpty() && c==')' && stack.get(stack.size() - 1) == '('){
                stack.remove(stack.size() - 1);
                continue;
            }
            stack.add(c);
        }

        return stack.size();
    }
}

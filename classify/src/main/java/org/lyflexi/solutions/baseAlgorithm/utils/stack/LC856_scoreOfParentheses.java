package org.lyflexi.solutions.baseAlgorithm.utils.stack;

/**
 * 856. 括号的分数
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 *
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 *
 *
 * 示例 1：
 *
 * 输入： "()"
 * 输出： 1
 * 示例 2：
 *
 * 输入： "(())"
 * 输出： 2
 * 示例 3：
 *
 * 输入： "()()"
 * 输出： 2
 * 示例 4：
 *
 * 输入： "(()(()))"
 * 输出： 6
 *
 *
 * 提示：
 *
 * S 是平衡括号字符串，且只含有 ( 和 ) 。
 * 2 <= S.length <= 50
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 63,363/92K
 * 通过率
 * 68.9%
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 栈 布尔标记
 */
public class LC856_scoreOfParentheses {
    //'('进栈, 遇到')'则累加答案: 2^(左括号个数减1)
    public int scoreOfParentheses(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int ret = 0;
        boolean flag = false;
        for(char c: s.toCharArray()){
            if(c == ')'){
                stack.pop();
                //如"(())", 通过标记避免连续')'的时候重复计算
                //错误答案: 2+1
                //正确答案: 2
                if(!flag){
                    ret += 1 << stack.size(); //2^size
                    flag = true;
                }
                continue;
            }
            stack.push(c);
            flag = false;
        }
        return ret;
    }
}

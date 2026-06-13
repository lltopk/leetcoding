package org.lyflexi.solutions.baseAlgorithm.utils.stack.index;

/**
 * 1249. 移除无效的括号
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个由 '('、')' 和小写字母组成的字符串 s。
 *
 * 你需要从字符串中删除最少数目的 '(' 或者 ')' （可以删除任意位置的括号)，使得剩下的「括号字符串」有效。
 *
 * 请返回任意一个合法字符串。
 *
 * 有效「括号字符串」应当符合以下 任意一条 要求：
 *
 * 空字符串或只包含小写字母的字符串
 * 可以被写作 AB（A 连接 B）的字符串，其中 A 和 B 都是有效「括号字符串」
 * 可以被写作 (A) 的字符串，其中 A 是一个有效的「括号字符串」
 *
 *
 * 示例 1：
 *
 * 输入：s = "lee(t(c)o)de)"
 * 输出："lee(t(c)o)de"
 * 解释："lee(t(co)de)" , "lee(t(c)ode)" 也是一个可行答案。
 * 示例 2：
 *
 * 输入：s = "a)b(c)d"
 * 输出："ab(c)d"
 * 示例 3：
 *
 * 输入：s = "))(("
 * 输出：""
 * 解释：空字符串也是有效的
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s[i] 可能是 '('、')' 或英文小写字母
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 59,421/96.6K
 * 通过率
 * 61.5%
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 栈存储索引
 */
public class LC1249_minRemoveToMakeValid {
    public String minRemoveToMakeValid(String s) {
        StringBuilder ret = new StringBuilder();
        //栈仅仅存储'('的索引
        Deque<Integer> stack = new ArrayDeque<>();
        //标记有效位
        int[] mark = new int[s.length()];
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            if(stack.isEmpty() && c == ')'){
                continue;
            }

            //有效的'(...)'之间的元素如果要入栈, 则无法再给答案插入'(', 因此采用标记法, 最终统一构造答案
            if(!stack.isEmpty() && s.charAt(stack.peek()) == '(' && c ==')'){
                mark[stack.pop()] = 1;
                mark[i] = 1;
                continue;
            }
            if(c == '('){
                stack.push(i);
            }

            if(Character.isLetter(c)){
                mark[i] = 1;
            }
        }

        for(int i = 0; i< s.length(); i++){
            if(mark[i] == 1){
                ret.append(s.charAt(i));
            }
        }
        return ret.toString();
    }
}

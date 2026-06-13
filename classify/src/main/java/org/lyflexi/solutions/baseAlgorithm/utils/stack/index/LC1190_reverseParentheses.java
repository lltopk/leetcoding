package org.lyflexi.solutions.baseAlgorithm.utils.stack.index;

/**
 * 1190. 反转每对括号间的子串
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 *
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 *
 * 注意，您的结果中 不应 包含任何括号。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "(abcd)"
 * 输出："dcba"
 * 示例 2：
 *
 * 输入：s = "(u(love)i)"
 * 输出："iloveu"
 * 解释：先反转子字符串 "love" ，然后反转整个字符串。
 * 示例 3：
 *
 * 输入：s = "(ed(et(oc))el)"
 * 输出："leetcode"
 * 解释：先反转子字符串 "oc" ，接着反转 "etco" ，然后反转整个字符串。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 2000
 * s 中只有小写英文字母和括号
 * 题目测试用例确保所有括号都是成对出现的
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 76,512/116K
 * 通过率
 * 65.9%
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 栈中不存实际字符, 只存储'('的索引
 */
public class LC1190_reverseParentheses {
    public String reverseParentheses(String s) {
        //栈仅仅用来存储'('的下标i, 当遇到')'的时候, 将(i, j)中的数据reverse
        Deque<Integer> stack = new ArrayDeque<>();
        char[] sa = s.toCharArray();
        for(int i = 0 ;i <sa.length; i++){
            char c = sa[i];
            if(c =='('){
                stack.push(i);
                continue;
            }

            if(c == ')'){
                reverse(sa, stack.pop()+1, i);
                continue;
            }
        }

        StringBuilder ret = new StringBuilder();
        for(char c: sa){
            if(c!='(' && c!=')'){
                ret.append(c);
            }
        }

        return ret.toString();
    }

    /**
     左闭右开区间
     */
    private void reverse(char[] sa, int start, int end){
        end-=1;
        //偶数情况: 跳出循环恰好start > end
        //奇数情况: 跳出循环恰好start == end
        while(start < end){
            char t = sa[start];
            sa[start] = sa[end];
            sa[end] = t;
            start++;
            end--;
        }
    }
}

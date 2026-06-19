package org.lyflexi.solutions.baseAlgorithm.utils.stack;

/**
 * 227. 基本计算器 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 * 整数除法仅保留整数部分。
 *
 * 你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。
 *
 * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "3+2*2"
 * 输出：7
 * 示例 2：
 *
 * 输入：s = " 3/2 "
 * 输出：1
 * 示例 3：
 *
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 3 * 105
 * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 * s 表示一个 有效表达式
 * 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
 * 题目数据保证答案是一个 32-bit 整数
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 211,409/455.6K
 * 通过率
 * 46.4%
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 栈
 */
public class LC227_calculate {

    /**
     '乘法 除法'需要和栈顶元素相乘, 计算后需要重新入栈
     '加法 减法'直接入栈

     最终答案为 = 累加栈中全部元素
     */
    public int calculate(String s) {
        //当前值
        int cur = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        char preC = '+';//用加法初始化栈
        char[] sa = s.toCharArray();
        for(int i = 0; i< sa.length; i++){
            char c = sa[i];
            if(Character.isDigit(c)){
                cur = cur * 10 + c - '0';
            }
            //|| i == sa.length - 1保证最后的数值可以被计算
            if((!Character.isDigit(c) && c != ' ') || i == sa.length - 1){
                if(preC == '+'){
                    stack.push(cur);
                }else if(preC == '-'){
                    stack.push(-cur);
                }else if(preC == '*'){
                    stack.push(stack.pop() * cur);
                }else if(preC == '/'){
                    stack.push(stack.pop() / cur);
                }
                preC = c;
                cur = 0;//归零
            }
        }

        int ret = 0;
        while(! stack.isEmpty()){
            ret += stack.pop();
        }
        return ret;
    }
}

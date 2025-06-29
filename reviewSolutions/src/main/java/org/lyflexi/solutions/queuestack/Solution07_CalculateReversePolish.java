package org.lyflexi.solutions.queuestack;

import java.util.LinkedList;

/**
 * @Author: ly
 * @Date: 2024/3/19 13:22
 */

/*
* 150. 逆波兰表达式求值
*
* 逆波兰表达式由波兰的逻辑学家卢卡西维兹提出。逆波兰表达式的特点是：没有括号，运算符总是放在和它相关的操作数之后。因此，逆波兰表达式也称后缀表达式。
比如后缀表达式相加，34+4* -> （3 + 4） * 4 = 28
* 现在，给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。请你计算该表达式。返回一个表示表达式值的整数。

注意：
有效的算符为 '+'、'-'、'*' 和 '/' 。
每个操作数（运算对象）都可以是一个整数或者另一个表达式。
两个整数之间的除法总是 向零截断 。
表达式中不含除零运算。
输入是一个根据逆波兰表示法表示的算术表达式。
答案及所有中间计算结果可以用 32 位 整数表示。


示例 1：
输入：tokens = ["2","1","+","3","*"]
输出：9
解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
*
示例 2：
输入：tokens = ["4","13","5","/","+"]
输出：6
解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
*
示例 3：
输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
输出：22
解释：该算式转化为常见的中缀算术表达式为：
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22

* */
public class Solution07_CalculateReversePolish {
    public int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (String item:tokens){
            //如果item是数字进栈
            if (isNumber(item)){
                stack.push(Integer.valueOf(item));
            }else {//如果item运算符号

                Integer right = stack.pop();//出栈函数是pop,第一次出栈的作为右元素
                Integer left = stack.pop();//出栈函数是pop,第一次出栈的作为左元素

                if ("+".equals(item)){
                    stack.push(left+right);
                }
                if ("-".equals(item)){
                    stack.push(left-right);
                }
                if ("*".equals(item)){
                    stack.push(left*right);
                }
                if ("/".equals(item)){
                    stack.push(left/right);
                }
            }
        }

        return stack.peek();

    }

    private boolean isNumber(String item){

        if ("+".equals(item)||"-".equals(item)||"*".equals(item)||"/".equals(item)){
            return false;
        }

        return true;
    }
}

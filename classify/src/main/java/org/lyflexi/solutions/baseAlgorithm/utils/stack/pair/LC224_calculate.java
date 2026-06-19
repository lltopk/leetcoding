package org.lyflexi.solutions.baseAlgorithm.utils.stack.pair;

/**
 * 224. 基本计算器
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 *
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 *
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
 * '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
 * 输入中不存在两个连续的操作符
 * 每个数字和运行的计算将适合于一个有符号的 32位 整数
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 210,231/479.7K
 * 通过率
 * 43.8%
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * pair栈: 每一层内的前缀值 以及 每一层内合并值num = num * 10 +  sa[i++] - '0';
 *
 * 然后枚举右维护左
 */
public class LC224_calculate {
    private record Pair(int prefix, int positive){
    }

    /**
     本题关键是把类似-(123)计算为-123
     */
    public int calculate(String s) {
        //当前值(两个变量)
        int ret = 0, positive = 1;
        //枚举右, 用栈维护左
        Deque<Pair> stack = new ArrayDeque<>();
        char[] sa = s.toCharArray();
        for(int i = 0; i< sa.length;i++ ){
            char c = sa[i];
            if(! stack.isEmpty() && c == ')'){
                Pair pair = stack.pop();
                ret = pair.prefix + pair.positive* ret;
                continue;
            }
            if(c == ' '){
                continue;
            }
            if(c == '-'){
                positive = -1;
            }else if(c == '+'){
                positive  = 1;//这里要显示赋值, 避免之前的-影响
            }else if(c == '('){
                stack.push(new Pair(ret, positive));

                //两个变量归0, 准备计算下一层
                ret = 0;
                positive = 1;
            }else{
                int num = 0;
                //将这个数找完
                while(i < sa.length && sa[i] >= '0' && sa[i] <= '9'){
                    num = num * 10 +  sa[i++] - '0';
                }
                i--;
                ret += positive * num;
            }
        }
        return ret;
    }
}

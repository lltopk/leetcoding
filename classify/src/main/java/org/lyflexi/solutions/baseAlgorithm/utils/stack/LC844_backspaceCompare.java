package org.lyflexi.solutions.baseAlgorithm.utils.stack;

/**
 * 844. 比较含退格的字符串
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符。
 *
 * 注意：如果对空文本输入退格字符，文本继续为空。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "ab#c", t = "ad#c"
 * 输出：true
 * 解释：s 和 t 都会变成 "ac"。
 * 示例 2：
 *
 * 输入：s = "ab##", t = "c#d#"
 * 输出：true
 * 解释：s 和 t 都会变成 ""。
 * 示例 3：
 *
 * 输入：s = "a#c", t = "b"
 * 输出：false
 * 解释：s 会变成 "c"，但 t 仍然是 "b"。
 *
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 200
 * s 和 t 只含有小写字母以及字符 '#'
 *
 *
 * 进阶：
 *
 * 你可以用 O(n) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 315,786/657K
 * 通过率
 * 48.1%
 */

import java.util.Stack;

/**
 * 栈
 */
public class LC844_backspaceCompare {
    public boolean backspaceCompare(String s, String t) {
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        stack(stack1, sa);
        stack(stack2, ta);
        if(stack1.isEmpty() && stack2.isEmpty()){
            return true;
        }
        if(stack1.size() != stack2.size()){
            return false;
        }

        //退栈操作不能使用for循环, 会导致循环次数不够, 最终执行次数只有栈大小的一半
        while(!stack1.isEmpty()){
            if((stack1.pop() - 'a') != (stack2.pop() - 'a')){
                return false;
            }
        }
        return true;

        // return stack1.equals(stack2);

    }

    private void stack(Stack<Character> stack, char[] array){
        for(char c: array){
            if('#' == c){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else{
                stack.push(c);
            }
        }
    }
}

package org.lyflexi.solutions.baseAlgorithm.utils.stack;

/**
 * 2116. 判断一个括号字符串是否有效
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 一个括号字符串是只由 '(' 和 ')' 组成的 非空 字符串。如果一个字符串满足下面 任意 一个条件，那么它就是有效的：
 *
 * 字符串为 ().
 * 它可以表示为 AB（A 与 B 连接），其中A 和 B 都是有效括号字符串。
 * 它可以表示为 (A) ，其中 A 是一个有效括号字符串。
 * 给你一个括号字符串 s 和一个字符串 locked ，两者长度都为 n 。locked 是一个二进制字符串，只包含 '0' 和 '1' 。对于 locked 中 每一个 下标 i ：
 *
 * 如果 locked[i] 是 '1' ，你 不能 改变 s[i] 。
 * 如果 locked[i] 是 '0' ，你 可以 将 s[i] 变为 '(' 或者 ')' 。
 * 如果你可以将 s 变为有效括号字符串，请你返回 true ，否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：s = "))()))", locked = "010100"
 * 输出：true
 * 解释：locked[1] == '1' 和 locked[3] == '1' ，所以我们无法改变 s[1] 或者 s[3] 。
 * 我们可以将 s[0] 和 s[4] 变为 '(' ，不改变 s[2] 和 s[5] ，使 s 变为有效字符串。
 * 示例 2：
 *
 * 输入：s = "()()", locked = "0000"
 * 输出：true
 * 解释：我们不需要做任何改变，因为 s 已经是有效字符串了。
 * 示例 3：
 *
 * 输入：s = ")", locked = "0"
 * 输出：false
 * 解释：locked 允许改变 s[0] 。
 * 但无论将 s[0] 变为 '(' 或者 ')' 都无法使 s 变为有效字符串。
 * 示例 4：
 *
 * 输入：s = "(((())(((())", locked = "111111010111"
 * 输出：true
 * 解释：locked 允许我们改变 s[6] 和 s[8]。
 * 我们将 s[6] 和 s[8] 改为 ')' 使 s 变为有效字符串。
 *
 *
 * 提示：
 *
 * n == s.length == locked.length
 * 1 <= n <= 105
 * s[i] 要么是 '(' 要么是 ')' 。
 * locked[i] 要么是 '0' 要么是 '1' 。
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 19,288/42.6K
 * 通过率
 * 45.3%
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 栈, 正反两次贪心遍历
 */
public class LC2116_canBeValid {
    //由于星号是万能牌, 可以将星号都当作'('进行正向遍历入栈, 保证栈不为空
    //然后将星号都当作')'进行反向遍历入栈, 保证栈不为空
    public boolean canBeValid(String s, String locked) {
        int n  = s.length();
        //但相比于LC678来说, 本题星号不能多余, 因此要特判下
        if(n % 2 == 1) return false;
        //在两次方向遍历的过程中, 如果栈为空, 说明彻底拦不住, 则返回false
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i<n; i++){
            char c = s.charAt(i);
            char lock = locked.charAt(i);
            if(c == ')'){
                if(lock == '1'){
                    if(stack.isEmpty()){
                        return false;
                    }else{
                        stack.pop();
                    }
                }else{
                    stack.push(c);
                }
                continue;
            }
            stack.push(c);
        }


        stack.clear();
        for(int i = n-1; i>=0; i--){
            char c = s.charAt(i);
            char lock = locked.charAt(i);
            if(c == '('){
                if(lock == '1'){
                    if(stack.isEmpty()){
                        return false;
                    }else{
                        stack.pop();
                    }
                }else{
                    stack.push(c);
                }
                continue;
            }
            stack.push(c);
        }

        return true;
    }
}

package org.lyflexi.solutions.baseAlgorithm.utils.stack.list_simulation;

/**
 * 1003. 检查替换后的词是否有效
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个字符串 s ，请你判断它是否 有效 。
 * 字符串 s 有效 需要满足：假设开始有一个空字符串 t = "" ，你可以执行 任意次 下述操作将 t 转换为 s ：
 *
 * 将字符串 "abc" 插入到 t 中的任意位置。形式上，t 变为 tleft + "abc" + tright，其中 t == tleft + tright 。注意，tleft 和 tright 可能为 空 。
 * 如果字符串 s 有效，则返回 true；否则，返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aabcbc"
 * 输出：true
 * 解释：
 * "" -> "abc" -> "aabcbc"
 * 因此，"aabcbc" 有效。
 * 示例 2：
 *
 * 输入：s = "abcabcababcc"
 * 输出：true
 * 解释：
 * "" -> "abc" -> "abcabc" -> "abcabcabc" -> "abcabcababcc"
 * 因此，"abcabcababcc" 有效。
 * 示例 3：
 *
 * 输入：s = "abccba"
 * 输出：false
 * 解释：执行操作无法得到 "abccba" 。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 2 * 104
 * s 由字母 'a'、'b' 和 'c' 组成
 */

/**
 * 字符串模拟栈
 */
public class LC1003_isValid {
    public boolean isValid(String s) {
        //逆向思维, 我们不求如何构造出S
        //而是扫描原始S, 遇到完整的"abc"就出栈, 最后剩下空串或者连续"abcabc"即为有效
        StringBuilder stack = new StringBuilder();
        char[] sa = s.toCharArray();
        for(char c: sa){
            if(stack.length()>1
                    && c=='c' && stack.charAt(stack.length() - 1) == 'b' && stack.charAt(stack.length() - 2) == 'a'){
                // stack.deleteCharAt(stack.length() - 1);
                // stack.deleteCharAt(stack.length() - 1);
                stack.setLength(stack.length() - 2);
                continue;
            }
            stack.append(c);
        }

        if(stack.isEmpty()){
            return true;
        }

        char[] stackArr = stack.toString().toCharArray();
        StringBuilder stackNew = new StringBuilder();
        for(char c: stackArr){
            if(stackNew.length()>1
                    && c=='c' && stackNew.charAt(stackNew.length() - 1) == 'b' && stackNew.charAt(stackNew.length() - 2) == 'a'){
                // stackNew.deleteCharAt(stackNew.length() - 1);
                // stackNew.deleteCharAt(stackNew.length() - 1);
                stackNew.setLength(stackNew.length() - 2);
                continue;
            }
            stackNew.append(c);
        }

        return stackNew.isEmpty();
    }
}

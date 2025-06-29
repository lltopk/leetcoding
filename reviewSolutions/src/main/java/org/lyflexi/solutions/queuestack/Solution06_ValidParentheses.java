package org.lyflexi.solutions.queuestack;

import java.util.*;

/**
 * @Author: ly
 * @Date: 2024/2/16 12:10
 */

/*
* 20. 有效的括号
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
有效字符串需满足：
左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
每个右括号都有一个对应的相同类型的左括号。

示例 1：
输入：s = "()"
输出：true
*
示例 2：
输入：s = "()[]{}"
输出：true
*
示例 3：
输入：s = "(]"
输出：false
* */
public class Solution06_ValidParentheses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(isValid(s));
    }

    //map(')','())'),其实代码量都差不多，没啥区别
    public static boolean isValid(String s) {
        HashMap<Character, Character> rightToleft = new HashMap<>();
        rightToleft.put(')', '(');
        rightToleft.put('}', '{');
        rightToleft.put(']', '[');

        Stack<Character> stack = new Stack<>();


        int n = s.length();

        for (int i = 0; i < n; i++) {

            char c = s.charAt(i);

            if (!stack.isEmpty()) {
                if (stack.peek().equals(rightToleft.get(c))) {
                    stack.pop();
                } else if (rightToleft.containsKey(c)){
                    return false;
                }
            }else if(rightToleft.containsKey(c)){
                return false;
            }

            //栈中只允许进入左括号
            if (!rightToleft.containsKey(c)) {
                stack.add(c);
            }
        }

        return stack.isEmpty();
    }


    //map('(',')')
//    public static boolean isValid(String s) {
//
//        HashMap<Character, Character> leftToRight = new HashMap<>();
//        leftToRight.put('(', ')');
//        leftToRight.put('{', '}');
//        leftToRight.put('[', ']');
//
//        Stack<Character> stack = new Stack<>();
//
//
//        Collection<Character> values = leftToRight.values();
//        if (values.contains(s.charAt(0))){
//            return false;
//        }
//
//
//        int n = s.length();
//        Set<Character> keySet = leftToRight.keySet();
//        for (int i = 0; i < n; i++) {
//
//            char c = s.charAt(i);
//
//            if (!stack.isEmpty()){
//                if (leftToRight.get(stack.peek()).equals(c)) {
//                    stack.pop();
//                }else if (!keySet.contains(c)) {
//                    return false;
//                }
//            }else if (!keySet.contains(c)){
//                return false;
//            }
//
//            if (keySet.contains(c)) {
//                stack.add(c);
//            }
//
//        }
//        return stack.isEmpty();
//    }

}

package org.lyflexi.solutions.baseAlgorithm.utils.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test {
    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid("())()((("));
    }
    public static String minRemoveToMakeValid(String s) {
        StringBuilder ret = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        for(char c: s.toCharArray()){
            if(stack.isEmpty() && c == ')'){
                continue;
            }

            if(!stack.isEmpty() && stack.peek() == '(' && c ==')'){
                stack.pop();
                ret.append(c);
                continue;
            }

            if(c == '('){
                stack.push('(');
            }

            if(c == '(' || Character.isLetter(c)){
                ret.append(c);
            }
        }
        if(stack.isEmpty()){
            return ret.toString();
        }

        int cnt = 0;
        for(int i = 0; i<ret.length(); i++){
            if(ret.charAt(i) == '(' && cnt<stack.size()){
                cnt++;
                ret.deleteCharAt(i);
                i--;
            }
        }

        return ret.toString();
    }
}

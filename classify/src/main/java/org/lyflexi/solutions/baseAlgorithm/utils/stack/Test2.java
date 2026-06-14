package org.lyflexi.solutions.baseAlgorithm.utils.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test2 {
    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"4","13","5","/","+"}));
    }
    public static int evalRPN(String[] tokens) {
        int ret = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for(String s: tokens){
            if(stack.size() > 1){
                int v1 = stack.pop();
                int v2 = stack.pop();
                int newV = 0;
                if("+".equals(s)){
                    newV = v1 + v2;
                }else if("-".equals(s)){
                    newV = v1 - v2;
                }else if("*".equals(s)){
                    newV = v1 * v2;
                }else if("/".equals(s)){
                    newV = v1/v2;
                }
                stack.push(newV);
            }else{
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.peek();
        }

}

package org.lyflexi.solutions.queuestack;

import java.util.Stack;

/**
 * @Author: ly
 * @Date: 2024/2/16 16:02
 */
public class Solution05_StackToQueue {
    Stack<Integer> stack;

    public Solution05_StackToQueue() {
        stack = new Stack<>();

    }

    public void push(int x) {

        //StackToQueue必须使用两个Stack,因为stack.pop()操作出的是新入栈的元素,相当于白忙活
        Stack<Integer> tempStack = new Stack<>();

        while (!stack.isEmpty()){
            tempStack.push(stack.pop());
        }

        stack.push(x);

        while(!tempStack.isEmpty()){
            stack.push(tempStack.pop());
        }

    }

    public int pop() {

        if (!stack.isEmpty()){
            return stack.pop();
        }

        return -1;
    }

    public int peek() {
        if (!stack.isEmpty()){
            return stack.peek();
        }
        return -1;
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}

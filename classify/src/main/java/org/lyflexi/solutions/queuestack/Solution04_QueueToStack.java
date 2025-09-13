package org.lyflexi.solutions.queuestack;

import java.util.LinkedList;

/**
 * @Author: ly
 * @Date: 2024/2/16 15:44
 */

/*
* 225. 用队列实现栈
请你使用队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。

实现 MyStack 类：
void push(int x) 将元素 x 压入栈顶。
int pop() 移除并返回栈顶元素。
int top() 返回栈顶元素。
boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
* */
public class Solution04_QueueToStack {

    public LinkedList<Integer> queue;
    public Solution04_QueueToStack() {
        queue = new LinkedList();
    }

    public void push(int x) {

        queue.offer(x);


        int size = queue.size();

        for (int i = 0; i < size - 1; i++) {
            Integer poll = (Integer) queue.poll();
            queue.offer(poll);
        }


    }

    public int pop() {

        if (!queue.isEmpty()){
            return (Integer)queue.poll();
        }
        return -1;

    }

    public int top() {
        if (!queue.isEmpty()){
            return (Integer) queue.peek();
        }
        return -1;
    }

    public boolean empty() {

        return queue.isEmpty();

    }
}

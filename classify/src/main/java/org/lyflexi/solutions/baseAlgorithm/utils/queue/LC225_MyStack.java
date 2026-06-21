package org.lyflexi.solutions.baseAlgorithm.utils.queue;

/**
 * 225. 用队列实现栈
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 *
 * 实现 MyStack 类：
 *
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 *
 *
 * 注意：
 *
 * 你只能使用队列的标准操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 * 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 *
 *
 * 示例：
 *
 * 输入：
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 2, 2, false]
 *
 * 解释：
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // 返回 2
 * myStack.pop(); // 返回 2
 * myStack.empty(); // 返回 False
 *
 *
 * 提示：
 *
 * 1 <= x <= 9
 * 最多调用100 次 push、pop、top 和 empty
 * 每次调用 pop 和 top 都保证栈不为空
 *
 *
 * 进阶：你能否仅用一个队列来实现栈。
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 511,231/779.5K
 * 通过率
 * 65.6%
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 一个队列实现栈
 */
public class LC225_MyStack {
    Deque<Integer> q;
    public LC225_MyStack() {
        q  =  new ArrayDeque<>();
    }

    public void push(int x) {
        int len = q.size();
        q.offer(x);
        for(int i = 0; i< len; i++){
            q.offer(q.pop());
        }
    }

    public int pop() {
        return q.pop();
    }

    public int top() {
        return q.peek();
    }

    public boolean empty() {
        return q.isEmpty();
    }
}

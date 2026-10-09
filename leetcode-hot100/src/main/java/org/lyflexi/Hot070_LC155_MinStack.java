package org.lyflexi;

import java.util.*;

/**
 * 155. 最小栈
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 
 * 实现 MinStack 类:
 * 
 * - MinStack() 初始化堆栈对象。
 * 
 * - void push(int value) 将元素 value 推入堆栈。
 * 
 * - void pop() 删除堆栈顶部的元素。
 * 
 * - int top() 获取堆栈顶部的元素。
 * 
 * - int getMin() 获取堆栈中的最小元素。
 * 
 * 示例 1:
 * 
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * 
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * 
 * 提示：
 * 
 * - -2^31 <= val <= 2^31 - 1
 * 
 * - pop、top 和 getMin 操作总是在 非空栈 上调用
 * 
 * - push, pop, top, and getMin最多被调用 3 * 10^4 次
 */

/**
 * 细节
 * 
 * 一开始栈为空（$n=0$），添加 $val$ 时，我们没有对应的 $preMin[n-1]$。需要特判栈为空的情况吗？
 * 
 * 不需要。初始化的时候，在栈底加一个 $\infty$ 哨兵，作为 $preMin[-1]$。
 * 注：题目保证 $pop,top,getMin$ 都是在非空栈上操作的。
 */
public class Hot070_LC155_MinStack {
    // 注意不要使用 Stack 类，因为它继承自 Vector，是同步的，会导致一些性能问题
    private final Deque<int[]> st = new ArrayDeque<>();

    public Hot070_LC155_MinStack() {
        // 添加栈底哨兵 Integer.MAX_VALUE
        // 这里的 0 写成任意数都可以，反正用不到
        st.push(new int[]{0, Integer.MAX_VALUE});
    }

    public void push(int val) {
        st.push(new int[]{val, Math.min(getMin(), val)});
    }

    public void pop() {
        st.pop();
    }

    public int top() {
        return st.peek()[0];
    }

    public int getMin() {
        return st.peek()[1];
    }
}

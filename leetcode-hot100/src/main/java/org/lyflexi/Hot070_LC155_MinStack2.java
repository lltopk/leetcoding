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
 * 方法二：保存差值
 * 
 * 进阶问题：如果不允许额外保存前缀最小值，栈中只能保存整数（不能保存数对），怎么做？
 * 
 * 如果栈中保存的是 $val$ 与前缀最小值的差值，那么只要我们能实时维护前缀最小值，就能通过差值还原 $val$。
 * 
 * 例如依次插入 $val = 5,6,8,1,2$，计算过程如下表。
 * 
 * 如何阅读下表：
 * 
 * 1. 表格中的差值等于插入的 $val$ 减去插入之前的最小值。
 * 2. $push(val)$ 从上到下阅读，$top$ 和 $pop$ 从下到上阅读。
 * 
 * | $push(val)$  | 最小值  | 差值  | $top$  | $pop$  |
 * |:---:|:---:|:---:|---|---|
 * | $ $  | $\infty$  | $ $  |  $ $ | $ $ |
 * | $5$  | $5$  | $-\infty$  | 最小值  | 最小值增加 $\infty$  |
 * | $6$  | $5$  | $1$  | 最小值$+$差值  | 最小值不变  |
 * | $8$  | $5$  | $3$  | 最小值$+$差值  | 最小值不变  |
 * | $1$  | $1$  | $-4$  |  最小值 | 最小值增加 $4$  |
 * | $2$  | $1$  | $1$  | 最小值$+$差值  | 最小值不变  |
 * 
 * 一般地：
 * 
 * - 初始化前缀最小值 $mn = \infty$。
 * - $push(val)$：先把 $(val - mn)$ 入栈，再更新 $mn$ 为 $min(mn,val)$。
 * - $top$：返回 $mn + max(栈顶,0)$。如果栈顶大于 $0$，说明 $val$ 比 $mn$ 多一个栈顶的值；否则 $val$ 就是 $mn$。
 * - $pop$：把 $mn$ 减少 $min(栈顶,0)$。如果栈顶小于 $0$，这会把 $mn$ 增大；否则 $mn$ 不变。
 * - $getMin$：返回 $mn$ 即可。
 * 
 * 代码实现时，为避免溢出，需要用 64 位整数。
 * 注：题目保证 $pop,top,getMin$ 都是在非空栈上操作的。
 */
public class Hot070_LC155_MinStack2 {
    // 注意不要使用 Stack 类，因为它继承自 Vector，是同步的，会导致一些性能问题
    private final Deque<Long> st = new ArrayDeque<>();
    private long mn = Long.MAX_VALUE / 2; // 避免 val - mn 溢出

    public void push(int val) {
        // 栈中保存 val - 之前的最小值
        st.push(val - mn);
        mn = Math.min(mn, val);
    }

    public void pop() {
        // 如果栈顶是负数，增大 mn，否则不变
        mn -= Math.min(st.pop(), 0);
    }

    public int top() {
        // 如果栈顶是正数，说明实际的 val 比 mn 大，否则 val 等于 mn
        return (int) (mn + Math.max(st.peek(), 0));
    }

    public int getMin() {
        return (int) mn;
    }
}

package org.lyflexi.solutions.baseAlgorithm.utils.stack.list_simulation;

/**
 * 1381. 设计一个支持增量操作的栈
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 请你设计一个支持对其元素进行增量操作的栈。
 *
 * 实现自定义栈类 CustomStack ：
 *
 * CustomStack(int maxSize)：用 maxSize 初始化对象，maxSize 是栈中最多能容纳的元素数量。
 * void push(int x)：如果栈还未增长到 maxSize ，就将 x 添加到栈顶。
 * int pop()：弹出栈顶元素，并返回栈顶的值，或栈为空时返回 -1 。
 * void inc(int k, int val)：栈底的 k 个元素的值都增加 val 。如果栈中元素总数小于 k ，则栈中的所有元素都增加 val 。
 *
 *
 * 示例：
 *
 * 输入：
 * ["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
 * [[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
 * 输出：
 * [null,null,null,2,null,null,null,null,null,103,202,201,-1]
 * 解释：
 * CustomStack stk = new CustomStack(3); // 栈是空的 []
 * stk.push(1);                          // 栈变为 [1]
 * stk.push(2);                          // 栈变为 [1, 2]
 * stk.pop();                            // 返回 2 --> 返回栈顶值 2，栈变为 [1]
 * stk.push(2);                          // 栈变为 [1, 2]
 * stk.push(3);                          // 栈变为 [1, 2, 3]
 * stk.push(4);                          // 栈仍然是 [1, 2, 3]，不能添加其他元素使栈大小变为 4
 * stk.increment(5, 100);                // 栈变为 [101, 102, 103]
 * stk.increment(2, 100);                // 栈变为 [201, 202, 103]
 * stk.pop();                            // 返回 103 --> 返回栈顶值 103，栈变为 [201, 202]
 * stk.pop();                            // 返回 202 --> 返回栈顶值 202，栈变为 [201]
 * stk.pop();                            // 返回 201 --> 返回栈顶值 201，栈变为 []
 * stk.pop();                            // 返回 -1 --> 栈为空，返回 -1
 *
 *
 * 提示：
 *
 * 1 <= maxSize, x, k <= 1000
 * 0 <= val <= 100
 * 每种方法 increment，push 以及 pop 分别最多调用 1000 次
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 列表模拟栈, 用列表数组/模拟栈的好处就是能够按照扫描顺序还原字符串
 *
 * 时间优化
 */
public class LC1381_CustomStack2 {
    //题目需求从栈底扫描增加, 因此用列表模拟栈
    //存pair的原因是时间优化, 执行increment的时候, 我们只标记stack[k-1][1]+=val,
    // 这样pop的时候就知道增量了, 并且出栈后需要向栈低方向低传递增量, 操作都是O1
    List<int[]> stack;
    int maxSize = 0;
    public LC1381_CustomStack2(int maxSize) {
        stack = new ArrayList<>();
        this.maxSize = maxSize;
    }

    public void push(int x) {
        if(stack.size() == maxSize){
            return;
        }
        //初始增量都是0
        stack.add(new int[]{x, 0});
    }

    public int pop() {
        if(stack.isEmpty()){
            return -1;
        }
        int[] peek = stack.get(stack.size()-1);
        stack.remove(stack.size()-1);
        int ret = peek[0] + peek[1];
        if(stack.isEmpty()){
            return ret;
        }
        int[] peek0 = stack.get(stack.size()-1);
        peek0[1] += peek[1];//O(1)操作将增量向栈底方向传递即可
        return ret;
    }

    public void increment(int k, int val) {
        if(stack.isEmpty()){
            return;
        }
        int n = Math.min(k, stack.size());
        stack.get(n-1)[1] +=val;
    }
}

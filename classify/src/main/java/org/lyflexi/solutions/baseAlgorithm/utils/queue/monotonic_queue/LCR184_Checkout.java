package org.lyflexi.solutions.baseAlgorithm.utils.queue.monotonic_queue;

/**
 * LCR 184. 设计自助结算系统
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 请设计一个自助结账系统，该系统需要通过一个队列来模拟顾客通过购物车的结算过程，需要实现的功能有：
 *
 * get_max()：获取结算商品中的最高价格，如果队列为空，则返回 -1
 * add(value)：将价格为 value 的商品加入待结算商品队列的尾部
 * remove()：移除第一个待结算的商品价格，如果队列为空，则返回 -1
 * 注意，为保证该系统运转高效性，以上函数的均摊时间复杂度均为 O(1)
 *
 *
 *
 * 示例 1：
 *
 * 输入:
 * ["Checkout","add","add","get_max","remove","get_max"]
 * [[],[4],[7],[],[],[]]
 *
 * 输出: [null,null,null,7,4,7]
 * 示例 2：
 *
 * 输入:
 * ["Checkout","remove","get_max"]
 * [[],[],[]]
 *
 * 输出: [null,-1,-1]
 *
 *
 * 提示：
 *
 * 1 <= get_max, add, remove 的总操作数 <= 10000
 * 1 <= value <= 10^5
 *
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 192,967/401.6K
 * 通过率
 * 48.0%
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 单调队列
 */
public class LCR184_Checkout {
    Deque<Integer> q0;
    Deque<Integer> qDes;//单调队列
    public LCR184_Checkout() {
        q0 = new ArrayDeque<>();
        qDes = new ArrayDeque<>();
    }

    public int get_max() {
        if(qDes.isEmpty()){
            return -1;
        }
        return qDes.peek();
    }

    public void add(int value) {
        while(! qDes.isEmpty() && qDes.getLast() < value){
            qDes.removeLast();
        }
        qDes.offer(value);
        q0.offer(value);
    }

    public int remove() {
        if(q0.isEmpty()){
            return -1;
        }
        int pop = q0.pop();
        //处理单调队列队首元素的过期问题
        if(pop == qDes.peek()){
            qDes.pop();
        }
        return pop;
    }
}

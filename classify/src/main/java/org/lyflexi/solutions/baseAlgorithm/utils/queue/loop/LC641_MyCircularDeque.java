package org.lyflexi.solutions.baseAlgorithm.utils.queue.loop;

/**
 * 641. 设计循环双端队列
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 设计实现双端队列。
 *
 * 实现 MyCircularDeque 类:
 *
 * MyCircularDeque(int k) ：构造函数,双端队列最大为 k 。
 * boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true ，否则返回 false 。
 * boolean insertLast() ：将一个元素添加到双端队列尾部。如果操作成功返回 true ，否则返回 false 。
 * boolean deleteFront() ：从双端队列头部删除一个元素。 如果操作成功返回 true ，否则返回 false 。
 * boolean deleteLast() ：从双端队列尾部删除一个元素。如果操作成功返回 true ，否则返回 false 。
 * int getFront() )：从双端队列头部获得一个元素。如果双端队列为空，返回 -1 。
 * int getRear() ：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1 。
 * boolean isEmpty() ：若双端队列为空，则返回 true ，否则返回 false  。
 * boolean isFull() ：若双端队列满了，则返回 true ，否则返回 false 。
 *
 *
 * 示例 1：
 *
 * 输入
 * ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * 输出
 * [null, true, true, true, false, 2, true, true, true, 4]
 *
 * 解释
 * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
 * circularDeque.insertLast(1);			        // 返回 true
 * circularDeque.insertLast(2);			        // 返回 true
 * circularDeque.insertFront(3);			        // 返回 true
 * circularDeque.insertFront(4);			        // 已经满了，返回 false
 * circularDeque.getRear();  				// 返回 2
 * circularDeque.isFull();				        // 返回 true
 * circularDeque.deleteLast();			        // 返回 true
 * circularDeque.insertFront(4);			        // 返回 true
 * circularDeque.getFront();				// 返回 4
 *
 *
 *
 * 提示：
 *
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull  调用次数不大于 2000 次
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 82,524/146.3K
 * 通过率
 * 56.4%
 */

/**
 * 实现循环双端队列, 见JDK ArrayDeque
 */
public class LC641_MyCircularDeque {
    int[] q;
    int head = 0, tail = 0;
    public LC641_MyCircularDeque(int k) {
        q = new int[k + 1];
    }

    /**
     实现双端循环队列, 多的方法insertFront, 其他方法和循环队列相同
     */
    public boolean insertFront(int value) {
        if(isFull()){
            return false;
        }
        head  = (head - 1 + q.length) % q.length;
        q[head] = value;
        return true;
    }

    public boolean insertLast(int value) {
        if(isFull()){
            return false;
        }
        q[tail] = value;
        tail = (tail + 1) % q.length;
        return true;
    }

    public boolean deleteFront() {
        if(isEmpty()){
            return false;
        }
        head = (head + 1) % q.length;
        return true;
    }

    /**
     实现双端循环队列, 多的方法deleteLast, 其他方法和循环队列相同
     */
    public boolean deleteLast() {
        if(isEmpty()){
            return false;
        }
        tail = (tail - 1 + q.length) % q.length;
        return true;
    }

    public int getFront() {
        if(isEmpty()){
            return -1;
        }
        return q[head % q.length];
    }

    public int getRear() {
        if(isEmpty()){
            return -1;
        }
        //解决负数取模问题
        return q[(tail-1 + q.length) % q.length];
    }

    public boolean isEmpty() {
        return tail == head;
    }

    public boolean isFull() {
        return (tail + 1) % q.length == head;
    }
}

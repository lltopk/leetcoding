package org.lyflexi.solutions.baseAlgorithm.utils.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1670. 设计前中后队列
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 请你设计一个队列，支持在前，中，后三个位置的 push 和 pop 操作。
 *
 * 请你完成 FrontMiddleBack 类：
 *
 * FrontMiddleBack() 初始化队列。
 * void pushFront(int val) 将 val 添加到队列的 最前面 。
 * void pushMiddle(int val) 将 val 添加到队列的 正中间 。
 * void pushBack(int val) 将 val 添加到队里的 最后面 。
 * int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * 请注意当有 两个 中间位置的时候，选择靠前面的位置进行操作。比方说：
 *
 * 将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。
 * 从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3 ，数组变为 [1, 2, 4, 5, 6] 。
 *
 *
 * 示例 1：
 *
 * 输入：
 * ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
 * [[], [1], [2], [3], [4], [], [], [], [], []]
 * 输出：
 * [null, null, null, null, null, 1, 3, 4, 2, -1]
 *
 * 解释：
 * FrontMiddleBackQueue q = new FrontMiddleBackQueue();
 * q.pushFront(1);   // [1]
 * q.pushBack(2);    // [1, 2]
 * q.pushMiddle(3);  // [1, 3, 2]
 * q.pushMiddle(4);  // [1, 4, 3, 2]
 * q.popFront();     // 返回 1 -> [4, 3, 2]
 * q.popMiddle();    // 返回 3 -> [4, 2]
 * q.popMiddle();    // 返回 4 -> [2]
 * q.popBack();      // 返回 2 -> []
 * q.popFront();     // 返回 -1 -> [] （队列为空）
 *
 *
 * 提示：
 *
 * 1 <= val <= 109
 * 最多调用 1000 次 pushFront， pushMiddle， pushBack， popFront， popMiddle 和 popBack 。
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 29,493/49.2K
 * 通过率
 * 60.0%
 */

/**
 * 由于我们约定了q2的大小, 最多比q1大1,
 *
 * 即 0 <= q2.size() - q1.size() <=1
 *
 * 说明整个LC1670_FrontMiddleBackQueue2为空的条件是q2.isEmpty(), 与q1无关
 */
public class LC1670_FrontMiddleBackQueue2 {
    //如果只是pushBack/popFront  pushFront/popBack, 则用一个双端队列就可以了
    //要想做到操作中间, 用两个双端队列q1, q2, 然后我们想办法维护这两个队列大小尽可能平均
    Deque<Integer> q1;
    Deque<Integer> q2;

    public LC1670_FrontMiddleBackQueue2() {
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
    }

    //这就要求每次当q2.size() > q1.size() + 1的时候, 或者q1.size() > q2.size()的时候, 及时balance调整两个队列的大小
    private void balance(){
        //pushBack
        if(q2.size() > q1.size() + 1){
            q1.offer(q2.pop());
        }

        //pushFront
        if(q1.size() > q2.size()){
            q2.addFirst(q1.removeLast());
        }
    }

    public void pushFront(int val) {
        q1.addFirst(val);
        balance();
    }

    public void pushMiddle(int val) {
        if(q1.size() == q2.size()){
            q2.addFirst(val);
            return;
        }
        //我们维护了q2顶多比q1大1
        q1.addLast(val);
        return;
    }

    public void pushBack(int val) {
        q2.offer(val);
        balance();
    }

    public int popFront() {
        if(isEmpty()){
            return -1;
        }
        int ret = 0;
        //特殊情况
        if(q1.isEmpty()){
            ret = q2.pop();
        }else{
            //优先从q1出队
            ret = q1.pop();
        }
        balance();
        return ret;
    }

    public int popMiddle() {
        if(isEmpty()){
            return -1;
        }
        if(q1.size() == q2.size()){
            return q1.removeLast();
        }
        //我们维护了q2顶多比q1大1
        return q2.pop();
    }

    public int popBack() {
        if(isEmpty()){
            return -1;
        }
        int ret = q2.removeLast();
        balance();
        return ret;
    }

    private boolean isEmpty(){
        return q2.isEmpty();
    }
}

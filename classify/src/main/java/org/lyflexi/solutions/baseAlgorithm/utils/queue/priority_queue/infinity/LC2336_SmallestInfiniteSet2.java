package org.lyflexi.solutions.baseAlgorithm.utils.queue.priority_queue.infinity;

/**
 * 2336. 无限集中的最小数字
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 现有一个包含所有正整数的集合 [1, 2, 3, 4, 5, ...] 。
 *
 * 实现 SmallestInfiniteSet 类：
 *
 * SmallestInfiniteSet() 初始化 SmallestInfiniteSet 对象以包含 所有 正整数。
 * int popSmallest() 移除 并返回该无限集中的最小整数。
 * void addBack(int num) 如果正整数 num 不 存在于无限集中，则将一个 num 添加 到该无限集中。
 *
 *
 * 示例：
 *
 * 输入
 * ["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
 * [[], [2], [], [], [], [1], [], [], []]
 * 输出
 * [null, null, 1, 2, 3, null, 1, 4, 5]
 *
 * 解释
 * SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
 * smallestInfiniteSet.addBack(2);    // 2 已经在集合中，所以不做任何变更。
 * smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 是最小的整数，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 2 ，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 3 ，并将其从集合中移除。
 * smallestInfiniteSet.addBack(1);    // 将 1 添加到该集合中。
 * smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 在上一步中被添加到集合中，
 *                                    // 且 1 是最小的整数，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 4 ，并将其从集合中移除。
 * smallestInfiniteSet.popSmallest(); // 返回 5 ，并将其从集合中移除。
 *
 *
 * 提示：
 *
 * 1 <= num <= 1000
 * 最多调用 popSmallest 和 addBack 方法 共计 1000 次
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 46,266/65K
 * 通过率
 * 71.2%
 */

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * 优先级队列, + Set时间优化
 */
public class LC2336_SmallestInfiniteSet2 {
    //仅存储从无限集中访问过的元素, 且不可重复
    Queue<Integer> priorityQueue;
    //时间优化, 用set判断重复, 复杂度是O(1).  如果用priorityQueue判断重复是O(N)
    Set<Integer> set;
    int number = 1;
    public LC2336_SmallestInfiniteSet2() {
        priorityQueue = new PriorityQueue<>();
        set = new HashSet<>();
    }

    public int popSmallest() {
        //堆为空, 按照无限集的顺序返回
        if(priorityQueue.isEmpty()){
            return number++;
        }
        int v = priorityQueue.poll();
        set.remove(v);
        return v;
    }

    public void addBack(int num) {
        //set.add(num)会返回boolean值来判断set中是否存在
        if(num < number && set.add(num)){
            priorityQueue.offer(num);
        }

        //如果num >= number, 说明还在无限集中, 不用管
    }
}

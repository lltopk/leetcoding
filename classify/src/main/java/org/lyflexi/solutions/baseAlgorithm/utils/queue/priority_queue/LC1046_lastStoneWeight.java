package org.lyflexi.solutions.baseAlgorithm.utils.queue.priority_queue;

/**
 * 1046. 最后一块石头的重量
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 有一堆石头，每块石头的重量都是正整数。
 *
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 *
 *
 *
 * 示例：
 *
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
 * 再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
 * 接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
 * 最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
 *
 *
 * 提示：
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 129,182/196.6K
 * 通过率
 * 65.7%
 */

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 优先级队列
 */
public class LC1046_lastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        Queue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> b - a);

        for(int x: stones){
            priorityQueue.offer(x);
        }

        while (priorityQueue.size() > 1) {
            int v1 = priorityQueue.poll();
            int v2 = priorityQueue.poll();

            if (v1 != v2) {
                priorityQueue.offer(v1 - v2);
            }
        }

        return priorityQueue.isEmpty()? 0: priorityQueue.peek();
    }
}

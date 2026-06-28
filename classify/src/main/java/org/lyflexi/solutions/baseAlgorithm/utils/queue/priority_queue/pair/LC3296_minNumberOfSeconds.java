package org.lyflexi.solutions.baseAlgorithm.utils.queue.priority_queue.pair;

/**
 * 3296. 移山所需的最少秒数
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数 mountainHeight 表示山的高度。
 *
 * 同时给你一个整数数组 workerTimes，表示工人们的工作时间（单位：秒）。
 *
 * 工人们需要 同时 进行工作以 降低 山的高度。对于工人 i :
 *
 * 山的高度降低 1，需要 workerTimes[i] 秒。
 * 山的高度降低 2，需要 workerTimes[i] * 2 秒。
 * ...
 * 山的高度降低 x，需要 workerTimes[i] * x 秒。
 * 工人 i 所花费的总时间是所有 x 单位所需时间的总和。由于所有工人同时操作，所需的总时间是任何工人花费的 最大 时间。
 *
 * 返回一个整数，表示工人们使山的高度降低到 0 所需的 最少 秒数。
 *
 *
 *
 * 示例 1：
 *
 * 输入： mountainHeight = 4, workerTimes = [2,1,1]
 *
 * 输出： 3
 *
 * 解释：
 *
 * 将山的高度降低到 0 的一种方式是：
 *
 * 工人 0 将高度降低 1，花费 workerTimes[0] = 2 秒。
 * 工人 1 将高度降低 2，花费 workerTimes[1] + workerTimes[1] * 2 = 3 秒。
 * 工人 2 将高度降低 1，花费 workerTimes[2] = 1 秒。
 * 因为工人同时工作，所需的最少时间为 max(2, 3, 1) = 3 秒。
 *
 * 示例 2：
 *
 * 输入： mountainHeight = 10, workerTimes = [3,2,2,4]
 *
 * 输出： 12
 *
 * 解释：
 *
 * 工人 0 将高度降低 2，花费 workerTimes[0] + workerTimes[0] * 2 = 9 秒。
 * 工人 1 将高度降低 3，花费 workerTimes[1] + workerTimes[1] * 2 + workerTimes[1] * 3 = 12 秒。
 * 工人 2 将高度降低 3，花费 workerTimes[2] + workerTimes[2] * 2 + workerTimes[2] * 3 = 12 秒。
 * 工人 3 将高度降低 2，花费 workerTimes[3] + workerTimes[3] * 2 = 12 秒。
 * 所需的最少时间为 max(9, 12, 12, 12) = 12 秒。
 *
 * 示例 3：
 *
 * 输入： mountainHeight = 5, workerTimes = [1]
 *
 * 输出： 15
 *
 * 解释：
 *
 * 这个示例中只有一个工人，所以答案是 workerTimes[0] + workerTimes[0] * 2 + workerTimes[0] * 3 + workerTimes[0] * 4 + workerTimes[0] * 5 = 15 秒。
 *
 *
 *
 * 提示：
 *
 * 1 <= mountainHeight <= 105
 * 1 <= workerTimes.length <= 104
 * 1 <= workerTimes[i] <= 106
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 21,922/42.7K
 * 通过率
 * 51.3%
 */

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 优先队列
 */
public class LC3296_minNumberOfSeconds {
    /**
     虽说是同时工作, 但其实你有权利把当前高度交给谁来降低1
     */
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        int n = workerTimes.length;
        //a[0]: base
        //a[1]: 下次要花费多少时间(等差数列尾项)
        //a[2]: 下次时间累加总和(等差数列和), 最小堆中的元素a[2]即为最终答案
        Queue<long[]> priorityQueue = new PriorityQueue<>((a, b) -> (int)(a[2] - b[2]));
        for(int x: workerTimes){
            priorityQueue.offer(new long[]{x, x, x});
        }

        //因为题目公式固定是个等差数列, 所以每次用最小堆来计算就能够保证答案最小
        long ans = 0;
        while(mountainHeight-- > 0){
            long[] peek = priorityQueue.poll();
            long base = peek[0], cur = peek[1], next = peek[2];
            //更新答案
            ans = peek[2];
            //更新long[]
            peek[1] = base + cur;//新的尾项
            peek[2] += peek[1];//新的数列和
            priorityQueue.offer(peek);
        }
        return ans;
    }
}

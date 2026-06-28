package org.lyflexi.solutions.baseAlgorithm.utils.queue.priority_queue;

/**
 * 2233. K 次增加后的最大乘积
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个非负整数数组 nums 和一个整数 k 。每次操作，你可以选择 nums 中 任一 元素并将它 增加 1 。
 *
 * 请你返回 至多 k 次操作后，能得到的 nums的 最大乘积 。由于答案可能很大，请你将答案对 109 + 7 取余后返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [0,4], k = 5
 * 输出：20
 * 解释：将第一个数增加 5 次。
 * 得到 nums = [5, 4] ，乘积为 5 * 4 = 20 。
 * 可以证明 20 是能得到的最大乘积，所以我们返回 20 。
 * 存在其他增加 nums 的方法，也能得到最大乘积。
 * 示例 2：
 *
 * 输入：nums = [6,3,3,2], k = 2
 * 输出：216
 * 解释：将第二个数增加 1 次，将第四个数增加 1 次。
 * 得到 nums = [6, 4, 3, 3] ，乘积为 6 * 4 * 3 * 3 = 216 。
 * 可以证明 216 是能得到的最大乘积，所以我们返回 216 。
 * 存在其他增加 nums 的方法，也能得到最大乘积。
 *
 *
 * 提示：
 *
 * 1 <= nums.length, k <= 105
 * 0 <= nums[i] <= 106
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 14,352/35.7K
 * 通过率
 * 40.2%
 */

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 优先级队列
 */
public class LC2233_maximumProduct {
    int MOD = 1_000_000_007;
    public int maximumProduct(int[] nums, int k) {
        //贪心：每次给最小的元素加一
        Queue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> (int)(a - b));
        for(int x: nums){
            priorityQueue.offer(x);
        }

        while(k-- >0){
            priorityQueue.offer(priorityQueue.poll() + 1);
        }

        long ret = 1;
        for(int x: priorityQueue){
            ret = (ret % MOD) * (x % MOD) % MOD;
        }
        return (int)ret;
    }
}

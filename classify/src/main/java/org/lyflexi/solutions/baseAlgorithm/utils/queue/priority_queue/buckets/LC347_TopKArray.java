package org.lyflexi.solutions.baseAlgorithm.utils.queue.priority_queue.buckets;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 347. 前 K 个高频元素
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,2,2,3], k = 2
 *
 * 输出：[1,2]
 *
 * 示例 2：
 *
 * 输入：nums = [1], k = 1
 *
 * 输出：[1]
 *
 * 示例 3：
 *
 * 输入：nums = [1,2,1,2,1,2,3,1,3,2], k = 2
 *
 * 输出：[1,2]
 *
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 *
 *
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 958,701/1.5M
 * 通过率
 * 65.9%
 */

/**
 * pair优先队列 + 哈希
 */
public class LC347_TopKArray {

    public int[] topKByPriotity(int[] nums, int k) {

        //小顶堆
        Queue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        //分组计数
        Map<Integer, Integer> helper = new HashMap<>();
        for(int x: nums){
            helper.merge(x, 1, Integer::sum);
        }

        int[] ret = new int[k];
        //枚举哈希, 保持优先级队列中始终只有k个元素
        for(int key: helper.keySet()){
            if(priorityQueue.size() == k){
                if(priorityQueue.peek()[1] < helper.get(key)){
                    priorityQueue.poll();
                    priorityQueue.offer(new int[]{key, helper.get(key)});
                }
            }else{
                priorityQueue.offer(new int[]{key, helper.get(key)});
            }
        }

        for (int i = 0; i < k; i++) {
            ret[i] = priorityQueue.poll()[0];
        }
        return ret;
    }
}

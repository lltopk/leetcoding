package org.lyflexi.solutions.baseAlgorithm.utils.queue.priority_queue.buckets;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Author: ly
 * @Date: 2024/3/8 10:17
 */

/**
 * 215. 数组中的第K个最大元素
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 *
 *
 * 提示：
 *
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,736,083/2.9M
 * 通过率
 * 60.1%
 */


public class LC215_TopKth {
    public int findKthLargest(int[] nums, int k) {

        Queue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> (int)(b - a));
        for(int x: nums){
            priorityQueue.offer(x);
        }

        int ret = 0;
        while(k-- >0){
            ret = priorityQueue.poll();
        }
        return ret;

    }
}

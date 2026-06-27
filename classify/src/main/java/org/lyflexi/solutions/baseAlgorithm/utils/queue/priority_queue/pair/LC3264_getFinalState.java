package org.lyflexi.solutions.baseAlgorithm.utils.queue.priority_queue.pair;

/**
 * 3264. K 次乘运算后的最终数组 I
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums ，一个整数 k  和一个整数 multiplier 。
 *
 * 你需要对 nums 执行 k 次操作，每次操作中：
 *
 * 找到 nums 中的 最小 值 x ，如果存在多个最小值，选择最 前面 的一个。
 * 将 x 替换为 x * multiplier 。
 * 请你返回执行完 k 次乘运算之后，最终的 nums 数组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,1,3,5,6], k = 5, multiplier = 2
 *
 * 输出：[8,4,6,5,6]
 *
 * 解释：
 *
 * 操作	结果
 * 1 次操作后	[2, 2, 3, 5, 6]
 * 2 次操作后	[4, 2, 3, 5, 6]
 * 3 次操作后	[4, 4, 3, 5, 6]
 * 4 次操作后	[4, 4, 6, 5, 6]
 * 5 次操作后	[8, 4, 6, 5, 6]
 * 示例 2：
 *
 * 输入：nums = [1,2], k = 3, multiplier = 4
 *
 * 输出：[16,8]
 *
 * 解释：
 *
 * 操作	结果
 * 1 次操作后	[4, 2]
 * 2 次操作后	[4, 8]
 * 3 次操作后	[16, 8]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 * 1 <= k <= 10
 * 1 <= multiplier <= 5
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 25,750/31.4K
 * 通过率
 * 82.1%
 */

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * pair堆
 */
public class LC3264_getFinalState {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        Queue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            //当值相等时，PriorityQueue 不保证按下标排序，因此上面要特判
            //题目要求, 找到 nums 中的 最小 值 x ，如果存在多个最小值，选择最 前面 的一个。
            return a[1] - b[1];
        });

        for(int i = 0; i< nums.length; i++){
            priorityQueue.offer(new int[]{nums[i], i});
        }

        while(k-- > 0 && ! priorityQueue.isEmpty()){
            int[] peek = priorityQueue.poll();
            peek[0] *= multiplier;
            nums[peek[1]] = peek[0];
            priorityQueue.offer(peek);
        }

        return nums;
    }
}

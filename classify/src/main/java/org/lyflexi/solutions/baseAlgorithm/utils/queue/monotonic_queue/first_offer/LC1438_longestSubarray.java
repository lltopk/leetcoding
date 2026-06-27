package org.lyflexi.solutions.baseAlgorithm.utils.queue.monotonic_queue.first_offer;

/**
 * 1438. 绝对差不超过限制的最长连续子数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [8,2,4,7], limit = 4
 * 输出：2
 * 解释：所有子数组如下：
 * [8] 最大绝对差 |8-8| = 0 <= 4.
 * [8,2] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
 * [2] 最大绝对差 |2-2| = 0 <= 4.
 * [2,4] 最大绝对差 |2-4| = 2 <= 4.
 * [2,4,7] 最大绝对差 |2-7| = 5 > 4.
 * [4] 最大绝对差 |4-4| = 0 <= 4.
 * [4,7] 最大绝对差 |4-7| = 3 <= 4.
 * [7] 最大绝对差 |7-7| = 0 <= 4.
 * 因此，满足题意的最长子数组的长度为 2 。
 * 示例 2：
 *
 * 输入：nums = [10,1,2,4,7,2], limit = 5
 * 输出：4
 * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 * 示例 3：
 *
 * 输入：nums = [4,2,2,2,4,4,2,2], limit = 0
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 0 <= limit <= 109
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 71,963/132.8K
 * 通过率
 * 54.2%
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 单调队列
 */
public class LC1438_longestSubarray {
    //好题目, 不定长滑动窗口 + 单调队列
    //但是用1个队列无法同时保存最大值和最小值,因为单调队列为了维护一种极值，另一种极值的信息会丢失.
    // 比如维护最大值时(递减队列)，较小元素会被循环删除；维护最小值时(递增队列)，较大元素会被循环删除。
    public int longestSubarray(int[] nums, int limit) {
        int ret = 0, l = 0;//滑动窗口求最大长度
        Deque<Integer> dequeDes = new ArrayDeque<>();
        Deque<Integer> dequeInc = new ArrayDeque<>();
        for(int r = 0; r<nums.length; r++){
            while(! dequeDes.isEmpty() && nums[dequeDes.getLast()] < nums[r]){
                dequeDes.removeLast();
            }
            while(! dequeInc.isEmpty() && nums[dequeInc.getLast()] > nums[r]){
                dequeInc.removeLast();
            }

            //1.右边入队列
            dequeDes.offer(r);
            dequeInc.offer(r);

            //2.左边出队列, 不定长滑动窗口收缩的时候要用while
            while(nums[dequeDes.getFirst()] - nums[dequeInc.getFirst()] > limit){
                l++;
                if(l > dequeDes.getFirst()){
                    dequeDes.pop();
                }
                if(l > dequeInc.getFirst()){
                    dequeInc.pop();
                }
            }

            //3.更新答案
            ret = Math.max(ret, r - l + 1);
        }
        return ret;
    }
}

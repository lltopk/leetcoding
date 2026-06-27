package org.lyflexi.solutions.baseAlgorithm.utils.queue.monotonic_queue.first_offer;

/**
 * 2762. 不间断子数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始的整数数组 nums 。nums 的一个子数组如果满足以下条件，那么它是 不间断 的：
 *
 * i，i + 1 ，...，j  表示子数组中的下标。对于所有满足 i <= i1, i2 <= j 的下标对，都有 0 <= |nums[i1] - nums[i2]| <= 2 。
 * 请你返回 不间断 子数组的总数目。
 *
 * 子数组是一个数组中一段连续 非空 的元素序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,4,2,4]
 * 输出：8
 * 解释：
 * 大小为 1 的不间断子数组：[5], [4], [2], [4] 。
 * 大小为 2 的不间断子数组：[5,4], [4,2], [2,4] 。
 * 大小为 3 的不间断子数组：[4,2,4] 。
 * 没有大小为 4 的不间断子数组。
 * 不间断子数组的总数目为 4 + 3 + 1 = 8 。
 * 除了这些以外，没有别的不间断子数组。
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：6
 * 解释：
 * 大小为 1 的不间断子数组：[1], [2], [3] 。
 * 大小为 2 的不间断子数组：[1,2], [2,3] 。
 * 大小为 3 的不间断子数组：[1,2,3] 。
 * 不间断子数组的总数目为 3 + 2 + 1 = 6 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 单调队列 + 滑动窗口求个数
 */
public class LC2762_continuousSubarrays {
    public long continuousSubarrays(int[] nums) {
        return longestSubarray(nums, 2);
    }
    private long longestSubarray(int[] nums, int limit) {
        long ret = 0, l = 0;//滑动窗口求最大长度
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

            //2.左边出队列, 不定长滑动窗口收缩的时候要用while, 求个数, 越短越合法
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
            ret += r - l + 1;
        }
        return ret;
    }
}

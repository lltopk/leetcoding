package org.lyflexi.solutions.baseAlgorithm.slippoints.winFixedSize;

/**
 * 3439. 重新安排会议得到最多空余时间 I
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数 eventTime 表示一个活动的总时长，这个活动开始于 t = 0 ，结束于 t = eventTime 。
 *
 * 同时给你两个长度为 n 的整数数组 startTime 和 endTime 。它们表示这次活动中 n 个时间 没有重叠 的会议，其中第 i 个会议的时间为 [startTime[i], endTime[i]] 。
 *
 * 你可以重新安排 至多 k 个会议，安排的规则是将会议时间平移，且保持原来的 会议时长 ，你的目的是移动会议后 最大化 相邻两个会议之间的 最长 连续空余时间。
 *
 * 移动前后所有会议之间的 相对 顺序需要保持不变，而且会议时间也需要保持互不重叠。
 *
 * 请你返回重新安排会议以后，可以得到的 最大 空余时间。
 *
 * 注意，会议 不能 安排到整个活动的时间以外。
 *
 *
 *
 * 示例 1：
 *
 * 输入：eventTime = 5, k = 1, startTime = [1,3], endTime = [2,5]
 *
 * 输出：2
 *
 * 解释：
 *
 *
 *
 * 将 [1, 2] 的会议安排到 [2, 3] ，得到空余时间 [0, 2] 。
 *
 * 示例 2：
 *
 * 输入：eventTime = 10, k = 1, startTime = [0,2,9], endTime = [1,4,10]
 *
 * 输出：6
 *
 * 解释：
 *
 *
 *
 * 将 [2, 4] 的会议安排到 [1, 3] ，得到空余时间 [3, 9] 。
 *
 * 示例 3：
 *
 * 输入：eventTime = 5, k = 2, startTime = [0,1,2,3,4], endTime = [1,2,3,4,5]
 *
 * 输出：0
 *
 * 解释：
 *
 * 活动中的所有时间都被会议安排满了。
 *
 *
 *
 * 提示：
 *
 * 1 <= eventTime <= 109
 * n == startTime.length == endTime.length
 * 2 <= n <= 105
 * 1 <= k <= n
 * 0 <= startTime[i] < endTime[i] <= eventTime
 * endTime[i] <= startTime[i + 1] 其中 i 在范围 [0, n - 2] 之间。
 */

/**
 * 固定尺寸的滑动窗口
 */
public class LC3439_maxFreeTime {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        //题目转化为, 有n+1个空余时间段, 求窗口大小固定为k+1的最大空余时间和
        // 其中空余时间段包括最左边的[0, startTime[0]], 和最右边的[endTime[n-1], eventTime], 将空余时间段抽象为新数组
        // 其中窗口大小为移动次数 + 1, 只是窗口中每个空闲段的值不同
        int n = startTime.length;
        int[] free = new int[n+1];
        free[0] = startTime[0];
        for(int i =1; i<n; i++){
            free[i] = startTime[i] - endTime[i-1];
        }
        free[n] = eventTime - endTime[n-1];

        int ans = 0;
        int l = 0;
        int helper = 0;
        //对新数组free采用固定尺寸的滑动窗口
        for(int r = 0; r < n+1; r++){
            helper+=free[r];
            if(r - l +1 == k+1){
                ans = Math.max(ans, helper);

                helper -= free[l];
                l++;
            }
        }

        return ans;
    }
}

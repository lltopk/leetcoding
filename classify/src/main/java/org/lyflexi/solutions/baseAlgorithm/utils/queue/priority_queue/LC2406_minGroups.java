package org.lyflexi.solutions.baseAlgorithm.utils.queue.priority_queue;

/**
 * 2406. 将区间分为最少组数
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个二维整数数组 intervals ，其中 intervals[i] = [lefti, righti] 表示 闭 区间 [lefti, righti] 。
 *
 * 你需要将 intervals 划分为一个或者多个区间 组 ，每个区间 只 属于一个组，且同一个组中任意两个区间 不相交 。
 *
 * 请你返回 最少 需要划分成多少个组。
 *
 * 如果两个区间覆盖的范围有重叠（即至少有一个公共数字），那么我们称这两个区间是 相交 的。比方说区间 [1, 5] 和 [5, 8] 相交。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
 * 输出：3
 * 解释：我们可以将区间划分为如下的区间组：
 * - 第 1 组：[1, 5] ，[6, 8] 。
 * - 第 2 组：[2, 3] ，[5, 10] 。
 * - 第 3 组：[1, 10] 。
 * 可以证明无法将区间划分为少于 3 个组。
 * 示例 2：
 *
 * 输入：intervals = [[1,3],[5,6],[8,10],[11,13]]
 * 输出：1
 * 解释：所有区间互不相交，所以我们可以把它们全部放在一个组内。
 *
 *
 * 提示：
 *
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * 1 <= lefti <= righti <= 106
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 17,934/33.5K
 * 通过率
 * 53.6%
 */

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 优先级队列
 */
public class LC2406_minGroups {
    //输入：intervals = [[1,3],[5,6],[8,10],[11,13]]
    //输出：1
    //上述特殊的例子启发我们先按照left排序, 这样我们仅需考虑前一个的right和后一个的left关系即可
    public int minGroups(int[][] intervals) {
        //总是用枚举到的left贪心的与之前最小的right比较机会最大， 如果min(right) < left表示可以归为一组， 否则先前剩下的right更大不可能归为一组
        //right可以用优先级队列小顶堆来存储
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for(int[] interval: intervals){
            if(! priorityQueue.isEmpty() && priorityQueue.peek() < interval[0]){
                //和堆中最小的right比较， right < interval[0], 表示可以归为一组
                priorityQueue.poll();
            }
            priorityQueue.offer(interval[1]);
        }
        //最后堆中只剩下每组的最后一个interval[1]
        //[1,5],[1,10],[2,3],[5,10],[6,8]
        //5
        //10
        //3, 10
        //10, 10
        //8, 10, 10
        return priorityQueue.size();
    }
}

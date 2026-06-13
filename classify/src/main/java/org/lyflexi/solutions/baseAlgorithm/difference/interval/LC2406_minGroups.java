package org.lyflexi.solutions.baseAlgorithm.difference.interval;

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
 */

/**
 * 差分思想
 */
public class LC2406_minGroups {
    //将每个区间视为一个人,他在start上车, end+1下车, 求整个旅途期间车上最大人数
    //车上共存的人数就是必须要划分的组数
    public int minGroups(int[][] intervals) {
        //这题差分数组索引不到10(9), 因此可以初始化差分数组长度不会OOM, 不用TreeMap
        int[] diff = new int[1_000_002];
        for(int[] interval: intervals){
            diff[interval[0]]++;
            diff[interval[1]+1]--;
        }

        int s = 0, ans = 0;
        for(int v: diff){
            s += v;
            ans = Math.max(ans, s);
        }

        return ans;
    }
}

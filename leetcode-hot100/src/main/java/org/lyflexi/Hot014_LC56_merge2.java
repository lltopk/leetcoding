package org.lyflexi;

import java.util.*;

/**
 * 56. 合并区间
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [start_i, end_i] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * 
 * 示例 1：
 * 
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 
 * 示例 2：
 * 
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 
 * 示例 3：
 * 
 * 输入：intervals = [[4,7],[1,4]]
 * 输出：[[1,7]]
 * 解释：区间 [1,4] 和 [4,7] 可被视为重叠区间。
 * 
 * 提示：
 * 
 * - 1 <= intervals.length <= 10^4
 * 
 * - intervals[i].length == 2
 * 
 * - 0 <= start_i <= end_i <= 10^4
 */

/**
 * 写法二
 * 
 * 直接生成合并后的区间，不修改 $ans$ 中的区间。
 */
public class Hot014_LC56_merge2 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (p, q) -> p[0] - q[0]); // 按照左端点从小到大排序

        int n = intervals.length;
        List<int[]> ans = new ArrayList<>();
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            left = Math.min(left, intervals[i][0]);
            right = Math.max(right, intervals[i][1]);
            // 下一个区间与 [left, right] 不相交
            if (i == n - 1 || intervals[i + 1][0] > right) {
                ans.add(new int[]{left, right});
                left = Integer.MAX_VALUE;
            }
        }

        return ans.toArray(new int[ans.size()][]);
    }
}

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
 * 方法二：差分数组
 * 
 * 创建一个计数数组 $cnt$，初始值均为 $0$。
 * 
 * 对于区间 $[start,end]$，把 $cnt[start],cnt[start+1],\ldots,cnt[end]$ 都增加 $1$。最终满足 $cnt[i]>0$ 的连续下标，就是合并后的区间。例如 $intervals= [[0,2],[1,3],[5,6]]$，对应的 $cnt=[1,2,2,1,0,1,1]$，其中下标 $i=0,1,2,3,5,6$ 的 $cnt[i] > 0$，所以合并后的区间为 $[0,3]$ 和 $[5,6]$。
 * 
 * 然而，这个做法有一个 bug，例如 $intervals= [[1,2],[3,4]]$，这两个区间不能合并，但按照上述做法，由于 $cnt=[0,1,1,1,1]$，我们会误认为合并后的区间为 $[1,4]$。
 * 
 * 解决办法：把区间左右端点乘以 $2$，例如 $[1,2],[3,4]$ 变成 $[2,4],[6,8]$，这样就把相邻的区间用整数 $5$ 隔开了。对应的 $cnt=[0,0,1,1,1,0,1,1,1]$，区间为 $[2,4],[6,8]$。最后再把左右端点除以 $2$，得到 $[1,2],[3,4]$。
 * 
 * 如何快速实现区间加一？请看 差分数组原理讲解。
 */
public class Hot014_LC56_merge3 {
    public int[][] merge(int[][] intervals) {
        int mx = 0;
        for (int[] p : intervals) {
            mx = Math.max(mx, p[1]);
        }

        int[] diff = new int[mx * 2 + 2];
        for (int[] p : intervals) {
            // 把区间 [p[0]*2, p[1]*2] 增加 1
            diff[p[0] * 2]++;
            diff[p[1] * 2 + 1]--;
        }

        List<int[]> ans = new ArrayList<>();
        int sumD = 0;
        int start = -1; // -1 表示尚未遇到合并后的区间左端点
        for (int i = 0; i < diff.length; i++) {
            sumD += diff[i]; // 计算 diff 的前缀和
            if (sumD > 0) {
                if (start < 0) {
                    start = i; // 合并后的区间左端点
                }
            } else if (start >= 0) {
                // i-1 是合并后的区间右端点
                // 由于乘 2 操作，区间左右端点都是偶数，所以 i-1 是偶数，i 是奇数，(i-1)/2 == floor(i/2)
                ans.add(new int[]{start / 2, i / 2});
                start = -1;
            }
        }
        // 注：最后一轮循环 sumD == 0，我们不会漏掉最后一个区间
        return ans.toArray(new int[ans.size()][]);
    }
}

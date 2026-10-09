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
 * 方法三：扫描线
 * 
 * 想象一根垂线从左到右，缓缓扫过每个区间。在这个过程中，用一个计数器 $cnt$ 表示当前垂线与多少个区间相交。
 * 
 * - 如果垂线遇到区间左端点 $start$，则垂线开始与该区间相交，把 $cnt$ 加一。如果加一前 $cnt=0$，则说明我们开始了一段新的合并区间，$start$ 是合并后的区间左端点。
 * - 如果垂线遇到区间右端点 $end$，则垂线结束与该区间相交，把 $cnt$ 减一。如果减一后 $cnt=0$，则说明 $end$ 是合并后的区间右端点。
 * 
 * 区间 $[1,3]$ 和 $[2,6]$ 的合并过程如下：
 * 
 * 1. 初始化 $cnt = 0$。
 * 2. 扫描线遇到左端点 $1$，现在 $cnt = 1$。由于 $cnt$ 增加之前是 $0$，记录合并区间的左端点为 $1$。
 * 3. 扫描线遇到左端点 $2$，现在 $cnt = 2$。
 * 4. 扫描线遇到右端点 $3$，现在 $cnt = 1$。这说明我们仍然在一个区间内，合并过程没有结束。
 * 5. 扫描线遇到右端点 $6$，现在 $cnt = 0$。合并结束，把区间 $[1,6]$ 加入答案。
 * 顺带一提，回顾方法二中的例子，对于 $[1,2],[3,4]$ 这样的区间，我们会在 $2$ 这个位置就判断出 $[1,2]$ 是个独立的区间，不会把 $[1,2]$ 和 $[3,4]$ 合并。
 */
public class Hot014_LC56_merge4 {
    public int[][] merge(int[][] intervals) {
        Map<Integer, Integer> events = new TreeMap<>();
        for (int[] p : intervals) {
            events.merge(p[0], 1, Integer::sum); // 垂线遇到左端点则加一
            events.merge(p[1], -1, Integer::sum); // 垂线遇到右端点则减一
            // 这样处理后，就可以把 cnt 的更新逻辑统一成 cnt += events.get(x)，无需区分左右端点
        }

        List<int[]> ans = new ArrayList<>();
        int cnt = 0;
        int start = 0; // start 的初始值随意，0 可以换成任意值
        for (Map.Entry<Integer, Integer> e : events.entrySet()) {
            int x = e.getKey();
            if (cnt == 0) { // 扫描线开始与区间相交
                start = x; // x 是合并后的区间左端点
            }
            cnt += e.getValue();
            if (cnt == 0) { // 扫描线结束与区间相交
                ans.add(new int[]{start, x}); // x 是合并后的区间右端点
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
}

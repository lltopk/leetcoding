package org.lyflexi.solutions.strategy_retrieval_bfs.grid_chart.multi_source;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 994. 腐烂的橘子
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 *
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 *
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 示例 2：
 *
 * 输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个方向上。
 * 示例 3：
 *
 * 输入：grid = [[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] 仅为 0、1 或 2
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 497,598/893.8K
 * 通过率
 * 55.7%
 */
public class LC994_orangesRotting {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 四方向
    /**
     孤岛橘子永远不会腐烂

     2 1 1
     0 1 1
     1 0 1
     为了判断是否有永远不会腐烂的橘子，我们可以统计初始新鲜橘子的个数 fresh。在 BFS 中，每有一个新鲜橘子被腐烂，就把 fresh 减一，这样最后如果发现 fresh>0，就意味着有橘子永远不会腐烂，返回 −1。
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int fresh = 0;
        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    fresh++; // 统计新鲜橘子个数
                } else if (grid[i][j] == 2) {
                    q.add(new int[]{i, j}); // 一开始就腐烂的橘子, 作为多源点
                }
            }
        }

        int ans = 0;
        for (; fresh > 0 && !q.isEmpty(); ans++) {
            int size = q.size();
            while (size-- > 0) {
                int[] pos = q.pop();
                for (int[] d : DIRECTIONS) { // 四方向
                    int i = pos[0] + d[0];
                    int j = pos[1] + d[1];
                    if (0 <= i && i < m && 0 <= j && j < n && grid[i][j] == 1) { // 新鲜橘子
                        fresh--;
                        grid[i][j] = 2; // 变成腐烂橘子
                        q.add(new int[]{i, j});
                    }
                }
            }
        }

        return fresh > 0 ? -1 : ans;
    }
}

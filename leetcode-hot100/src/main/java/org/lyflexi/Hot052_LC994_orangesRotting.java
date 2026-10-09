package org.lyflexi;

import java.util.*;

/**
 * 994. 腐烂的橘子
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * 
 * - 值 0 代表空单元格；
 * 
 * - 值 1 代表新鲜橘子；
 * 
 * - 值 2 代表腐烂的橘子。
 * 
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * 
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 * 
 * 示例 1：
 * 
 * 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 
 * 示例 2：
 * 
 * 输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个方向上。
 * 
 * 示例 3：
 * 
 * 输入：grid = [[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 * 
 * 提示：
 * 
 * - m == grid.length
 * 
 * - n == grid[i].length
 * 
 * - 1 <= m, n <= 10
 * 
 * - grid[i][j] 仅为 0、1 或 2
 */

/**
 * 答疑
 * 
 * 问：如果代码不在 while 中判断 $fresh > 0$，会发生什么？
 * 
 * 答：会在腐烂完所有新鲜橘子后，多循环一次。这会导致 $ans$ 比实际多 $1$。
 */
public class Hot052_LC994_orangesRotting {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 四方向

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int fresh = 0;
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    fresh++; // 统计新鲜橘子个数
                } else if (grid[i][j] == 2) {
                    q.add(new int[]{i, j}); // 一开始就腐烂的橘子
                }
            }
        }

        int ans = 0;
        while (fresh > 0 && !q.isEmpty()) {
            ans++; // 经过一分钟
            List<int[]> tmp = q;
            q = new ArrayList<>();
            for (int[] pos : tmp) { // 已经腐烂的橘子
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

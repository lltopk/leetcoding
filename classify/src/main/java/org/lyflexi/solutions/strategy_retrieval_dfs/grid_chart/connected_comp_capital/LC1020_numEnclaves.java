package org.lyflexi.solutions.strategy_retrieval_dfs.grid_chart.connected_comp_capital;

/**
 * 1020. 飞地的数量
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
 *
 * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
 *
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * 输出：3
 * 解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
 * 示例 2：
 *
 *
 * 输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * 输出：0
 * 解释：所有 1 都在边界上或可以到达边界。
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 500
 * grid[i][j] 的值为 0 或 1
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 102,474/164.5K
 * 通过率
 * 62.3%
 */
public class LC1020_numEnclaves {
    //求内陆面积和
    private static final int[][] DIRS = {
            { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 }
    };
    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m < 3 || n < 3) return 0;

        //第一轮dfs只是把第一行， 最后一行 ，第一列， 最后一列给置为1， 但是不算答案
        for (int i = 0; i < m; i++) {
            // 如果是第一行和最后一行，访问所有格子
            // 如果不是，只访问第一列和最后一列的格子
            int step = i == 0 || i == m - 1 ? 1 : n - 1;
            for (int j = 0; j < n; j += step)
                dfs(grid, i, j);
        }

        int ans = 0;
        //第二轮dfs只算剩下的内圈的网格， 这个时候必定能够保证是封闭岛屿
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1) { // 从没有访问过的 1 出发
                    ans += dfs(grid, i, j);//一定是封闭岛屿
                }
            }
        }
        return ans;
    }

    private int dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || grid[x][y] != 1)
            return 0;
        int ret = grid[x][y];
        grid[x][y] = 0; // 标记 (x,y) 被访问，避免重复访问
        for(int[] dir: DIRS){
            ret += dfs(grid, x + dir[0], y + dir[1]);
        }
        return ret;
    }
}

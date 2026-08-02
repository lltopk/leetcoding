package org.lyflexi.solutions.strategy_retrieval_dfs.grid_chart.connected_comp_num;

/**
 * 130. 被围绕的区域
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' 组成，捕获 所有 被围绕的区域：
 *
 * 连接：一个单元格与水平或垂直方向上相邻的单元格连接。
 * 区域：连接所有 'O' 的单元格来形成一个区域。
 * 围绕：如果一个区域中的所有 'O' 单元格都不在棋盘的边缘，则该区域被包围。这样的区域 完全 被 'X' 单元格包围。
 * 通过 原地 将输入矩阵中的所有 'O' 替换为 'X' 来 捕获被围绕的区域。你不需要返回任何值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：board = [['X','X','X','X'],['X','O','O','X'],['X','X','O','X'],['X','O','X','X']]
 *
 * 输出：[['X','X','X','X'],['X','X','X','X'],['X','X','X','X'],['X','O','X','X']]
 *
 * 解释：
 *
 *
 * 在上图中，底部的区域没有被捕获，因为它在 board 的边缘并且不能被围绕。
 *
 * 示例 2：
 *
 * 输入：board = [['X']]
 *
 * 输出：[['X']]
 *
 *
 *
 * 提示：
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 372,709/781.9K
 * 通过率
 * 47.7%
 */
public class LC130_solve {
    //求内陆
    private static final int[][] DIRS = {
            { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 }
    };
    public void solve(char[][] grid) {
        int m = grid.length, n = grid[0].length;

        //第一轮dfs只是把第一行， 最后一行 ，第一列， 最后一列给置为#
        for (int i = 0; i < m; i++) {
            // 如果是第一行和最后一行，访问所有格子
            // 如果不是，只访问第一列和最后一列的格子
            int step = i == 0 || i == m - 1 ? 1 : n - 1;
            for (int j = 0; j < n; j += step)
                dfs(grid, i, j, 0);
        }

        int ans = 0;
        //第二轮dfs只算剩下的内圈的网格， 这个时候必定能够保证是封闭岛屿
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 'O') {
                    dfs(grid, i, j, 1);//一定是封闭岛屿
                }
            }
        }

        //第三轮dfs还原边界的#为X
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '#') {
                    grid[i][j] = 'O';
                }
            }
        }
    }

    /**
     type: 0 dfs边界 ;  1 dfs内陆
     */
    private void dfs(char[][] grid, int x, int y, int type) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || grid[x][y] != 'O')
            return;
        grid[x][y] = type == 0? '#': 'X'; // 标记 (x,y) 被访问，避免重复访问
        for(int[] dir: DIRS){
            dfs(grid, x + dir[0], y + dir[1], type);
        }
    }
}

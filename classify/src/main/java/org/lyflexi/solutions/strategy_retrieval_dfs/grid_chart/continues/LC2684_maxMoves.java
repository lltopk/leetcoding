package org.lyflexi.solutions.strategy_retrieval_dfs.grid_chart.continues;

/**
 * 2684. 矩阵中移动的最大次数
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始、大小为 m x n 的矩阵 grid ，矩阵由若干 正 整数组成。
 *
 * 你可以从矩阵第一列中的 任一 单元格出发，按以下方式遍历 grid ：
 *
 * 从单元格 (row, col) 可以移动到 (row - 1, col + 1)、(row, col + 1) 和 (row + 1, col + 1) 三个单元格中任一满足值 严格 大于当前单元格的单元格。
 * 返回你在矩阵中能够 移动 的 最大 次数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]
 * 输出：3
 * 解释：可以从单元格 (0, 0) 开始并且按下面的路径移动：
 * - (0, 0) -> (0, 1).
 * - (0, 1) -> (1, 2).
 * - (1, 2) -> (2, 3).
 * 可以证明这是能够移动的最大次数。
 * 示例 2：
 *
 *
 * 输入：grid = [[3,2,4],[2,1,9],[1,1,7]]
 * 输出：0
 * 解释：从第一列的任一单元格开始都无法移动。
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 1000
 * 4 <= m * n <= 105
 * 1 <= grid[i][j] <= 106
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 41,828/83.1K
 * 通过率
 * 50.3%
 */
public class LC2684_maxMoves {
    //这道题求深度
    int mxDepth = 0;
    private static final int[][] DIRS = {
            { -1, 1 }, { 0, 1 }, { 1, 1 }
    };
    public int maxMoves(int[][] grid) {
        int m = grid.length;
        //题目要求必须从第一列出发
        for (int i = 0; i < m; i++) {
            dfs(grid, i ,0);
        }
        return mxDepth;
    }

    /**
     j恰好就等价于维护了二叉树的高度
     */
    private void dfs(int[][] grid, int i , int j){
        if(i < 0 || i == grid.length || j< 0 || j==grid[0].length){
            return;
        }
        //重复访问
        if(grid[i][j] < 0){
            return;
        }
        for(int[] dir: DIRS){
            int i0 = i + dir[0];
            int j0 = j + dir[1];
            if(i0 < 0 || i0 == grid.length || j0 < 0 || j0==grid[0].length){
                continue;
            }
            if(grid[i0][j0] > grid[i][j]){
                mxDepth = Math.max(mxDepth, j+1);
                dfs(grid, i0, j0);
            }
        }
        grid[i][j] = -1;
    }
}

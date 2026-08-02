package org.lyflexi.solutions.strategy_retrieval_dfs.grid_chart.continues;

/**
 * 1034. 边界着色
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
 *
 * 如果两个方块在任意 4 个方向上相邻，则称它们 相邻 。
 *
 * 如果两个方块具有相同的颜色且相邻，它们则属于同一个 连通分量 。
 *
 * 连通分量的边界 是指连通分量中满足下述条件之一的所有网格块：
 *
 * 在上、下、左、右任意一个方向上与不属于同一连通分量的网格块相邻
 * 在网格的边界上（第一行/列或最后一行/列）
 * 请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色。
 *
 * 并返回最终的网格 grid 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
 * 输出：[[3,3],[3,2]]
 * 示例 2：
 *
 * 输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
 * 输出：[[1,3,3],[2,3,3]]
 * 示例 3：
 *
 * 输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
 * 输出：[[2,2,2],[2,1,2],[2,2,2]]
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j], color <= 1000
 * 0 <= row < m
 * 0 <= col < n
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 43,996/80K
 * 通过率
 * 55.0%
 */

/**
 * 补充: int[][] helper = new int[grid.length][grid[0].length]等价于boolean[][][] pair= new boolean[grid.length][grid[0].length][2];
 */
public class LC1034_colorBorder {
    /**
     只求当前(row, col)的连通块, 这只需要DFS一次

     boolean[][][] pair= new boolean[grid.length][grid[0].length][2];

     在DFS的过程中用辅助矩阵pair[i][j][0]标记边界格子设置为color, 同时pair[i][j][1]充当已访问标记设置为-1

     最后让原矩阵grid[i][j]匹配helper[i][j], 当helper[i][j]为color的时候, 更新grid[i][j]为color
     */
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // 左右上下
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        boolean[][][] pair = new boolean[grid.length][grid[0].length][2];
        dfs(grid, row, col, pair, grid[row][col], color);
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[0].length; j++){
                if(pair[i][j][0]) grid[i][j] = color;
            }
        }
        return grid;
    }

    private void dfs(int[][] grid, int i, int j, boolean[][][] pair, int origin, int color){
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }
        if(pair[i][j][0] || pair[i][j][1]) return;//重复访问
        pair[i][j][1] = true;
        int cnt = 0;
        for(int[] dir: DIRS){
            int i0 = i+dir[0];
            int j0 = j + dir[1];
            if(i0 < 0 || i0 == grid.length || j0 < 0 || j0==grid[0].length){
                continue;
            }
            if(grid[i0][j0]!= origin){
                continue;
            }
            cnt++;
            dfs(grid, i0, j0, pair, origin, color);
        }
        //如果超过四个, 则当前(i,j)不是边界, 反之则为边界
        if(cnt < 4){
            pair[i][j][0] = true;
        }
    }
}

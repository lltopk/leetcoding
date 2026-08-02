package org.lyflexi.solutions.strategy_retrieval_dfs.grid_chart;

/**
 * 1905. 统计子岛屿
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你两个 m x n 的二进制矩阵 grid1 和 grid2 ，它们只包含 0 （表示水域）和 1 （表示陆地）。一个 岛屿 是由 四个方向 （水平或者竖直）上相邻的 1 组成的区域。任何矩阵以外的区域都视为水域。
 *
 * 如果 grid2 的一个岛屿，被 grid1 的一个岛屿 完全 包含，也就是说 grid2 中该岛屿的每一个格子都被 grid1 中同一个岛屿完全包含，那么我们称 grid2 中的这个岛屿为 子岛屿 。
 *
 * 请你返回 grid2 中 子岛屿 的 数目 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
 * 输出：3
 * 解释：如上图所示，左边为 grid1 ，右边为 grid2 。
 * grid2 中标红的 1 区域是子岛屿，总共有 3 个子岛屿。
 * 示例 2：
 *
 *
 * 输入：grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
 * 输出：2
 * 解释：如上图所示，左边为 grid1 ，右边为 grid2 。
 * grid2 中标红的 1 区域是子岛屿，总共有 2 个子岛屿。
 *
 *
 * 提示：
 *
 * m == grid1.length == grid2.length
 * n == grid1[i].length == grid2[i].length
 * 1 <= m, n <= 500
 * grid1[i][j] 和 grid2[i][j] 都要么是 0 要么是 1 。
 */

/**
 * 这道题dfs自底向上判断是否覆盖, 类似于二叉树判断
 */
public class LC1905_countSubIslands {
    int ret = 0;
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // 左右上下
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length, n = grid1[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    if(dfs(grid1, grid2, i, j)) ret++;
                }
            }
        }
        return ret;
    }

    /**
        枚举的是grid2, 自底向上判断是否覆盖
     */
    private boolean dfs(int[][] grid1, int[][] grid2, int i , int j){
        int m = grid1.length, n = grid1[0].length;
        //边界返回true
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return true;
        }
        //海水或者重复访问返回true
        if(grid2[i][j] == 0){
            return true;
        }
        grid2[i][j] = 0;

        boolean contains = grid1[i][j] == 1? true: false;
        //这里的contains只代表局部的, 所以需要下面所有的子问题去共同计算
        for(int[] dir: DIRS){
            if(! dfs(grid1, grid2, i + dir[0], j + dir[1])){
                contains = false;
            }
        }
        //自底向上
        return contains;
    }
}

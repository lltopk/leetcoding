package org.lyflexi.solutions.strategy_retrieval_dfs.grid_chart.connected_comp_capital;

/**
 * 695. 岛屿的最大面积
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 *
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 *
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 输出：6
 * 解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
 * 示例 2：
 *
 * 输入：grid = [[0,0,0,0,0,0,0,0]]
 * 输出：0
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] 为 0 或 1
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 417,592/607.1K
 * 通过率
 * 68.8%
 */

/**
 * 定义数组表示四个方向
 */
public class LC695_maxAreaOfIsland2 {
    int ret = 0;
    /**
     1 1 1
     0 1 0

     注意求面积不能等于求DFS层数会得出最大面积为3， dfs(int[][] grid, int i , int j, int area)
     正确的做法是
     */
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};//定义四个方向
    public int maxAreaOfIsland(int[][] grid) {
        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                //遇到岛屿则开始dfs
                if(grid[i][j] == 1){
                    ret = Math.max(ret, dfs(grid, i, j));
                }
            }
        }
        return ret;
    }

    /**
     自底向上DFS求面积
     */
    private int dfs(int[][] grid, int i , int j){
        //越界 或 遇到水0 或 重复访问2
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || grid[i][j] == 2){
            return 0;
        }

        //染色， 标记为2吧
        grid[i][j] = 2;
        //左右上下四个方向
        int ret = 1;
        for(int[] dir: DIRS){
            ret += dfs(grid, i + dir[0], j + dir[1]);
        }
        return ret;
    }
}

package org.lyflexi.solutions.strategy_retrieval_dfs.grid_chart.connected_comp_num;

/**
 * 200. 岛屿数量
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *
 *
 * 示例 1：
 *
 * 输入：grid = [
 *   ['1','1','1','1','0'],
 *   ['1','1','0','1','0'],
 *   ['1','1','0','0','0'],
 *   ['0','0','0','0','0']
 * ]
 * 输出：1
 * 示例 2：
 *
 * 输入：grid = [
 *   ['1','1','0','0','0'],
 *   ['1','1','0','0','0'],
 *   ['0','0','1','0','0'],
 *   ['0','0','0','1','1']
 * ]
 * 输出：3
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,442,026/2.3M
 * 通过率
 * 64.1%
 */
public class LC200_numIslands {
    int ret = 0;
    private static final int[][] DIRS = {
            { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 }
    };
    public int numIslands(char[][] grid) {
        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                //遇到岛屿则开始dfs
                if(grid[i][j] == '1'){
                    dfs(grid, i, j);//全部感染当前连通块， 因此下面可以答案+1了
                    ret++;
                }
            }
        }
        return ret;
    }

    private void dfs(char[][] grid, int i , int j ){
        //越界 或 遇到水0 或 重复访问2
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0' || grid[i][j] == '2'){
            return;
        }

        //标记为2吧
        grid[i][j] = '2';
        //左右上下四个方向
        for(int[] dir: DIRS){
            dfs(grid, i+dir[0], j+dir[1]);
        }
    }
}

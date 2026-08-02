package org.lyflexi.solutions.strategy_retrieval_dfs.grid_chart.connected_comp_capital;

/**
 * 3619. 总价值可以被 K 整除的岛屿数目
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个 m x n 的矩阵 grid 和一个正整数 k。一个 岛屿 是由 正 整数（表示陆地）组成的，并且陆地间 四周 连通（水平或垂直）。
 *
 * 一个岛屿的总价值是该岛屿中所有单元格的值之和。
 *
 * 返回总价值可以被 k 整除 的岛屿数量。
 *
 *
 *
 * 示例 1:
 *
 *
 * 输入: grid = [[0,2,1,0,0],[0,5,0,0,5],[0,0,1,0,0],[0,1,4,7,0],[0,2,0,0,8]], k = 5
 *
 * 输出: 2
 *
 * 解释:
 *
 * 网格中包含四个岛屿。蓝色高亮显示的岛屿的总价值可以被 5 整除，而红色高亮显示的岛屿则不能。
 *
 * 示例 2:
 *
 *
 * 输入: grid = [[3,0,3,0], [0,3,0,3], [3,0,3,0]], k = 3
 *
 * 输出: 6
 *
 * 解释:
 *
 * 网格中包含六个岛屿，每个岛屿的总价值都可以被 3 整除。
 *
 *
 *
 * 提示:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 1000
 * 1 <= m * n <= 105
 * 0 <= grid[i][j] <= 106
 * 1 <= k < = 106
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 7,257/12.7K
 * 通过率
 * 57.3%
 */
public class LC3619_countIslands2 {
    int ret = 0;
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};//定义四个方向
    public int countIslands(int[][] grid, int k) {
        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] > 0 && dfs(grid, i, j) % k == 0){
                    ret++;
                }
            }
        }
        return ret;
    }

    /**
     自底向上DFS求面积
     */
    private long dfs(int[][] grid, int i , int j){
        //越界 或 遇到水0 或 重复访问
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0){
            return 0;
        }
        //先获取贡献
        long ret = grid[i][j];
        //标记
        grid[i][j] = 0;
        //左右上下四个方向
        for(int[] dir: DIRS){
            ret += dfs(grid, i + dir[0], j + dir[1]);
        }
        return ret;
    }
}

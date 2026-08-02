package org.lyflexi.solutions.strategy_retrieval_dfs.grid_chart.connected_comp_num;

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
    private static final int[][] DIRS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };
    private boolean ok;
    int ans = 0;
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int n = grid2.length;
        int m = grid2[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                //判断grid2即可
                if(grid2[i][j] > 0){
                    // 重置
                    ok = true;
                    dfs(grid1, grid2, i, j);
                    if(ok){
                        ans++;
                    }
                }
            }
        }

        return ans;
    }

    /**
     注意到只要 grid2 的岛屿在 grid1 对应位置上不存在水域，那其就是子岛屿

     因此这个dfs递归的是grid2, 至于grid1只是用来参照的
     */
    private void dfs(int[][] grid1, int[][] grid2, int x, int y){
        if(x < 0 && x>= grid2.length && y < 0 && y >= grid2[0].length){
            return;
        }
        // 标记访问
        grid2[x][y] = 0;

        // 对应在grid1中如果是水域, 则为false
        if(grid1[x][y] == 0) ok = false;

        for(int[] d: DIRS){
            int nx = x + d[0];
            int ny = y + d[1];
            if(nx >= 0 && nx < grid2.length && ny >= 0 && ny < grid2[0].length && grid2[nx][ny] > 0){
                dfs(grid1, grid2, nx, ny);
            }
        }
    }
}

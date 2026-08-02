package org.lyflexi.solutions.strategy_retrieval_dfs.grid_chart.connected_comp_capital;

/**
 * 2658. 网格图中鱼的最大数目
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始大小为 m x n 的二维整数数组 grid ，其中下标在 (r, c) 处的整数表示：
 *
 * 如果 grid[r][c] = 0 ，那么它是一块 陆地 。
 * 如果 grid[r][c] > 0 ，那么它是一块 水域 ，且包含 grid[r][c] 条鱼。
 * 一位渔夫可以从任意 水域 格子 (r, c) 出发，然后执行以下操作任意次：
 *
 * 捕捞格子 (r, c) 处所有的鱼，或者
 * 移动到相邻的 水域 格子。
 * 请你返回渔夫最优策略下， 最多 可以捕捞多少条鱼。如果没有水域格子，请你返回 0 。
 *
 * 格子 (r, c) 相邻 的格子为 (r, c + 1) ，(r, c - 1) ，(r + 1, c) 和 (r - 1, c) ，前提是相邻格子在网格图内。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
 * 输出：7
 * 解释：渔夫可以从格子 (1,3) 出发，捕捞 3 条鱼，然后移动到格子 (2,3) ，捕捞 4 条鱼。
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
 * 输出：1
 * 解释：渔夫可以从格子 (0,0) 或者 (3,3) ，捕捞 1 条鱼。
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * 0 <= grid[i][j] <= 10
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 14,780/20.6K
 * 通过率
 * 71.7%
 */
public class LC2658_findMaxFish {
    int ret = 0;
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};//定义四个方向
    public int findMaxFish(int[][] grid) {
        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                //遇到岛屿则开始dfs
                if(grid[i][j] != 0 ){
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
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0){
            return 0;
        }

        //先拿价值， 后标记
        int ret = grid[i][j];
        //标记为0吧
        grid[i][j] = 0;
        //左右上下四个方向
        for(int[] dir: DIRS){
            ret += dfs(grid, i + dir[0], j + dir[1]);
        }
        return ret;
    }
}

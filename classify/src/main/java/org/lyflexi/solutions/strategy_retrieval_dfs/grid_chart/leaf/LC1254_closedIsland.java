package org.lyflexi.solutions.strategy_retrieval_dfs.grid_chart.leaf;

/**
 * 1254. 统计封闭岛屿的数目
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 二维矩阵 grid 由 0 （土地）和 1 （水）组成。岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
 *
 * 请返回 封闭岛屿 的数目。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * 输出：2
 * 解释：
 * 灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * 输出：1
 * 示例 3：
 *
 * 输入：grid = [[1,1,1,1,1,1,1],
 *              [1,0,0,0,0,0,1],
 *              [1,0,1,1,1,0,1],
 *              [1,0,1,0,1,0,1],
 *              [1,0,1,1,1,0,1],
 *              [1,0,0,0,0,0,1],
 *              [1,1,1,1,1,1,1]]
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= grid.length, grid[0].length <= 100
 * 0 <= grid[i][j] <=1
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 84,620/130.6K
 * 通过率
 * 64.8%
 */
public class LC1254_closedIsland {
    int ret = 0;
    boolean closed = true;
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};//定义四个方向
    public int closedIsland(int[][] grid) {
        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                //遇到岛屿则开始dfs
                if(grid[i][j] == 0){
                    closed = true;
                    dfs(grid, i, j);//全部感染当前连通块， 因此下面可以答案+1了
                    if(closed){
                        ret++;
                    }
                }
            }
        }
        return ret;
    }

    private void dfs(int[][] grid, int i , int j ){
        //这里相当于判断叶子， 而不是判断空
        if(i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length - 1){
            //需要对边界进行特判（边界一定不会是有效解）
            if(grid[i][j] == 0) {
                closed = false;
            }
            return ;
        }

        //除此之外， 一定是封闭的， 保持closed是true即可
        if(grid[i][j] != 0) return;

        //标记为1吧
        grid[i][j] = 1;
        //左右上下四个方向
        for(int[] dir: DIRS){
            dfs(grid, i + dir[0], j + dir[1]);
        }
    }
}

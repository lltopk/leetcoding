package org.lyflexi.solutions.strategy_retrieval_bfs.grid_chart.single_source;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 934. 最短的桥
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个大小为 n x n 的二元矩阵 grid ，其中 1 表示陆地，0 表示水域。
 *
 * 岛 是由四面相连的 1 形成的一个最大组，即不会与非组内的任何其他 1 相连。grid 中 恰好存在两座岛 。
 *
 * 你可以将任意数量的 0 变为 1 ，以使两座岛连接起来，变成 一座岛 。
 *
 * 返回必须翻转的 0 的最小数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：grid = [[0,1],[1,0]]
 * 输出：1
 * 示例 2：
 *
 * 输入：grid = [[0,1,0],[0,0,0],[0,0,1]]
 * 输出：2
 * 示例 3：
 *
 * 输入：grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * 输出：1
 *
 *
 * 提示：
 *
 * n == grid.length == grid[i].length
 * 2 <= n <= 100
 * grid[i][j] 为 0 或 1
 * grid 中恰有两个岛
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 86,237/160.6K
 * 通过率
 * 53.7%
 */
public class LC934_shortestBridge {
    int[][] dirs = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    /**
     先通过 DFS 将其中一个岛屿的所有点找出来，放到一个队列 q 中。然后通过 BFS 一层层向外扩展，直至碰到另一个岛屿，此时将当前扩展的层数作为答案返回即可。
     */
    Deque<int[]> q = new ArrayDeque<>();
    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        //x相当于个标记
        for (int i = 0, x = 0; i < n && x == 0; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                    x = 1;//DFS一次, 找出第一片岛屿就够了, 作为单源
                    break;
                }
            }
        }
        int ans = 0;
        for (; ! q.isEmpty(); ans++) {
            int size = q.size();
            while(size-- > 0){
                int[] pos = q.pollFirst();
                for (int[] dir : dirs) {
                    int x = pos[0] + dir[0];
                    int y = pos[1] + dir[1];
                    if (x >= 0 && x < n && y >= 0 && y < n) {
                        if (grid[x][y] == 1) {
                            return ans;
                        }
                        if (grid[x][y] == 0) {
                            grid[x][y] = 2;
                            q.offer(new int[] {x, y});
                        }
                    }
                }
            }
        }
        return ans;
    }

    private void dfs(int[][] grid, int i, int j) {
        grid[i][j] = 2;
        q.offer(new int[] {i, j});
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid.length && grid[x][y] == 1) {
                dfs(grid, x, y);
            }
        }
    }
}

package org.lyflexi.solutions.strategy_retrieval_bfs.grid_chart.single_source;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1091. 二进制矩阵中的最短路径
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
 *
 * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
 *
 * 路径途经的所有单元格的值都是 0 。
 * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
 * 畅通路径的长度 是该路径途经的单元格总数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[0,1],[1,0]]
 * 输出：2
 * 示例 2：
 *
 *
 * 输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
 * 输出：4
 * 示例 3：
 *
 * 输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
 * 输出：-1
 *
 *
 * 提示：
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] 为 0 或 1
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 102,468/243.3K
 * 通过率
 * 42.1%
 */
public class LC1091_shortestPathBinaryMatrix {
    private static final int[][] DIRS = {
            {0, -1}, {0, 1}, {-1, 0}, {1, 0},
            {-1, -1}, {1, 1}, {1, -1}, {-1, 1}
    };//定义八个方向
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }
        if(grid[0].length == 1) return 1;
        int n = grid.length;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});

        grid[0][0] = 1;//重复性标记
        //没有距离矩阵,  需要分层计算, 每层只计算1次
        for (int ans = 1; !q.isEmpty(); ++ans) {
            for (int k = q.size(); k > 0; --k) {
                var p = q.poll();
                int i = p[0], j = p[1];
                for(int[] dir: DIRS){
                    int x = p[0] + dir[0];
                    int y = p[1] + dir[1];
                    if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                        //最先到达右下角的涟漪, 一定是最短的
                        if (x == n - 1 && y == n - 1) {
                            return ans + 1;
                        }
                        grid[x][y] = 1;//重复性标记
                        q.offer(new int[]{x, y});
                    }
                }
            }
        }
        return -1;
    }
}

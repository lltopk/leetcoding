package org.lyflexi.solutions.strategy_retrieval_bfs.grid_chart.multi_source;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1162. 地图分析
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 你现在手里有一份大小为 n x n 的 网格 grid，上面的每个 单元格 都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地。
 *
 * 请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的，并返回该距离。如果网格上只有陆地或者海洋，请返回 -1。
 *
 * 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - x1| + |y0 - y1| 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[1,0,1],[0,0,0],[1,0,1]]
 * 输出：2
 * 解释：
 * 海洋单元格 (1, 1) 和所有陆地单元格之间的距离都达到最大，最大距离为 2。
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[1,0,0],[0,0,0],[0,0,0]]
 * 输出：4
 * 解释：
 * 海洋单元格 (2, 2) 和所有陆地单元格之间的距离都达到最大，最大距离为 4。
 *
 *
 * 提示：
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] 不是 0 就是 1
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 72,703/152K
 * 通过率
 * 47.8%
 */
public class LC1162_maxDistance {
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        Queue<int[]> queue = new ArrayDeque<>();

        // 将所有陆地格子加入队列
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // 如果没有陆地或没有海洋，返回-1
        if (queue.isEmpty() || queue.size() == n * n) {
            return -1;
        }

        int[][] dirs = {{1,0}, {0,1}, {-1,0}, {0,-1}};

        int distance = 0;
        for ( ; !queue.isEmpty(); distance++) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] pos = queue.poll();

                for (int[] dir : dirs) {
                    int x = pos[0] + dir[0];
                    int y = pos[1] + dir[1];

                    if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                        grid[x][y] = 1; // 标记海洋为已访问
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }

        return distance - 1;
    }
}

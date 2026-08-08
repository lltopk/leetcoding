package org.lyflexi.solutions.strategy_retrieval_bfs.grid_chart.multi_source;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 542. 01 矩阵
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 *
 * 两个相邻元素间的距离为 1 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：[[0,0,0],[0,1,0],[0,0,0]]
 * 示例 2：
 *
 *
 *
 * 输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
 * 输出：[[0,0,0],[0,1,0],[1,2,1]]
 *
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * mat[i][j] is either 0 or 1.
 * mat 中至少有一个 0
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 163,979/341.1K
 * 通过率
 * 48.1%
 */
public class LC542_updateMatrix {
    int[][] dirs = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] ans = new int[m][n];

        Queue<int[]> q = new ArrayDeque<>();
        // 把所有的 0 加入队列
        for (int i = 0;i < m;i++) {
            for (int j = 0;j < n;j++) {
                if (mat[i][j] == 0) {
                    q.add(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] pos = q.poll();
                for (int[] dir : dirs) {
                    int x = pos[0] + dir[0];
                    int y = pos[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && mat[x][y] == 1) {
                        mat[x][y] = 0; // 标记为已访问
                        ans[x][y] = ans[pos[0]][pos[1]] + 1;
                        q.add(new int[]{x, y});
                    }
                }
            }
        }
        return ans;
    }
}

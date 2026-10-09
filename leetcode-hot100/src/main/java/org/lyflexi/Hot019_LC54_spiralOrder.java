package org.lyflexi;

import java.util.*;

/**
 * 54. 螺旋矩阵
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 
 * 示例 1：
 * 
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 
 * 示例 2：
 * 
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 * 提示：
 * 
 * - m == matrix.length
 * 
 * - n == matrix[i].length
 * 
 * - 1 <= m, n <= 10
 * 
 * - -100 <= matrix[i][j] <= 100
 */

/**
 * 方法一：标记
 * 
 * 1. 对于已经访问过的数字，可将其标记为 $\infty$ 或者空，从而避免重复访问。
 * 2. 用一个长为 $4$ 的方向数组 $DIRS = [(0, 1), (1, 0), (0, -1), (-1, 0)]$ 分别表示右下左上 $4$ 个方向。同时用一个下标 $di$ 表示当前方向，初始值为 $0$，表示一开始向右。
 * 3. 每次移动，相当于把行号增加 $DIRS[di][0]$，把列号增加 $DIRS[di][1]$。
 * 4. 向右转 $90^\circ$，相当于把 $di$ 增加 $1$，但在 $di=3$ 时要回到 $di=0$。两种情况合二为一，把 $di$ 更新为 $(di+1)\bmod 4$。
 */
public class Hot019_LC54_spiralOrder {
    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 右下左上

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> ans = new ArrayList<>(m * n); // 预分配空间
        int i = 0;
        int j = 0;
        int di = 0;
        for (int k = 0; k < m * n; k++) { // 一共走 mn 步
            ans.add(matrix[i][j]);
            matrix[i][j] = Integer.MAX_VALUE; // 标记，表示已经访问过（已经加入答案）
            int x = i + DIRS[di][0];
            int y = j + DIRS[di][1]; // 下一步的位置
            // 如果 (x, y) 出界或者已经访问过
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] == Integer.MAX_VALUE) {
                di = (di + 1) % 4; // 右转 90°
            }
            i += DIRS[di][0];
            j += DIRS[di][1]; // 走一步
        }
        return ans;
    }
}

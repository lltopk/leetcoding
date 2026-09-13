package org.lyflexi.solutions.baseAlgorithm.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,082,197/1.9M
 * 通过率
 * 55.7%
 */
public class LC54_spiralOrder {
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

            // 下一步的位置, 如果下一步 (x, y) 出界或者已经访问过, 则右转90°
            int x = i + DIRS[di][0];
            int y = j + DIRS[di][1];
            //
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] == Integer.MAX_VALUE) {
                di = (di + 1) % 4; // 右转 90°
            }

            // 实际执行走一步
            i += DIRS[di][0];
            j += DIRS[di][1];
        }
        return ans;
    }
}

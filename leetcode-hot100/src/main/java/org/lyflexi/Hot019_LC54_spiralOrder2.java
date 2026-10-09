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
 * 答疑
 * 
 * 问：如何说明这个规律的正确性？
 * 
 * 答：在上文的例子中，我们把示例 2 分成了 $5$ 组。一般地，每 $4$ 组，我们会把矩阵最外面一圈去掉（就像剥洋葱），矩阵的行数会减少 $2$，列数会减少 $2$。所以行列的减少是有规律的。
 */
public class Hot019_LC54_spiralOrder2 {
    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 右下左上

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int size = m * n;
        List<Integer> ans = new ArrayList<>(m * n); // 预分配空间
        int i = 0;
        int j = -1; // 从 (0, -1) 开始
        for (int di = 0; ans.size() < size; di = (di + 1) % 4) {
            for (int k = 0; k < n; k++) { // 走 n 步（注意 n 会减少）
                i += DIRS[di][0];
                j += DIRS[di][1]; // 先走一步
                ans.add(matrix[i][j]); // 再加入答案
            }
            int tmp = n;
            n = m - 1; // 减少后面的循环次数（步数）
            m = tmp;
        }
        return ans;
    }
}

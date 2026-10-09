package org.lyflexi;

import java.util.*;

/**
 * 73. 矩阵置零
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 * 
 * 示例 1：
 * 
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 * 
 * 示例 2：
 * 
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * 
 * 提示：
 * 
 * - m == matrix.length
 * 
 * - n == matrix[0].length
 * 
 * - 1 <= m, n <= 200
 * 
 * - -2^31 <= matrix[i][j] <= 2^31 - 1
 * 
 * 进阶：
 * 
 * - 一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 
 * - 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 
 * - 你能想出一个仅使用常量空间的解决方案吗？
 */

/**
 * 写法二
 * 
 * 记录行列是否包含 $0$ 时，上面的代码没有遍历第一列。如果顺带遍历第一列呢？
 * 
 * - 如果第一列全为 $1$，那么在遍历过程中，不会修改 $matrix[0][0]$，所以遍历结束后 $matrix[0][0]$ 仍然是 $1$。
 * - 如果第一列包含 $0$，那么在遍历过程中，我们会把 $matrix[0][0]$ 置为 $0$。
 * 
 * 所以可以直接用 $matrix[0][0]$ 表示「第一列是否在一开始包含 $0$」。
 */
public class Hot018_LC73_setZeroes3 {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean firstRowHasZero = false; 
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowHasZero = true;
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) { // 如果第一列包含 0，那么 matrix[0][0] 会置为 0
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 注意顺序，先改第一列，再改第一行（避免把 matrix[0][0] 从 1 改成 0 影响判断）
        if (matrix[0][0] == 0) { // 替换原来的 firstColHasZero
            for (int[] row : matrix) {
                row[0] = 0;
            }
        }

        if (firstRowHasZero) {
            Arrays.fill(matrix[0], 0);
        }
    }
}

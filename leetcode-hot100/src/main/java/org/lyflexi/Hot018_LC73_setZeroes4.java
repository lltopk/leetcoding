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
 * 写法三
 * 
 * 把 $matrix[i][j]$ 置 $0$ 的循环，可以倒着遍历 $i$ 行，这样可以直接修改第一列。
 * 
 * 对比一下，如果正着遍历 $i$ 行，如果提前把 $matrix[i][0]$ 改成 $0$，会误认为这一行要全部变成 $0$。
 * 
 * 下面的代码，前两个循环可以合在一起看，我们遍历了一次 $matrix$；后两个循环可以合在一起看，我们又遍历了一次 $matrix$。
 */
public class Hot018_LC73_setZeroes4 {
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
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; i++) {
            // 倒着遍历，避免提前把 matrix[i][0] 改成 0，误认为这一行要全部变成 0
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstRowHasZero) {
            Arrays.fill(matrix[0], 0);
        }
    }
}

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
 * 答疑
 * 
 * 问：能不能在发现 $matrix[i][j] = 0$ 时，直接把 $i$ 行 $j$ 列全变成 $0$？
 * 
 * 答：这样做是错误的。如示例 1，如果直接全变成 $0$，那么继续遍历，发现 $matrix[1][2] = 0$，我们会误以为 $matrix[1][2]$ 一开始就是 $0$，然后把 $2$ 列（最右边那一列）全变成 $0$。
 */
public class Hot018_LC73_setZeroes {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] rowHasZero = new boolean[m]; // 行是否包含 0
        boolean[] colHasZero = new boolean[n]; // 列是否包含 0

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rowHasZero[i] = colHasZero[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rowHasZero[i] || colHasZero[j]) { // i 行或 j 列有 0
                    matrix[i][j] = 0; // 题目要求原地修改，无返回值
                }
            }
        }
    }
}

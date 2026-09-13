package org.lyflexi.solutions.baseAlgorithm.matrix;

/**
 * 73. 矩阵置零
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 * 示例 2：
 *
 *
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -231 <= matrix[i][j] <= 231 - 1
 *
 *
 * 进阶：
 *
 * 一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个仅使用常量空间的解决方案吗？
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 873,795/1.2M
 * 通过率
 * 71.2%
 */
public class LC73_setZeroes {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] rowHasZero = new boolean[m]; // 行是否包含 0
        boolean[] colHasZero = new boolean[n]; // 列是否包含 0

        //先用两个boolean数组标记
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rowHasZero[i] = colHasZero[j] = true;
                }
            }
        }

        //对标记代表的行/列进行置零
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rowHasZero[i] || colHasZero[j]) { // i 行或 j 列有 0
                    matrix[i][j] = 0; // 题目要求原地修改，无返回值
                }
            }
        }
    }
}

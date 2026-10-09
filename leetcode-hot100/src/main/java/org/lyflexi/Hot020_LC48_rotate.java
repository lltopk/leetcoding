package org.lyflexi;

import java.util.*;

/**
 * 48. 旋转图像
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * 
 * 示例 1：
 * 
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * 
 * 示例 2：
 * 
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * 
 * 提示：
 * 
 * - n == matrix.length == matrix[i].length
 * 
 * - 1 <= n <= 20
 * 
 * - -1000 <= matrix[i][j] <= 1000
 */

/**
 * 写法一
 */
public class Hot020_LC48_rotate {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 第一步：转置
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) { // 遍历对角线下方元素
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        // 第二步：行翻转
        for (int[] row : matrix) {
            for (int j = 0; j < n / 2; j++) { // 遍历左半元素
                int tmp = row[j];
                row[j] = row[n - 1 - j];
                row[n - 1 - j] = tmp;
            }
        }
    }
}

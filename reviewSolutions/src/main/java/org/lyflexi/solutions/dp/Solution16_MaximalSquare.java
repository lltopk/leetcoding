package org.lyflexi.solutions.dp;

/**
 * @Author: ly
 * @Date: 2024/3/24 11:28
 */

/*
* 221. 最大正方形
在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
*
*
*例如：
输入：matrix = [["0","1"],["1","0"]]
输出：1
*
* */
public class Solution16_MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        //dp[i][j]表示以matrix[i][j]的右下角为只包含1的正方形的右下角，该情况下边长的最大值。
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {//边界条件
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }//默认dp[i][j] = 0;
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }
}

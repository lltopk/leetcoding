package org.lyflexi.solutions.dp;

/**
 * @Author: ly
 * @Date: 2024/3/24 11:30
 */

/*
* 1277. 统计全为 1 的正方形子矩阵
给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。


示例 1：
输入：matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
输出：15
解释：
边长为 1 的正方形有 10 个。
边长为 2 的正方形有 4 个。
边长为 3 的正方形有 1 个。
正方形的总数 = 10 + 4 + 1 = 15.
* */


public class Solution17_CountSquares {
    //只返回总和即可，不用分组返回
    public int countSquares(int[][] matrix) {
        int answer = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return answer;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        //dp[i][j]表示以matrix[i][j]为右下角，只包含1的正方形的边长最大值。
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {//边界条件
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    //dp[i][j]同时也表示以matrix[i][j]为右下角，正方形的数目为 x
                    answer += dp[i][j];
                }//默认dp[i][j] = 0;
            }
        }
        return answer;
    }
}

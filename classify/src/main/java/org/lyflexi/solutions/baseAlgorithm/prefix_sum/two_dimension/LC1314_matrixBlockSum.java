package org.lyflexi.solutions.baseAlgorithm.prefix_sum.two_dimension;

/**
 * 1314. 矩阵区域和
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个 m x n 的矩阵 mat 和一个整数 k ，请你返回一个矩阵 answer ，其中每个 answer[i][j] 是所有满足下述条件的元素 mat[r][c] 的和：
 *
 * i - k <= r <= i + k,
 * j - k <= c <= j + k 且
 * (r, c) 在矩阵内。
 *
 *
 * 示例 1：
 *
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * 输出：[[12,21,16],[27,45,33],[24,39,28]]
 * 示例 2：
 *
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], k = 2
 * 输出：[[45,45,45],[45,45,45],[45,45,45]]
 *
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n, k <= 100
 * 1 <= mat[i][j] <= 100
 */

/**
 * 二维前缀和
 */
public class LC1314_matrixBlockSum {
    //答案中的每个值, 正好就是两个前缀和相减s2 - s1后的结果
    //其中s2代表的点(i+k, j+k)
    //其中s1代表的点(i-k, j+k)
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[][] preS = new int[m+1][n+1];
        int[][] ans = new int[m][n];
        for(int i = 1; i<m+1; i++){
            for(int j = 1; j<n+1; j++){
                preS[i][j] = preS[i][j-1] + preS[i-1][j] - preS[i-1][j-1] + mat[i-1][j-1];
            }
        }

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                int i2 = Math.min(m-1, i+k);
                int j2 = Math.min(n-1, j+k);
                int i1 = Math.max(0, i-k);
                int j1 = Math.max(0, j-k);
                ans[i][j] = preS[i2+1][j2+1] - preS[i2+1][j1] - preS[i1][j2+1] + preS[i1][j1];
            }
        }

        return ans;

    }
}

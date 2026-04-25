package org.lyflexi.solutions.baseAlgorithm.prefix_sum.two_dimension;

/**
 * 363. 矩形区域不超过 K 的最大数值和
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
 *
 * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,0,1],[0,-2,3]], k = 2
 * 输出：2
 * 解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * 示例 2：
 *
 * 输入：matrix = [[2,2,-1]], k = 3
 * 输出：3
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 * -105 <= k <= 105
 *
 *
 * 进阶：如果行数远大于列数，该如何设计解决方案？
 */

/**
 * 二维前缀和
 */
public class LC363_maxSumSubmatrix {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] preS = new int[m+1][n+1];
        for(int i =1; i<m+1; i++){
            for(int j = 1; j< n+1; j++){
                preS[i][j] = preS[i][j-1] + preS[i-1][j] - preS[i-1][j-1] + matrix[i-1][j-1];
            }
        }

        int ans = Integer.MIN_VALUE;
        //由右下角顶点和左上角顶点即可确定一个矩阵,
        //因此枚举所有的右下角(两重循环m*n), 和左上角(两重循环m*n)
        //由于右下角一定在左上角的右下方, 因此整体复杂度会小于m^2*n^2
        for(int i = 0; i< m; i++){
            for(int j = 0; j<n; j++){
                for(int p = i; p<m; p++){
                    for(int q = j; q<n; q++){
                        int sub = preS[p+1][q+1] - preS[p+1][j] - preS[i][q+1] + preS[i][j];
                        if(sub <= k){
                            ans = Math.max(ans, sub);
                        }

                    }
                }
            }
        }

        return ans;
    }
}

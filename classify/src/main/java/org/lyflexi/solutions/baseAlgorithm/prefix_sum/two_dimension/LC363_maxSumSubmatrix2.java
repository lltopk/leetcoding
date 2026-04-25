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

import java.util.TreeSet;

/**
 * 二维前缀和
 *
 * 遍历上下边, 同时枚举右边, 也可确定子矩形
 *
 * 这可以降低复杂度, 从m^2*n^2 -> m^2 * n * logn
 */
public class LC363_maxSumSubmatrix2 {
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

        //遍历上下边, 同时枚举右边, 也可确定子矩形
        for(int top = 0; top< m; top++){
            for(int bot = top; bot< m; bot++){
                //由于不需要计数, 只需要求最大矩形: s2 - s1, 因此用有序集合就够了(底层也是红黑树TreeMap)
                TreeSet<Integer> treeSet = new TreeSet<>();
                treeSet.add(0);
                //两数之和, 哈希降维
                for(int r = 0; r<n; r++){
                    int s2 = preS[bot+1][r+1] - preS[top][r+1];
                    Integer s1 = treeSet.ceiling(s2 - k);
                    if(s1!=null){
                        ans = Math.max(ans, s2 - s1);
                    }
                    treeSet.add(s2);
                }
            }
        }

        return ans;
    }
}

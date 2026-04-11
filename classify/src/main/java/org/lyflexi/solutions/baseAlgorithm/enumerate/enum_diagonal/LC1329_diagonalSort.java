package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_diagonal;

/**
 * 1329. 将矩阵按对角线排序
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 矩阵对角线 是一条从矩阵最上面行或者最左侧列中的某个元素开始的对角线，沿右下方向一直到矩阵末尾的元素。例如，矩阵 mat 有 6 行 3 列，从 mat[2][0] 开始的 矩阵对角线 将会经过 mat[2][0]、mat[3][1] 和 mat[4][2] 。
 *
 * 给你一个 m * n 的整数矩阵 mat ，请你将同一条 矩阵对角线 上的元素按升序排序后，返回排好序的矩阵。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * 输出：[[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 * 示例 2：
 *
 * 输入：mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
 * 输出：[[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
 *
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * 1 <= mat[i][j] <= 100
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 枚举技巧, 枚举中间
 */
public class LC1329_diagonalSort {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        //反向对角线的规律是任意格子i-j都是个定值, 比如最中间的对角线i-j = 0;
        //设k = i - j + n;
        //则最上面的对角线k = 1
        //最下面的对角线k = m - 1 + n (本题m == n)
        //k取值范围[1, m-1+n], 这恰好是对角线个数
        for(int c = 0; c<m-1+n; c++){
            int k = c+1;
            //知道j就知道i = k+j-n, 所以接下来求j
            int maxJ = Math.min(n-1, m-1+n-k);//最大化i, 则最大化j
            int minJ = Math.max(0, n-k);//最小化i, 则最小化j

            List<Integer> diagonal = new ArrayList<>();
            for(int j = minJ; j<=maxJ; j++){
                int i = k + j -n;
                diagonal.add(mat[i][j]);
            }

            //diagonal.size() == maxJ - minJ + 1
            diagonal.sort(null);

            for(int j = minJ; j<=maxJ; j++){
                int i = k + j -n;
                mat[i][j] = diagonal.get(j-minJ);
            }
        }

        return mat;
    }
}

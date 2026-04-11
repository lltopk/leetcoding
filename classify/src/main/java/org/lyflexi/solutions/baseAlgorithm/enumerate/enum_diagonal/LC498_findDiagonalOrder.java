package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_diagonal;

/**
 * 498. 对角线遍历
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,4,7,5,3,6,8,9]
 * 示例 2：
 *
 * 输入：mat = [[1,2],[3,4]]
 * 输出：[1,2,3,4]
 *
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * -105 <= mat[i][j] <= 105
 */

/**
 * 枚举技巧, 枚举对角线
 */
public class LC498_findDiagonalOrder {
    //枚举技巧, 遍历对角线
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] ans = new int[m*n];
        int idx = 0;
        //正向对角线的规律是任意格子i+j都是个定值, 比如最中间的对角线i+j = 2;
        //设k = i + j;
        //则最上面的对角线k = 0
        //最下面的对角线k = m + n - 2 (本题m == n)
        //k取值范围[0, m+n-2], 这恰好是对角线个数
        for(int k = 0; k< m+n-1; k++){
            //知道j就知道i = k-j, 所以接下来求j
            int maxJ = Math.min(n-1, k);//最小化i, 则最大化j
            int minJ = Math.max(0, k-m+1);//最大化i, 则最小化j

            if(k%2 ==0){//正向遍历
                for(int j = minJ; j<=maxJ; j++){
                    int i = k - j;
                    ans[idx++] = mat[i][j];
                }
            }else{//反向遍历
                for(int j = maxJ; j>=minJ; j--){
                    int i = k - j;
                    ans[idx++] = mat[i][j];
                }
            }
        }
        return ans;
    }
}

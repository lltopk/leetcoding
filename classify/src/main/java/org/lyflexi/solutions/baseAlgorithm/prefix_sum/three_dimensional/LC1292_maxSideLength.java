package org.lyflexi.solutions.baseAlgorithm.prefix_sum.three_dimensional;

/**
 * 1292. 元素和小于等于阈值的正方形的最大边长
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个大小为 m x n 的矩阵 mat 和一个整数阈值 threshold。
 *
 * 请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0 。
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * 输出：2
 * 解释：总和小于或等于 4 的正方形的最大边长为 2，如图所示。
 * 示例 2：
 *
 * 输入：mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
 * 输出：0
 *
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 300
 * 0 <= mat[i][j] <= 104
 * 0 <= threshold <= 105
 */

/**
 * 二维前缀和
 */
public class LC1292_maxSideLength {
    /**
     * 首先用两重循环计算出二维数组所有的前缀和
     *
     * 然后用三重循环寻找正方形, 其中前两重循环用于确定正方形右下角顶点(i,j), 第三重循环暴力枚举正方形左上角顶点(i-1,j-1) (i-2,j-2)...直到越界或者子数组和超过threshold
     *
     * 作者：lltopk
     * 链接：https://leetcode.cn/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/solutions/3966348/er-wei-shu-zu-qian-zhui-he-bao-li-xun-zh-u4hu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param mat
     * @param threshold
     * @return
     */
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        int[][] preS = new int[m+1][n+1];
        int ans = 0;//最大边长
        for(int i = 1; i<m+1; i++){
            for(int j = 1; j<n+1; j++){
                preS[i][j] = preS[i][j-1] + preS[i-1][j] - preS[i-1][j-1] + mat[i-1][j-1];
            }
        }

        //怎么枚举正方形呢?
        //首先, 外面两层循环枚举正方形的右下角(i,j)
        //然后, 第三层循环计算正方形的左上角即(i-1,j-1) (i-2,j-2)直到越界或者子数组和超过threshold
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                int k = 1;
                while(true){
                    int ii = i-k+1;
                    int jj = j-k+1;
                    if(ii<0 || jj<0){
                        break;
                    }
                    int subS = preS[i+1][j+1] - preS[i+1][jj] - preS[ii][j+1] + preS[ii][jj];
                    if(subS > threshold){
                        break;
                    }
                    ans = Math.max(ans, k);
                    k++;
                }
            }
        }
        return ans;
    }
}

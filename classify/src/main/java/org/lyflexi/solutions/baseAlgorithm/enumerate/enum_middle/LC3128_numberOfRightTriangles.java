package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_middle;

/**
 * 3128. 直角三角形
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个二维 boolean 矩阵 grid 。
 *
 * 如果 grid 的 3 个元素的集合中，一个元素与另一个元素在 同一行，并且与第三个元素在 同一列，则该集合是一个 直角三角形。3 个元素 不必 彼此相邻。
 *
 * 请你返回使用 grid 中的 3 个元素可以构建的 直角三角形 数目，且满足 3 个元素值 都 为 1 。
 *
 *
 *
 * 示例 1：
 *
 * 0	1	0
 * 0	1	1
 * 0	1	0
 * 0	1	0
 * 0	1	1
 * 0	1	0
 * 输入：grid = [[0,1,0],[0,1,1],[0,1,0]]
 *
 * 输出：2
 *
 * 解释：
 *
 * 有 2 个值为 1 的直角三角形。注意蓝色的那个 没有 组成直角三角形，因为 3 个元素在同一列。
 *
 * 示例 2：
 *
 * 1	0	0	0
 * 0	1	0	1
 * 1	0	0	0
 * 输入：grid = [[1,0,0,0],[0,1,0,1],[1,0,0,0]]
 *
 * 输出：0
 *
 * 解释：
 *
 * 没有值为 1 的直角三角形。注意蓝色的那个 没有 组成直角三角形。
 *
 * 示例 3：
 *
 * 1	0	1
 * 1	0	0
 * 1	0	0
 * 1	0	1
 * 1	0	0
 * 1	0	0
 * 输入：grid = [[1,0,1],[1,0,0],[1,0,0]]
 *
 * 输出：2
 *
 * 解释：
 *
 * 有两个值为 1 的直角三角形。
 *
 *
 *
 * 提示：
 *
 * 1 <= grid.length <= 1000
 * 1 <= grid[i].length <= 1000
 * 0 <= grid[i][j] <= 1
 */

/**
 * 三点变量, 枚举中间(直角顶点)
 */
public class LC3128_numberOfRightTriangles {
    public long numberOfRightTriangles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long ans = 0;
        //设第 i 行有 rowSum 个 1，第 j 列有 colSum 个 1。
        //根据乘法原理，直角顶点为 (i,j) 的「直角三角形」有(rowSum−1)⋅(colSum−1)
        int[] colSum = new int[n];
        for(int[] row: grid){
            for(int j = 0; j < n; j++){
                colSum[j] +=row[j];
            }
        }

        //枚举行
        for(int[] row: grid){
            int rowSum = 0;
            //枚举当前行的所有列, 计算当前行所有1的个数
            for(int j = 0; j <n; j++){
                rowSum+=row[j];
            }

            //枚举直角顶点(枚举中间)
            for(int j = 0;j < n;j++){
                if(row[j] == 1){
                    ans+=(long)(rowSum-1)*(colSum[j]-1);
                }
            }
        }

        return ans;
    }
}

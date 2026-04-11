package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_diagonal;

/**
 * 2711. 对角线上不同值的数量差
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始、大小为 m x n 的二维矩阵 grid ，请你求解大小同样为 m x n 的答案矩阵 answer 。
 *
 * 矩阵 answer 中每个单元格 (r, c) 的值可以按下述方式进行计算：
 *
 * 令 topLeft[r][c] 为矩阵 grid 中单元格 (r, c) 左上角对角线上 不同值 的数量。
 * 令 bottomRight[r][c] 为矩阵 grid 中单元格 (r, c) 右下角对角线上 不同值 的数量。
 * 然后 answer[r][c] = |topLeft[r][c] - bottomRight[r][c]| 。
 *
 * 返回矩阵 answer 。
 *
 * 矩阵对角线 是从最顶行或最左列的某个单元格开始，向右下方向走到矩阵末尾的对角线。
 *
 * 如果单元格 (r1, c1) 和单元格 (r, c) 属于同一条对角线且 r1 < r ，则单元格 (r1, c1) 属于单元格 (r, c) 的左上对角线。类似地，可以定义右下对角线。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[1,2,3],[3,1,5],[3,2,1]]
 * 输出：[[1,1,0],[1,0,1],[0,1,1]]
 * 解释：第 1 个图表示最初的矩阵 grid 。
 * 第 2 个图表示对单元格 (0,0) 计算，其中蓝色单元格是位于右下对角线的单元格。
 * 第 3 个图表示对单元格 (1,2) 计算，其中红色单元格是位于左上对角线的单元格。
 * 第 4 个图表示对单元格 (1,1) 计算，其中蓝色单元格是位于右下对角线的单元格，红色单元格是位于左上对角线的单元格。
 * - 单元格 (0,0) 的右下对角线包含 [1,1] ，而左上对角线包含 [] 。对应答案是 |1 - 0| = 1 。
 * - 单元格 (1,2) 的右下对角线包含 [] ，而左上对角线包含 [2] 。对应答案是 |0 - 1| = 1 。
 * - 单元格 (1,1) 的右下对角线包含 [1] ，而左上对角线包含 [1] 。对应答案是 |1 - 1| = 0 。
 * 其他单元格的对应答案也可以按照这样的流程进行计算。
 * 示例 2：
 *
 * 输入：grid = [[1]]
 * 输出：[[0]]
 * 解释：- 单元格 (0,0) 的右下对角线包含 [] ，左上对角线包含 [] 。对应答案是 |0 - 0| = 0 。
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n, grid[i][j] <= 50
 */

import java.util.HashSet;
import java.util.Set;

/**
 * 枚举技巧 , 枚举对角线
 */
public class LC2711_differenceOfDistinctValues {
    public int[][] differenceOfDistinctValues(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m][n];
        for(int c = 0; c<m-1+n; c++){
            int k = c+1;
            //知道j就知道i = k+j-n, 所以接下来求j
            int maxJ = Math.min(n-1, m-1+n-k);//最大化i, 则最大化j
            int minJ = Math.max(0, n-k);//最小化i, 则最小化j

            //前后缀分解
            Set<Integer> topLeftCnt = new HashSet<>();
            Set<Integer> bottomRightCnt = new HashSet<>();
            for(int j = minJ; j<=maxJ; j++){
                int i = k + j -n;
                ans[i][j] = topLeftCnt.size();//先赋值topLeftCnt数量, 避免把自己包含进去
                topLeftCnt.add(grid[i][j]);
            }
            for(int j = maxJ; j>=minJ; j--){
                int i = k + j -n;
                //answer[r][c] = |topLeft[r][c] - bottomRight[r][c]| 。
                ans[i][j] = Math.abs(ans[i][j] - bottomRightCnt.size());
                bottomRightCnt.add(grid[i][j]);
            }
        }

        return ans;
    }
}

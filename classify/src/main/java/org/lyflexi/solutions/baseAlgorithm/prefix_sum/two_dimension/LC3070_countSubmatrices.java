package org.lyflexi.solutions.baseAlgorithm.prefix_sum.two_dimension;

/**
 * 3070. 元素和小于等于 k 的子矩阵的数目
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个下标从 0 开始的整数矩阵 grid 和一个整数 k。
 *
 * 返回包含 grid 左上角元素、元素和小于或等于 k 的 子矩阵的数目。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[7,6,3],[6,6,1]], k = 18
 * 输出：4
 * 解释：如上图所示，只有 4 个子矩阵满足：包含 grid 的左上角元素，并且元素和小于或等于 18 。
 * 示例 2：
 *
 *
 * 输入：grid = [[7,2,9],[1,5,0],[2,6,6]], k = 20
 * 输出：6
 * 解释：如上图所示，只有 6 个子矩阵满足：包含 grid 的左上角元素，并且元素和小于或等于 20 。
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= n, m <= 1000
 * 0 <= grid[i][j] <= 1000
 * 1 <= k <= 109
 */

/**
 * 二维前缀和
 */
public class LC3070_countSubmatrices {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] preS = new int[m+1][n+1];
        int ans = 0;
        for(int i = 1; i<m+1; i++){
            for(int j = 1; j<n+1; j++){
                preS[i][j] = preS[i][j-1] + preS[i-1][j] - preS[i-1][j-1] + grid[i-1][j-1];
                if(preS[i][j]<=k){
                    ans += 1;
                }
            }
        }
        return ans;
    }
}

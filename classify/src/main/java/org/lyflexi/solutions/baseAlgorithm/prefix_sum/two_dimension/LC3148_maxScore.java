package org.lyflexi.solutions.baseAlgorithm.prefix_sum.two_dimension;

/**
 * 3148. 矩阵中的最大得分
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个由 正整数 组成、大小为 m x n 的矩阵 grid。你可以从矩阵中的任一单元格移动到另一个位于正下方或正右侧的任意单元格（不必相邻）。从值为 c1 的单元格移动到值为 c2 的单元格的得分为 c2 - c1 。
 *
 * 你可以从 任一 单元格开始，并且必须至少移动一次。
 *
 * 返回你能得到的 最大 总得分。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：grid = [[9,5,7,3],[8,9,6,1],[6,7,14,3],[2,5,3,1]]
 *
 * 输出：9
 *
 * 解释：从单元格 (0, 1) 开始，并执行以下移动：
 * - 从单元格 (0, 1) 移动到 (2, 1)，得分为 7 - 5 = 2 。
 * - 从单元格 (2, 1) 移动到 (2, 2)，得分为 14 - 7 = 7 。
 * 总得分为 2 + 7 = 9 。
 *
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[4,3,2],[3,2,1]]
 *
 * 输出：-1
 *
 * 解释：从单元格 (0, 0) 开始，执行一次移动：从 (0, 0) 到 (0, 1) 。得分为 3 - 4 = -1 。
 *
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 1000
 * 4 <= m * n <= 105
 * 1 <= grid[i][j] <= 105
 */

import java.util.Arrays;
import java.util.List;

/**
 * 这道题并不是二维前缀和, 只是利用二维前缀和的思想求二维格子的最小值, 并不做和
 */
public class LC3148_maxScore {
    public int maxScore(List<List<Integer>> grid) {
        //将二维前缀和变式下, 我们现在求二维格子的最小值, 用f[i][j]表示
        int m = grid.size(), n = grid.get(0).size();
        int[][] f = new int[m+1][n+1];
        //答案求最大, 初始化MIN
        int ans = Integer.MIN_VALUE;
        //由于计算过程要求格子最小值, 将第一行初始化MAX
        Arrays.fill(f[0], Integer.MAX_VALUE);
        for(int i = 0; i< m; i++){
            //第一列也要补零
            f[i+1][0] = Integer.MAX_VALUE;
            List<Integer> row = grid.get(i);
            for(int j = 0; j<n; j++){
                int mn = Math.min(f[i][j+1], f[i+1][j]);
                int x = row.get(j);
                //我们并没有对mn做求和运算, 它一值都是格子的最小值
                ans = Math.max(ans, x - mn);
                //f[i+1][j+1] = Math.min(Math.min(f[i][j+1], f[i+1][j]), x);
                f[i+1][j+1] = Math.min(mn, x);

                //由于题目要求终点不包含起点, 因此对最新点位f[i+1][j+1]的迭代要放在最后计算
            }
        }
        return ans;
    }
}

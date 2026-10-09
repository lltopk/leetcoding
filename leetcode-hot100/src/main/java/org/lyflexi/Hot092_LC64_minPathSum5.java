package org.lyflexi;

import java.util.*;

/**
 * 64. 最小路径和
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 
 * 说明：每次只能向下或者向右移动一步。
 * 
 * 示例 1：
 * 
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * 
 * 示例 2：
 * 
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 * 
 * 提示：
 * 
 * - m == grid.length
 * 
 * - n == grid[i].length
 * 
 * - 1 <= m, n <= 200
 * 
 * - 0 <= grid[i][j] <= 200
 */

/**
 * 答疑
 * 
 * 问：可以初始化 $f[0] = 0$ 吗？
 * 
 * 答：这会导致所有 $f[i][0]$ 都是 $0$。但对于 $i>1$ 的情况，$f[i][0]$ 必须是 $\infty$。如果 $f[i][0]=0\ (i>1)$，相当于出界也是合法的，这就搞错了。
 */
public class Hot092_LC64_minPathSum5 {
    public int minPathSum(int[][] grid) {
        int n = grid[0].length;
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[1] = 0;
        for (int[] row : grid) {
            for (int j = 0; j < n; j++) {
                f[j + 1] = Math.min(f[j], f[j + 1]) + row[j];
            }
        }
        return f[n];
    }
}

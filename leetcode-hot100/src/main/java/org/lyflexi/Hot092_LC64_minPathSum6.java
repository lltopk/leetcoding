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
 * 五、空间优化（原地修改）
 * 
 * 直接用 $grid[0]$ 当作 $f$ 数组，可以做到 $O(1)$ 额外空间。
 * 
 * 由于 $grid[0]$ 的长度只有 $n$，所以要按照
 * 
 * $$
 * f[i][j] = min(f[i][j-1] , f[i-1][j]) +grid[i][j]
 * $$
 * 
 * 的方式来转移。
 * 
 * $i=0$ 和 $j=0$ 的情况要单独计算：
 * 
 * - $i=0$ 时，上式为 $f[i][j] = f[i][j-1]+grid[i][j]$；用一个数组时，为 $f[j] = f[j-1]+grid[0][j]=f[j-1]+f[j]$（$grid[0]$ 就是 $f$ 数组）。
 * - $j=0$ 时，上式为 $f[i][j] = f[i-1][j]+grid[i][j]$；用一个数组时，为 $f[0] = f[0]+grid[i][0]$。
 * 注：对比上下两份代码，你会发现长为 $n+1$ 的数组写起来是更加简洁的，因为可以避免特判位于边界的情况。
 */
public class Hot092_LC64_minPathSum6 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] f = grid[0]; // 这里没有拷贝，f 和 grid[0] 都持有同一段内存
        for (int j = 1; j < n; j++) {
            f[j] += f[j - 1];
        }
        for (int i = 1; i < m; i++) {
            f[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                f[j] = Math.min(f[j - 1], f[j]) + grid[i][j];
            }
        }
        return f[n - 1];
    }
}

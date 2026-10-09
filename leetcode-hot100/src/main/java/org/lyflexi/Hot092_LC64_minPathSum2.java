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
 * 二、用记忆化搜索优化
 * 
 * 举个例子，对于 $dfs(2,2)$ 来说，「先左再上」和「先上再左」，都会调用 $dfs(1,1)$。
 * 
 * 考虑到整个递归过程中有大量重复递归调用（递归入参相同）。由于递归函数没有副作用，同样的入参无论计算多少次，算出来的结果都是一样的，因此可以用记忆化搜索来优化：
 * 
 * - 如果一个状态（递归入参）是第一次遇到，那么可以在返回前，把状态及其结果记到一个 $memo$ 数组中。
 * - 如果一个状态不是第一次遇到（$memo$ 中保存的结果不等于 $memo$ 的初始值），那么可以直接返回 $memo$ 中保存的结果。
 * 
 * 注意：$memo$ 数组的初始值一定不能等于要记忆化的值！例如初始值设置为 $0$，并且要记忆化的 $dfs(i,j)$ 也等于 $0$，那就没法判断 $0$ 到底表示第一次遇到这个状态，还是表示之前遇到过了，从而导致记忆化失效。一般把初始值设置为 $-1$。
 * Python 用户可以无视上面这段，直接用 @cache 装饰器。
 */
public class Hot092_LC64_minPathSum2 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }
        return dfs(m - 1, n - 1, grid, memo);
    }

    private int dfs(int i, int j, int[][] grid, int[][] memo) {
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        if (i == 0 && j == 0) {
            return grid[i][j];
        }
        if (memo[i][j] != -1) { // 之前计算过
            return memo[i][j];
        }
        return memo[i][j] = Math.min(dfs(i, j - 1, grid, memo), dfs(i - 1, j, grid, memo)) + grid[i][j];
    }
}

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
 * 问：看上去，这计算的是从右下角到左上角的最小价值和？
 * 
 * 答：注意加法运算发生在递归返回后，即递归的「归」的时候我们才开始计算最小价值和，所以计算顺序是从左上角到右下角。
 * 
 * 问：为什么要倒着思考？
 * 
 * 答：方便后面 1:1 地翻译成递推。
 */
// 会超时的递归写法
public class Hot092_LC64_minPathSum {
    public int minPathSum(int[][] grid) {
        return dfs(grid.length - 1, grid[0].length - 1, grid);
    }

    private int dfs(int i, int j, int[][] grid) {
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        if (i == 0 && j == 0) {
            return grid[i][j];
        }
        return Math.min(dfs(i, j - 1, grid), dfs(i - 1, j, grid)) + grid[i][j];
    }
}

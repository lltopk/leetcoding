package org.lyflexi.solutions.dp;

import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/2/8 22:01
 */

/*
64. 最小路径和: 带有数值的二维动态规划规划，求和的最小值//f(x,y) = Math.min(f(x-1,y)+grid[x][y],f(x,y-1)+grid[x][y])

给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
说明：每次只能向下或者向右移动一步。


示例1：
输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。


示例 2：
输入：grid = [[1,2,3],[4,5,6]]
输出：12


*/
public class Solution11_MinPathSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入行数");
        int m = Integer.parseInt(scanner.nextLine());
        System.out.println("请输入列数");
        int n = Integer.parseInt(scanner.nextLine());
        int[][] grid = new int[m][n];
        System.out.println("请填充数组元素，放心，我可以循环控制输入");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int item = Integer.parseInt(scanner.nextLine());
                grid[i][j] = item;
            }
        }
        System.out.println(minPathSum(grid));
    }


    public static int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;


        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            int his = 0;
            for (int j = 0; j < i; j++) {
                his += grid[j][0];
            }
            dp[i][0] = his+grid[i][0];
        }

        for (int i = 1; i < n; i++) {
            int his = 0;
            for (int j = 0; j < i; j++) {
                his += grid[0][j];
            }
            dp[0][i] = his+grid[0][i];
        }

//f(x,y) = Math.min(f(x-1,y)+grid[x][y],f(x,y-1)+grid[x][y])
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j]+grid[i][j],dp[i][j-1]+grid[i][j]);
            }
        }



        return dp[m-1][n-1];

    }
}

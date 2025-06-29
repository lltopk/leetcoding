package org.lyflexi.solutions.dp;

import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/2/9 7:52
 */

/*63. 不同路径 II

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
网格中的障碍物和空位置分别用 1 和 0 来表示。

示例一：
输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
输出：2
解释：3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右

示例二：
输入：obstacleGrid = [[0,1],[0,0]]
输出：1
*/
public class Solution12_PathsWithObstacles {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入行数");
        int m = Integer.parseInt(scanner.nextLine());
        System.out.println("请输入列数");
        int n = Integer.parseInt(scanner.nextLine());

        System.out.println("请输入矩阵元素，放心我在循环录入");
        int[][] obstacleGrid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                obstacleGrid[i][j] = Integer.parseInt(scanner.nextLine());
            }
        }
        System.out.println(uniquePathsWithObstacles(obstacleGrid));

    }


    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1){
            return 0;
        }

        int[][] dp = new int[m][n];

//      只要第一列出现1，则该元素之后的dp元素全部置为0，意味走不动
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
            if (obstacleGrid[i][0]==1){
                dp[i][0] = 0;
                break;
            }
        }

//只要第一行出现1，则该元素之后的dp元素全部置为0,意味走不动
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
            if (obstacleGrid[0][i]==1){
                dp[0][i] = 0;
                break;
            }
        }

        //f(x,y) = f(x-1,y)+f(x,y-1)
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j]!=1){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];

    }
}

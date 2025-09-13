package org.lyflexi.solutions.dfs;

/**
 * @Author: ly
 * @Date: 2024/3/28 12:30
 */

/*
* 200. 岛屿数量
给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
此外，你可以假设该网格的四条边均被水包围。


示例 1：
输入：grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
输出：1
*
示例 2：
输入：grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
输出：3
*
*
* */
public class Solution02_IslandNums {
    // 深度优先遍历，递归
    public int numIslands(char[][] grid) {
        int ans = 0;
        for (int i = 0; i != grid.length; ++i) {
            for (int j = 0; j != grid[0].length; ++j) {
                if(dfs(grid, i, j)>0){
                    ans++;
                }
            }
        }
        return ans;
    }

    //完整的一个岛屿面积
    public static int dfs(char[][] grid, int cur_i, int cur_j) {
        if (cur_i < 0 || cur_j < 0 || cur_i == grid.length || cur_j == grid[0].length || grid[cur_i][cur_j] != '1') {
            return 0;
        }
        //访问过及时置为0
        grid[cur_i][cur_j] = 0;
        int[] di = { 0, 0, 1, -1 };
        int[] dj = { 1, -1, 0, 0 };
        int ans = 1;
        for (int index = 0; index != 4; ++index) {
            int next_i = cur_i + di[index], next_j = cur_j + dj[index];
            ans += dfs(grid, next_i, next_j);
        }
        return ans;
    }

    // 深度优先遍历，栈，栈的本质就是模拟了递归
    // public int numIslands(char[][] grid) {
    // int ans = 0;
    // for (int i = 0; i != grid.length; ++i) {
    // for (int j = 0; j != grid[0].length; ++j) {
    // //寻找当前岛屿
    // int currentRound = 0;

    // Stack<Integer> stacki = new Stack<Integer>();
    // Stack<Integer> stackj = new Stack<Integer>();
    // stacki.push(i);//栈中存储横坐标
    // stackj.push(j);//栈中存储纵坐标
    // while (!stacki.isEmpty()) {
    // int cur_i = stacki.pop(), cur_j = stackj.pop();
    // //边界异常处理，但是不要忘了最关键的一个条件|| grid[cur_i][cur_j] != 1，此条件不加栈是会溢出的
    // if (cur_i < 0 || cur_j < 0 || cur_i == grid.length || cur_j == grid[0].length
    // || grid[cur_i][cur_j] != '1') {
    // continue;
    // }
    // //计算岛屿面积
    // ++currentRound;
    // //避免重复计算
    // grid[cur_i][cur_j] = 0;
    // //设置好四个方位坐标用于移动
    // int[] di = {0, 0, 1, -1};
    // int[] dj = {1, -1, 0, 0};
    // //将四个方位坐标添加到各自的栈中
    // for (int index = 0; index != 4; ++index) {
    // int next_i = cur_i + di[index], next_j = cur_j + dj[index];
    // stacki.push(next_i);
    // stackj.push(next_j);
    // }
    // }
    // //求岛屿个数
    // if(currentRound>0){
    // ans = ans +1 ;
    // }

    // }
    // }
    // return ans;
    // }
}

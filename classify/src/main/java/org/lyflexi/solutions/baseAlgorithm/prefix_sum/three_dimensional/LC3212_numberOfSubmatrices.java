package org.lyflexi.solutions.baseAlgorithm.prefix_sum.three_dimensional;

/**
 * 3212. 统计 X 和 Y 频数相等的子矩阵数量
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个二维字符矩阵 grid，其中 grid[i][j] 可能是 'X'、'Y' 或 '.'，返回满足以下条件的子矩阵数量：
 *
 * 包含 grid[0][0]
 * 'X' 和 'Y' 的频数相等。
 * 至少包含一个 'X'。
 *
 *
 * 示例 1：
 *
 * 输入： grid = [["X","Y","."],["Y",".","."]]
 *
 * 输出： 3
 *
 * 解释：
 *
 *
 *
 * 示例 2：
 *
 * 输入： grid = [["X","X"],["X","Y"]]
 *
 * 输出： 0
 *
 * 解释：
 *
 * 不存在满足 'X' 和 'Y' 频数相等的子矩阵。
 *
 * 示例 3：
 *
 * 输入： grid = [[".","."],[".","."]]
 *
 * 输出： 0
 *
 * 解释：
 *
 * 不存在满足至少包含一个 'X' 的子矩阵。
 *
 *
 *
 * 提示：
 *
 * 1 <= grid.length, grid[i].length <= 1000
 * grid[i][j] 可能是 'X'、'Y' 或 '.'.
 */

/**
 * 三维前缀和
 */
public class LC3212_numberOfSubmatrices {
    //x->1
    //y->-1
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //表示两个二维前缀和数组, 一个用于前缀和计算, 一个用于计算前缀和中'X'的个数
        int[][][] preS = new int[m+1][n+1][2];
        int ans = 0;
        for(int i =1; i<m+1; i++){
            for(int j = 1; j< n+1; j++){
                int c = 0;//代表.
                int x = 0;//代表'X'
                if(grid[i-1][j-1]=='X'){
                    c = 1;
                    x = 1;
                }else if(grid[i-1][j-1]=='Y'){
                    c = -1;
                }
                preS[i][j][0] = preS[i][j-1][0] + preS[i-1][j][0] - preS[i-1][j-1][0] + c;
                preS[i][j][1] = preS[i][j-1][1] + preS[i-1][j][1] - preS[i-1][j-1][1] + x;
                if(preS[i][j][0] == 0 && preS[i][j][1]>0){
                    ans +=1;
                }
            }
        }

        return ans;
    }
}

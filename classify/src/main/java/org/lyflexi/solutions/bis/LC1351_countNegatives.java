package org.lyflexi.solutions.bis;

/**
 * @author hasee
 * @description V1.0
 * 1351. 统计有序矩阵中的负数
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个 m * n 的矩阵 grid，矩阵中的元素无论是按行还是按列，都以非严格递减顺序排列。 请你统计并返回 grid 中 负数 的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * 输出：8
 * 解释：矩阵中共有 8 个负数。
 * 示例 2：
 *
 * 输入：grid = [[3,2],[1,0]]
 * 输出：0
 * @create 2025/10/7 17:18
 */
public class LC1351_countNegatives {
    public int countNegatives(int[][] grid) {
        int m = grid.length;
        int rs = 0;
        for(int i=0;i<m;i++){
            rs +=bisCount(grid[i]);
        }
        return rs;
    }

    private int bisCount(int[] line){
        int l = 0;
        int n = line.length;
        int r = n;
        int rBoard = -1;
        while(l<r){
            int midIndex = l + ((r-l)>>1);
            //递减的
            if(line[midIndex]<0){
                r = midIndex;
            }else if(line[midIndex]>=0){
                l = midIndex+1;
                rBoard = midIndex;//找右界
            }
        }

        return rBoard>=0?n-(rBoard+1):n-l;
    }
}

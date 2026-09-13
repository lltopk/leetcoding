package org.lyflexi.solutions.strategy_binary;

/**
 * 240. 搜索二维矩阵 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 916,756/1.6M
 * 通过率
 * 57.6%
 */
public class LC240_searchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        for(int i=0;i<m;i++){
            if(biSearch(matrix[i],target)!=-1){
                return true;
            }
        }
        return false;
    }

    private int biSearch(int[] line,int target){
        int n = line.length;
        int l = 0;
        int r = n;
        while(l<r){
            int midIndex = (l+r)>>1;
            if(line[midIndex]>target){
                r = midIndex;
            }else if(line[midIndex]<target){
                l = midIndex+1;
            }else{
                l = r = midIndex;
                return l;
            }

        }
        return -1;
    }
}

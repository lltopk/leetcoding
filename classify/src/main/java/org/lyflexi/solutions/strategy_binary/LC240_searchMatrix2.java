package org.lyflexi.solutions.strategy_binary;

/**
 * 240. 搜索二维矩阵 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 * <p>
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 916,756/1.6M
 * 通过率
 * 57.6%
 */

/**
 * 排除法
 */
public class LC240_searchMatrix2 {
    /**
     * 矩阵的四个角是最特殊的，左上角的值最小，右下角的值最大，另两个角的值居中。
     *
     * 可以从右上角或者左下角这种中间值开始，帮助我们排除不含答案的行列。
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length - 1; // 从右上角开始

        //下面i只会加, j只会减, while条件简单许多(不需要i < matrix.length && i >= 0 && j >= 0 && j < matrix.length)
        while (i < matrix.length && j >= 0) { // 还有剩余元素
            if (matrix[i][j] == target) {
                return true; // 找到 target
            }
            if (matrix[i][j] < target) {
                i++; // 这一行剩余元素全部小于 target，排除
            } else {
                j--; // 这一列剩余元素全部大于 target，排除
            }
        }
        return false;
    }
}

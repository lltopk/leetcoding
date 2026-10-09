package org.lyflexi;

import java.util.*;

/**
 * 240. 搜索二维矩阵 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * 
 * - 每行的元素从左到右升序排列。
 * 
 * - 每列的元素从上到下升序排列。
 * 
 * 示例 1：
 * 
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 
 * 示例 2：
 * 
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 * 
 * 提示：
 * 
 * - m == matrix.length
 * 
 * - n == matrix[i].length
 * 
 * - 1 <= n, m <= 300
 * 
 * - -10^9 <= matrix[i][j] <= 10^9
 * 
 * - 每行的所有元素从左到右升序排列
 * 
 * - 每列的所有元素从上到下升序排列
 * 
 * - -10^9 <= target <= 10^9
 */

/**
 * 矩阵的四个角是最特殊的，左上角的值最小，右下角的值最大，另两个角的值居中。对于有序数据，从右上角或者左下角这种中间值开始，可以帮助我们排除不含答案的行列。
 * 
 * 注：也可以从左下角开始，方法类似。
 * 
 * 总结：利用 $matrix$ 行列有序的性质，我们可以用 $O(1)$ 的时间获取 $O(m)$ 或 $O(n)$ 的信息。相比之下，$O(mn)$ 的暴力查找（一个一个地找），每次花费 $O(1)$ 的时间，只获取了 $O(1)$ 的信息。
 * 
 * 这就是为什么我们可以把 $O(mn)$ 的暴力查找优化成 $O(m+n)$。
 */
public class Hot021_LC240_searchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length - 1; // 从右上角开始
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

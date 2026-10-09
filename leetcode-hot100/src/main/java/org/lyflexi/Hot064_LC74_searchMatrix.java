package org.lyflexi;

import java.util.*;

/**
 * 74. 搜索二维矩阵
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 * 
 * - 每行中的整数从左到右按非严格递增顺序排列。
 * 
 * - 每行的第一个整数大于前一行的最后一个整数。
 * 
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 * 
 * 你必须编写一个时间复杂度为 O(log(m * n)) 的解决方案。
 * 
 * 示例 1：
 * 
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * 
 * 示例 2：
 * 
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 * 
 * 提示：
 * 
 * - m == matrix.length
 * 
 * - n == matrix[i].length
 * 
 * - 1 <= m, n <= 100
 * 
 * - -10^4 <= matrix[i][j], target <= 10^4
 */

/**
 * 由于矩阵的每一行是递增的，且每行的第一个数大于前一行的最后一个数，如果把矩阵每一行拼在一起，我们可以得到一个递增数组。
 * 
 * 例如示例 1，三行拼在一起得
 * 
 * $$
 * a = [1,3,5,7,10,11,16,20,23,30,34,60]
 * $$
 * 
 * 由于这是一个有序数组，我们可以用二分查找判断 $target$ 是否在 $matrix$ 中。
 * 
 * 代码实现时，并不需要真的拼成一个长为 $mn$ 的数组 $a$，而是将 $a[i]$ 转换成矩阵中的行号和列号。例如示例 1，$i=9$ 对应的 $a[i]=30$，由于矩阵有 $n=4$ 列，所以 $a[i]$ 在 $<=ft\lfloor\dfrac{i}{n}\right\rfloor=2$ 行，在 $i\bmod n = 1$ 列。
 * 
 * 一般地，有
 * 
 * $$
 * a[i] = matrix[\lfloor i/n\rfloor][i\bmod n]
 * $$
 * 
 * 关于二分查找的原理，请看视频讲解：二分查找 红蓝染色法【基础算法精讲 04】
 * 
 * 下面用的开区间二分，其它写法也可以。
 */
public class Hot064_LC74_searchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = -1;
        int right = m * n;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            int x = matrix[mid / n][mid % n];
            if (x == target) {
                return true;
            }
            if (x < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return false;
    }
}

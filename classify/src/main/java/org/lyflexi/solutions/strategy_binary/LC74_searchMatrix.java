package org.lyflexi.solutions.strategy_binary;

/**
 * 74. 搜索二维矩阵
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 *
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 *
 * 你必须编写一个时间复杂度为 O(log(m * n)) 的解决方案。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 807,277/1.5M
 * 通过率
 * 52.6%
 */
public class LC74_searchMatrix {
    /**
     * 由于矩阵的每一行是递增的，且每行的第一个数大于前一行的最后一个数，如果把矩阵每一行拼在一起，我们可以得到一个递增数组。
     *
     * 代码实现时，并不需要真的拼成一个长为 mn 的数组 a，而是设定二分的上界为索引最大值m*n
     *
     * 这样我们可以计算行号和列号。一般地，有
     *
     * a[i]=matrix[⌊i/n⌋][i % n]
     *
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/search-a-2d-matrix/solutions/2783931/liang-chong-fang-fa-er-fen-cha-zhao-pai-39d74/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param matrix
     * @param target
     * @return
     */
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

package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_diagonal;

/**
 * 3446. 按对角线进行矩阵排序
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个大小为 n x n 的整数方阵 grid。返回一个经过如下调整的矩阵：
 *
 * 左下角三角形（包括中间对角线）的对角线按 非递增顺序 排序。
 * 右上角三角形 的对角线按 非递减顺序 排序。
 *
 *
 * 示例 1：
 *
 * 输入： grid = [[1,7,3],[9,8,2],[4,5,6]]
 *
 * 输出： [[8,2,3],[9,6,7],[4,5,1]]
 *
 * 解释：
 *
 *
 *
 * 标有黑色箭头的对角线（左下角三角形）应按非递增顺序排序：
 *
 * [1, 8, 6] 变为 [8, 6, 1]。
 * [9, 5] 和 [4] 保持不变。
 * 标有蓝色箭头的对角线（右上角三角形）应按非递减顺序排序：
 *
 * [7, 2] 变为 [2, 7]。
 * [3] 保持不变。
 * 示例 2：
 *
 * 输入： grid = [[0,1],[1,2]]
 *
 * 输出： [[2,1],[1,0]]
 *
 * 解释：
 *
 *
 *
 * 标有黑色箭头的对角线必须按非递增顺序排序，因此 [0, 2] 变为 [2, 0]。其他对角线已经符合要求。
 *
 * 示例 3：
 *
 * 输入： grid = [[1]]
 *
 * 输出： [[1]]
 *
 * 解释：
 *
 * 只有一个元素的对角线已经符合要求，因此无需修改。
 *
 *
 *
 * 提示：
 *
 * grid.length == grid[i].length == n
 * 1 <= n <= 10
 * -105 <= grid[i][j] <= 105
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 枚举技巧 枚举对角线
 */
public class LC3446_sortMatrix {
    //枚举技巧, 遍历对角线
    public int[][] sortMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        //反向对角线的规律是任意格子i-j都是个定值, 比如最中间的对角线i-j = 0;
        //设k = i - j + n;
        //则最上面的对角线k = 1
        //最下面的对角线k = m - 1 + n (本题m == n)
        //k取值范围[1, m-1+n], 这恰好是对角线个数
        for(int c = 0; c<m-1+n; c++){
            int k = c+1;
            //知道j就知道i = k+j-n, 所以接下来求j
            int maxJ = Math.min(n-1, m-1+n-k);//最大化i, 则最大化j
            int minJ = Math.max(0, n-k);//最小化i, 则最小化j

            List<Integer> diagonal = new ArrayList<>();
            for(int j = minJ; j<=maxJ; j++){
                int i = k + j -n;
                diagonal.add(grid[i][j]);
            }

            //diagonal.size() == maxJ - minJ + 1
            diagonal.sort(minJ > 0? null: Comparator.reverseOrder());

            for(int j = minJ; j<=maxJ; j++){
                int i = k + j -n;
                grid[i][j] = diagonal.get(j-minJ);
            }
        }

        return grid;
    }
}

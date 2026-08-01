package org.lyflexi.solutions.strategy_retrieval_dfs.grid_chart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 面试题 16.19. 水域大小
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
 *
 * 示例：
 *
 * 输入：
 * [
 *   [0,2,1,0],
 *   [0,1,0,1],
 *   [1,1,0,1],
 *   [0,1,0,1]
 * ]
 * 输出： [1,2,4]
 * 提示：
 *
 * 0 < len(land) <= 1000
 * 0 < len(land[i]) <= 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 55,020/81.1K
 * 通过率
 * 67.8%
 */
public class LC_Interview_16_19_pondSizes {
    List<Integer> ret = new ArrayList<Integer>();
    private static final int[][] DIRS = {
            {0, -1}, {0, 1}, {-1, 0}, {1, 0},
            {-1, -1}, {1, 1}, {1, -1}, {-1, 1}
    };//定义八个方向

    public int[] pondSizes(int[][] land) {
        for(int i = 0; i< land.length; i++){
            for(int j = 0; j < land[0].length; j++){
                //遇到水池则访问
                if(land[i][j] == 0){
                    ret.add(dfs(land, i, j));
                }
            }
        }
        int[] ret0 = new int[ret.size()];
        int i = 0;
        for (int x : ret)
            ret0[i++] = x;
        Arrays.sort(ret0);
        return ret0;
    }

    private int dfs(int[][] grid, int i , int j ){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 0){
            return 0;
        }

        //标记为1吧
        grid[i][j] = 1;
        int ret = 1;
        for(int[] dir: DIRS){
            ret += dfs(grid, i+ dir[0], j + dir[1]);
        }
        return ret;
    }
}

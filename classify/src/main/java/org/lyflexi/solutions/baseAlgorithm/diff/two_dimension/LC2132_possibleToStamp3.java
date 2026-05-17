package org.lyflexi.solutions.baseAlgorithm.diff.two_dimension;

/**
 * 2132. 用邮票贴满网格图
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个 m x n 的二进制矩阵 grid ，每个格子要么为 0 （空）要么为 1 （被占据）。
 *
 * 给你邮票的尺寸为 stampHeight x stampWidth 。我们想将邮票贴进二进制矩阵中，且满足以下 限制 和 要求 ：
 *
 * 覆盖所有 空 格子。
 * 不覆盖任何 被占据 的格子。
 * 我们可以放入任意数目的邮票。
 * 邮票可以相互有 重叠 部分。
 * 邮票不允许 旋转 。
 * 邮票必须完全在矩阵 内 。
 * 如果在满足上述要求的前提下，可以放入邮票，请返回 true ，否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
 * 输出：true
 * 解释：我们放入两个有重叠部分的邮票（图中标号为 1 和 2），它们能覆盖所有与空格子。
 * 示例 2：
 *
 *
 *
 * 输入：grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2
 * 输出：false
 * 解释：没办法放入邮票覆盖所有的空格子，且邮票不超出网格图以外。
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[r].length
 * 1 <= m, n <= 105
 * 1 <= m * n <= 2 * 105
 * grid[r][c] 要么是 0 ，要么是 1 。
 * 1 <= stampHeight, stampWidth <= 105
 */

/**
 * 二维差分数组
 */
public class LC2132_possibleToStamp3 {
    //二维差分题目, 还原覆盖次数矩阵的时候空间优化
    //灵神对二维差分数组下标的处理1-based, 最终能够让其还原覆盖次数矩阵的时候代码更加的优雅.
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        //两个关键点
        //1. 如何判断邮票可放, 这用二维前缀和为0判断, 如果可放, 批量更新覆盖次数矩阵visit[][]中与被贴住范围对应的格子
        //2. 如何标记格子被放, 这用二维差分性质2快速还原覆盖次数矩阵中每个格子+1操作, 这可以省略覆盖次数矩阵visit[][]
        //最后通过二维差分数组还原覆盖次数矩阵, 如果还原过程中存在差分子和为0, 则返回false

        int m = grid.length, n = grid[0].length;
        int[][] preS = new int[m+1][n+1];
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                preS[i+1][j+1] = preS[i][j+1] + preS[i+1][j] - preS[i][j] + grid[i][j];
            }
        }

        //定义差分数组长度, 因为要用到性质2, 所以长度都要加1
        //为了统一最后还原覆盖次数矩阵的计算方式, 我们对差分数组额外补充第一行和第一列全部保留为0值, 因此再对长度加1
        //最终差分数组长度+2
        int[][] diff = new int[m+2][n+2];
        for(int i = stampHeight-1; i<m; i++){//枚举邮票右下角
            for(int j = stampWidth-1; j<n; j++){
                int i0 = i - stampHeight + 1;
                int j0 = j - stampWidth + 1;
                int subS = preS[i+1][j+1] - preS[i+1][j0] - preS[i0][j+1] + preS[i0][j0];
                if(subS == 0){
                    //维护差分数组的四个顶点, 注意不从0开始, 1-based
                    int iNew = i+1;
                    int jNew = j+1;
                    int i0New = i0+1;
                    int j0New = j0+1;
                    diff[i0New][j0New]++;
                    diff[i0New][jNew+1]--;//jNew是实际枚举右顶点j+1(1-based), 这里再对jNew+1(性质2), 因此diff数组长度初始为m+2,n+2
                    diff[iNew+1][j0New]--;//iNew是实际枚举下顶点i+1(1-based), 这里再对iNew+1(性质2), 因此diff数组长度初始为m+2,n+2
                    diff[iNew+1][jNew+1]++;
                }
            }
        }

        //二维差分数组求和空间优化
        //以右下角(>0, >0)作为顶点的前缀和, 都依赖左 上 左上, 因此二维差分数组也可以就地求和
        int s = 0;
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                // 在二维差分求和过程中, 我们可以用差分和替换差分值, 这样就可以利用历史求和数据计算新的差分和, 这不影响新的差分和计算
                //此时 diff 已经不再是差分数组, 而是恢复后的覆盖次数矩阵
                diff[i+1][j+1] += diff[i][j+1] +  diff[i+1][j] - diff[i][j];
                if(diff[i+1][j+1] == 0 && grid[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }
}

package org.lyflexi.solutions.strategy_retrieval_dfs.back_track.permutation;

/**
 * 52. N 皇后 II
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 4
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= n <= 9
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 221,184/266.7K
 * 通过率
 * 82.9%
 */
public class LC52_totalNQueens {
    int ret;
    public int totalNQueens(int n) {
        //右上对角线i+j范围是[0, 2*n-2], 共2*n - 1 个
        boolean[] diagonal1 = new boolean[2*n - 1];
        //左上对角线i-j范围是[-n + 1, n - 1]， 共2*n - 1 个
        boolean[] diagonal2 = new boolean[2*n - 1];
        //标记当前列有没有放过皇后
        boolean[] colMark = new boolean[n];
        dfs(n, diagonal1, diagonal2, colMark, 0);
        return ret;
    }

    private void dfs(int n, boolean[] diagonal1, boolean[] diagonal2, boolean[] colMark, int i){
        //注意N皇后问题每次是按照整个棋盘来作为答案收集的， 而不是每行
        if(i == n){
            ret++;
            return;
        }

        for(int j = 0; j <n; j++){
            //!diagonal2[i - j + n - 1]中i-j之后还要 + n- 1， 目的是防止diagonal2数组索引取到负数
            if(!colMark[j] && !diagonal1[i+j] && !diagonal2[i - j + n - 1]){
                colMark[j] = diagonal1[i+j] = diagonal2[i - j + n - 1] = true;
                dfs(n, diagonal1, diagonal2, colMark, i+1);
                colMark[j] = diagonal1[i+j] = diagonal2[i - j + n - 1] = false;
            }
        }
    }
}

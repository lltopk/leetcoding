package org.lyflexi.solutions.strategy_retrieval_dfs.back_track.permutation;

/**
 * 51. N 皇后
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[["Q"]]
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
 * 705,526/937.1K
 * 通过率
 * 75.3%
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列
 *
 * valid函数是O(N)的， 能否做到O(1), 可以考虑提前存储可能攻击位置的存在性
 * - 提前存储右上对角线i+j范围是[0, 2*n-2], 共2*n - 1 个
 * - 提前存储左上对角线i-j范围是[-n + 1, n - 1]， 共2*n - 1 个
 * - 提前存储当前列， 先前是否已经放了皇后就会存在攻击性， 共n个
 */
public class LC51_solveNQueens2 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ret = new ArrayList<>();
        //索引代表行号， 值代表皇后的列号
        int[] col = new int[n];
        //右上对角线i+j范围是[0, 2*n-2], 共2*n - 1 个
        boolean[] diagonal1 = new boolean[2*n - 1];
        //左上对角线i-j范围是[-n + 1, n - 1]， 共2*n - 1 个
        boolean[] diagonal2 = new boolean[2*n - 1];
        //标记当前列有没有放过皇后
        boolean[] colMark = new boolean[n];
        //示例 1 的两个图，分别对应排列 [1,3,0,2] 和 [2,0,3,1]
        //因此题目等价于求列号的全排列， 因此state为为int[] col
        dfs(n, ret, col, diagonal1, diagonal2, colMark, 0);
        return ret;
    }

    /**
     i代表行号， 也代表层数
     col[], 代表这一层找到的答案
     */
    private void dfs(int n, List<List<String>> ret, int[] col, boolean[] diagonal1, boolean[] diagonal2, boolean[] colMark, int i){
        //注意N皇后问题每次是按照整个棋盘来作为答案收集的， 而不是每行
        if(i == n){
            List<String> ret0 = new ArrayList<>();
            for(int j: col){
                char[] row = new char[n];
                Arrays.fill(row, '.');
                row[j] = 'Q';
                ret0.add(new String(row));
            }
            ret.add(ret0);
            return;
        }

        for(int j = 0; j <n; j++){
            //!diagonal2[i - j + n - 1]中i-j之后还要 + n- 1， 目的是防止diagonal2数组索引取到负数
            if(!colMark[j] && !diagonal1[i+j] && !diagonal2[i - j + n - 1]){
                col[i] = j;
                colMark[j] = diagonal1[i+j] = diagonal2[i - j + n - 1] = true;
                dfs(n, ret, col, diagonal1, diagonal2, colMark, i+1);
                colMark[j] = diagonal1[i+j] = diagonal2[i - j + n - 1] = false;
            }
        }
    }
}

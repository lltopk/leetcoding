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
 */
public class LC51_solveNQueens {
    List<List<String>> ret = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        //索引代表行号， 值代表皇后的列号
        int[] col = new int[n];

        //示例 1 的两个图，分别对应排列 [1,3,0,2] 和 [2,0,3,1]
        //因此题目等价于求列号的全排列， 因此state为为int[] col
        dfs(n, col, 0);
        return ret;
    }

    /**
     i代表行号， 也代表层数
     col[], 代表这一层找到的答案
     */
    private void dfs(int n, int[] col, int i){
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
        }

        for(int j = 0; j <n; j++){
            if(valid(i, j, col)){
                col[i] = j;
                dfs(n, col, i+1);
            }
        }
    }

    private boolean valid(int i, int j, int[] col){
        //注意这里是和已经放过的皇后进行校验， 所以i0 < i, 而不是i0 < n
        for(int i0 = 0; i0< i; i0++){
            int j0 = col[i0];
            //i + j == i0 + j0代表右对角线
            //i - j == i0 - j0代表左对角线
            //不需要判断同行i == i0, 因为永远不会同行， i作为层数当前dfs(i)中的i肯定已经大于上一层的i0
            if(j == j0 || i + j == i0 + j0  || i - j == i0 - j0){
                return false;
            }
        }
        return true;
    }
}

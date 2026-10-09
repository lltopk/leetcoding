package org.lyflexi;

import java.util.*;

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
 * 示例 1：
 * 
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 
 * 示例 2：
 * 
 * 输入：n = 1
 * 输出：[["Q"]]
 * 
 * 提示：
 * 
 * - 1 <= n <= 9
 */

/**
 * 答疑
 * 
 * 问：本题和 46. 全排列 的关系是什么？
 * 
 * 答：由于每行恰好放一个皇后，记录每行的皇后放在哪一列，可以得到一个 $[0,n-1]$ 的排列 $queens$。示例 1 的两个图，分别对应排列 $[1, 3, 0, 2]$ 和 $[2, 0, 3, 1]$。所以我们本质上是在枚举列号的全排列。
 * 
 * 问：如何 $O(1)$ 判断两个皇后互相攻击？
 * 
 * 答：由于我们保证了每行每列恰好放一个皇后，所以只需检查斜方向。对于 ↗ 方向的格子，行号加列号是不变的。对于 ↖ 方向的格子，行号减列号是不变的。如果两个皇后，行号加列号相同，或者行号减列号相同，那么这两个皇后互相攻击。
 * 
 * 问：如何 $O(1)$ 判断当前位置被之前放置的某个皇后攻击到？
 * 
 * 答：额外用两个数组 $diag_1$ 和 $diag_2$ 分别标记之前放置的皇后的行号加列号，以及行号减列号。如果当前位置的行号加列号在 $diag_1$ 中（标记为 $true$），或者当前位置的行号减列号在 $diag_2$ 中（标记为 $true$），那么当前位置被之前放置的皇后攻击到，不能放皇后。
 */
public class Hot062_LC51_solveNQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        int[] queens = new int[n]; // 皇后放在 (r,queens[r])
        boolean[] col = new boolean[n];
        boolean[] diag1 = new boolean[n * 2 - 1];
        boolean[] diag2 = new boolean[n * 2 - 1];
        dfs(0, queens, col, diag1, diag2, ans);
        return ans;
    }

    private void dfs(int r, int[] queens, boolean[] col, boolean[] diag1, boolean[] diag2, List<List<String>> ans) {
        int n = col.length;
        if (r == n) {
            List<String> board = new ArrayList<>(n); // 预分配空间
            for (int c : queens) {
                char[] row = new char[n];
                Arrays.fill(row, '.');
                row[c] = 'Q';
                board.add(new String(row));
            }
            ans.add(board);
            return;
        }
        // 在 (r,c) 放皇后
        for (int c = 0; c < n; c++) {
            int rc = r - c + n - 1;
            if (!col[c] && !diag1[r + c] && !diag2[rc]) { // 判断能否放皇后
                queens[r] = c; // 直接覆盖，无需恢复现场
                col[c] = diag1[r + c] = diag2[rc] = true; // 皇后占用了 c 列和两条斜线
                dfs(r + 1, queens, col, diag1, diag2, ans);
                col[c] = diag1[r + c] = diag2[rc] = false; // 恢复现场
            }
        }
    }
}

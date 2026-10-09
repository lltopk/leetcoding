package org.lyflexi;

import java.util.*;

/**
 * 79. 单词搜索
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * 
 * 示例 1：
 * 
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCCED"
 * 输出：true
 * 
 * 示例 2：
 * 
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "SEE"
 * 输出：true
 * 
 * 示例 3：
 * 
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCB"
 * 输出：false
 * 
 * 提示：
 * 
 * - m == board.length
 * 
 * - n = board[i].length
 * 
 * - 1 <= m, n <= 6
 * 
 * - 1 <= word.length <= 15
 * 
 * - board 和 word 仅由大小写英文字母组成
 * 
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 */

/**
 * 基本思路（优化前）
 * 
 * 枚举 $i=0,1,2,\ldots,m-1$ 和 $j=0,1,2,\ldots,n-1$，以 $(i,j)$ 为起点开始搜索。
 * 
 * 同时，我们还需要知道当前匹配到了 $word$ 的第几个字母，所以还需要一个参数 $k$。
 * 
 * 定义 $dfs(i,j,k)$ 表示当前在 $board[i][j]$ 这个格子，要匹配 $word[k]$，返回在这个状态下最终能否匹配成功（搜索成功）。
 * 
 * 分类讨论：
 * 
 * - 如果 $board[i][j] \ne word[k]$，匹配失败，返回 $false$。
 * - 否则，如果 $k=len(word)-1$，匹配成功，返回 $true$。
 * - 否则，枚举 $(i,j)$ 周围的四个相邻格子 $(x,y)$，如果 $(x,y)$ 没有出界，则递归 $dfs(x,y,k+1)$，如果其返回 $true$，则 $dfs(i,j,k)$ 也返回 $true$。
 * - 如果递归周围的四个相邻格子都没有返回 $true$，则最后返回 $false$，表示没有搜到。
 * 
 * 细节：
 * 
 * - 递归过程中，为了避免重复访问同一个格子，可以用 $vis$ 数组标记。更简单的做法是，直接修改 $board[i][j]$，将其置为空（或者 $0$），返回 $false$ 前再恢复成原来的值（恢复现场）。注意返回 $true$ 的时候就不用恢复现场了，因为已经成功搜到 $word$ 了。
 */
public class Hot060_LC79_exist {
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs(i, j, 0, board, w)) {
                    return true; // 搜到了！
                }
            }
        }
        return false; // 没搜到
    }

    private boolean dfs(int i, int j, int k, char[][] board, char[] word) {
        if (board[i][j] != word[k]) { // 匹配失败
            return false;
        }
        if (k == word.length - 1) { // 匹配成功！
            return true;
        }
        board[i][j] = 0; // 标记访问过
        for (int[] d : DIRS) {
            int x = i + d[0];
            int y = j + d[1]; // 相邻格子
            if (0 <= x && x < board.length && 0 <= y && y < board[x].length && dfs(x, y, k + 1, board, word)) {
                return true; // 搜到了！
            }
        }
        board[i][j] = word[k]; // 恢复现场
        return false; // 没搜到
    }
}

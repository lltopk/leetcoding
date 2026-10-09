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
 * 第二个优化
 * 
 * 启发：如果 $word=abcd$ 但 $board$ 中的 $a$ 很多，$d$ 很少（比如只有一个），那么从 $d$ 开始搜索，能更快地找到答案。（即使我们肉眼去找，这种方法也是更快的）
 * 
 * 设 $word$ 的第一个字母在 $board$ 中出现了 $x$ 次，$word$ 的最后一个字母在 $board$ 中出现了 $y$ 次。
 * 
 * 如果 $y<x$，我们可以把 $word$ 反转，相当于从 $word$ 的最后一个字母开始搜索，这样更容易在一开始就满足 board[i][j] != word[k]，不会往下递归，递归的总次数更少。
 * 
 * 加上这两个优化，就可以击败接近 $100\%$ 了！其中 Java、C++、Go 和 Rust 都可以跑到 0ms。
 */
public class Hot060_LC79_exist2 {
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public boolean exist(char[][] board, String word) {
        // 为了方便，直接用数组代替哈希表
        int[] cnt = new int[128];
        for (char[] row : board) {
            for (char c : row) {
                cnt[c]++;
            }
        }

        // 优化一
        char[] w = word.toCharArray();
        int[] wordCnt = new int[128];
        for (char c : w) {
            if (++wordCnt[c] > cnt[c]) {
                return false;
            }
        }

        // 优化二
        if (cnt[w[w.length - 1]] < cnt[w[0]]) {
            w = new StringBuilder(word).reverse().toString().toCharArray();
        }

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

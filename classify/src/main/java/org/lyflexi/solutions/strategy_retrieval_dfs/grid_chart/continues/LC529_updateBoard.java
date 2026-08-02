package org.lyflexi.solutions.strategy_retrieval_dfs.grid_chart.continues;

/**
 * 529. 扫雷游戏
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 让我们一起来玩扫雷游戏！
 *
 * 给你一个大小为 m x n 二维字符矩阵 board ，表示扫雷游戏的盘面，其中：
 *
 * 'M' 代表一个 未挖出的 地雷，
 * 'E' 代表一个 未挖出的 空方块，
 * 'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的 已挖出的 空白方块，
 * 数字（'1' 到 '8'）表示有多少地雷与这块 已挖出的 方块相邻，
 * 'X' 则表示一个 已挖出的 地雷。
 * 给你一个整数数组 click ，其中 click = [clickr, clickc] 表示在所有 未挖出的 方块（'M' 或者 'E'）中的下一个点击位置（clickr 是行下标，clickc 是列下标）。
 *
 * 根据以下规则，返回相应位置被点击后对应的盘面：
 *
 * 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X' 。
 * 如果一个 没有相邻地雷 的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的 未挖出 方块都应该被递归地揭露。
 * 如果一个 至少与一个地雷相邻 的空方块（'E'）被挖出，修改它为数字（'1' 到 '8' ），表示相邻地雷的数量。
 * 如果在此次点击中，若无更多方块可被揭露，则返回盘面。
 *
 *
 * 示例 1：
 *
 *
 * 输入：board = [["E","E","E","E","E"],["E","E","M","E","E"],["E","E","E","E","E"],["E","E","E","E","E"]], click = [3,0]
 * 输出：[["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]
 * 示例 2：
 *
 *
 * 输入：board = [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]], click = [1,2]
 * 输出：[["B","1","E","1","B"],["B","1","X","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]
 *
 *
 * 提示：
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 50
 * board[i][j] 为 'M'、'E'、'B' 或数字 '1' 到 '8' 中的一个
 * click.length == 2
 * 0 <= clickr < m
 * 0 <= clickc < n
 * board[clickr][clickc] 为 'M' 或 'E'
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 66,234/102.5K
 * 通过率
 * 64.6%
 */
public class LC529_updateBoard {
    private static final int[][] DIRS = {
            {0, -1}, {0, 1}, {-1, 0}, {1, 0},
            {-1, -1}, {1, 1}, {1, -1}, {-1, 1}
    };//定义八个方向
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;//摸到了地雷 结束游戏
        }else{
            //看隔壁是否又雷区
            //若隔壁有雷, 则更新当前格子为地雷数量
            //若隔壁无雷, 则继续DFS
            dfs(board, click[0], click[1]);
        }

        //顶多dfs了一次, 起点是click[0], click[1]
        return board;
    }

    private void dfs(char[][] board, int i, int j){

        //模拟, 搜索周围有没有地雷
        int cnt = 0;
        for(int[] dir: DIRS){
            int i0 = i + dir[0];
            int j0 = j + dir[1];
            if(i0 < 0 || i0 >= board.length || j0 < 0 || j0 >= board[0].length){
                continue;
            }
            if(board[i0][j0] == 'M'){
                cnt++;
            }
        }

        //隔壁有地雷, 结束游戏
        if(cnt > 0){
            board[i][j] = (char)(cnt + '0');
            return;
        }

        //当前安全是E, 改为B, 然后递归八个方向
        board[i][j] = 'B';
        for(int[] dir: DIRS){
            int i0 = i + dir[0];
            int j0 = j + dir[1];
            if(i0 < 0 || i0 >= board.length || j0 < 0 || j0 >= board[0].length){
                continue;//越界了continue, 但不能return, 避免其他的方向还没来得及标记
            }
            if(board[i0][j0] != 'E'){
                continue;//重复访问, 或者不可访问如数字, 则continue, 但不能return, 避免其他的方向还没来得及标记
            }
            dfs(board, i0, j0);
        }
    }
}

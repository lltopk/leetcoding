package org.lyflexi.solutions.strategy_retrieval_dfs.grid_chart.biz_prop;

/**
 * LCS 03. 主题空间
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 「以扣会友」线下活动所在场地由若干主题空间与走廊组成，场地的地图记作由一维字符串型数组 grid，字符串中仅包含 "0"～"5" 这 6 个字符。地图上每一个字符代表面积为 1 的区域，其中 "0" 表示走廊，其他字符表示主题空间。相同且连续（连续指上、下、左、右四个方向连接）的字符组成同一个主题空间。
 *
 * 假如整个 grid 区域的外侧均为走廊。请问，不与走廊直接相邻的主题空间的最大面积是多少？如果不存在这样的空间请返回 0。
 *
 * 示例 1：
 *
 * 输入：grid = ["110","231","221"]
 *
 * 输出：1
 *
 * 解释：4 个主题空间中，只有 1 个不与走廊相邻，面积为 1。image.png
 *
 * 示例 2：
 *
 * 输入：grid = ["11111100000","21243101111","21224101221","11111101111"]
 *
 * 输出：3
 *
 * 解释：8 个主题空间中，有 5 个不与走廊相邻，面积分别为 3、1、1、1、2，最大面积为 3。image.png
 *
 * 提示：
 *
 * 1 <= grid.length <= 500
 * 1 <= grid[i].length <= 500
 * grid[i][j] 仅可能为 "0"～"5"
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 11,652/25.3K
 * 通过率
 * 46.0%
 */
public class LCS_03_largestArea {
    private static final int[][] DIRS = {
            { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 }
    };

    int ans = 0;
    boolean touch; // 当前主题空间是否接触走廊

    public int largestArea(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();

        char[][] g = new char[m][n];
        for (int i = 0; i < m; i++) {
            g[i] = grid[i].toCharArray();
        }
        //由于要判断走廊0， 所以必须创建boolean[][] vis， 不能复用char[][] g来标记
        //复用char[][] g来标记访问， 会造成“假”走廊
        boolean[][] vis = new boolean[g.length][g[0].length];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] != '0') {
                    touch = false;
                    char color = g[i][j];
                    int area = dfs(g, vis, i, j, color);
                    if (!touch) {
                        ans = Math.max(ans, area);
                    }
                }
            }
        }

        return ans;
    }

    private int dfs(char[][] g, boolean[][] vis, int i, int j, char color) {

        // 越界
        if (i < 0 || i >= g.length || j < 0 || j >= g[0].length) {
            touch = true;
            return 0;
        }

        // 走廊
        if (g[i][j] == '0') {
            touch = true;
            return 0;
        }

        // 不属于当前主题空间
        if (g[i][j] != color) {
            return 0;
        }

        // 已访问
        if (vis[i][j]) {
            return 0;
        }

        vis[i][j] = true;

        int area = 1;
        for (int[] d : DIRS) {
            area += dfs(g, vis, i + d[0], j + d[1], color);
        }

        return area;
    }
}

package org.lyflexi.solutions.strategy_retrieval_dfs.back_track.combine;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 示例 2：
 *
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *
 *
 * 提示：
 *
 * 1 <= n <= 20
 * 1 <= k <= n
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,027,668/1.3M
 * 通过率
 * 77.5%
 */
public class LC77_combine {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        dfs(n, k, ret, new ArrayList<>(), 1);
        return ret;
    }

    /**
     * 枚举思路（答案视角）
     * @param n
     * @param k
     * @param ret
     * @param path
     * @param i
     */
    private void dfs(int n, int k, List<List<Integer>> ret, List<Integer> path, int i){
        if (k == 0){
            ret.add(new ArrayList<>(path));
            return;
        }

        //从i开始往后， 避免重复组合
        for(int j = i; j<=n; j++){
            path.add(j);
            dfs(n, k - 1, ret, path, j+1);
            path.remove(path.size() - 1);
        }
    }
}

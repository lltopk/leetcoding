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
public class LC77_combine4 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        dfs(k, ret, new ArrayList<>(), n);
        return ret;
    }

    /**
     * 选与不选思路（输入视角）
     *
     * 由于答案与顺序无关， 我们还可以反向递归， 让i从n开始， 这样递归函数可以少接收1个变量
     */
    private void dfs(int k, List<List<Integer>> ret, List<Integer> path, int i){
        if (k == 0){
            ret.add(new ArrayList<>(path));
            return;
        }
        //剪枝优化， 剩余数字不够选剩余的K个
        if (k > i) {
            return;
        }
        dfs(k, ret, path, i - 1);

        path.add(i);
        dfs(k - 1, ret, path, i - 1);
        path.remove(path.size() - 1);
    }
}

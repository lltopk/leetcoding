package org.lyflexi.solutions.strategy_retrieval_dfs.back_track.combine;

import java.util.ArrayList;
import java.util.List;

/**
 * 39. 组合总和
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 *
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 *
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *
 *
 *
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * 示例 2：
 *
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 *
 * 输入: candidates = [2], target = 1
 * 输出: []
 *
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * candidates 的所有元素 互不相同
 * 1 <= target <= 40
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,516,323/2M
 * 通过率
 * 74.1%
 */
public class LC39_combinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        dfs(candidates, target, ret, new ArrayList<>(), 0);
        return ret;
    }

    /**
     * 选与不选思路（输入视角）
     * @param n
     * @param k
     * @param ret
     * @param path
     * @param i
     */
    private void dfs(int[] candidates, int target, List<List<Integer>> ret, List<Integer> path, int i){
        if (target == 0){
            ret.add(new ArrayList<>(path));
            return;
        }
        if(i == candidates.length || target < 0){
            return;
        }
        dfs(candidates, target, ret, path, i + 1);

        path.add(candidates[i]);
        //这题允许重复选， 因此下次dfs无需i+1
        dfs(candidates, target - candidates[i], ret, path, i);
        path.remove(path.size() - 1);
    }
}

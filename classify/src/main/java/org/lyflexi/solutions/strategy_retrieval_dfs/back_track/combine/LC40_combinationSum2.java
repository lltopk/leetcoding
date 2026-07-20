package org.lyflexi.solutions.strategy_retrieval_dfs.back_track.combine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. 组合总和 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 *
 * 注意：解集不能包含重复的组合。
 *
 *
 *
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 *
 * 提示:
 *
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 703,522/1.2M
 * 通过率
 * 60.1%
 */
public class LC40_combinationSum2 {
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //这道题candidates存在重复元素， 而结果不能出现重复组合
        Arrays.sort(candidates);
        dfs(candidates, target, new ArrayList<>(), 0);
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
    private void dfs(int[] candidates, int target, List<Integer> path, int i){
        if (target == 0){
            ret.add(new ArrayList<>(path));
            return;
        }
        //没有可选的数字了
        if(i == candidates.length){
            return;
        }
        //所选元素之和无法恰好等于 target
        if(target < candidates[i]){
            return;
        }

        int x = candidates[i];
        path.add(candidates[i]);
        dfs(candidates, target - candidates[i], path, i+1);
        path.remove(path.size() - 1);

        i++;
        //排序后了， 所以不选的时候要先跳过选的所有x
        while(i < candidates.length && candidates[i] == x){
            i++;
        }
        //不选, 注意此时由于上面的while循环， i恰好变成了i+1, 所以不选的时候传i即可了
        dfs(candidates, target, path, i);
    }
}

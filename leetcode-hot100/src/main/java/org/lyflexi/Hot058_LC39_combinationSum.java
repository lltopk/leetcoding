package org.lyflexi;

import java.util.*;

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
 * 示例 1：
 * 
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * 
 * 示例 2：
 * 
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 
 * 示例 3：
 * 
 * 输入: candidates = [2], target = 1
 * 输出: []
 * 
 * 提示：
 * 
 * - 1 <= candidates.length <= 30
 * 
 * - 2 <= candidates[i] <= 40
 * 
 * - candidates 的所有元素 互不相同
 * 
 * - 1 <= target <= 40
 */

/**
 * 方法一：选或不选
 * 
 * 前置题目：78. 子集
 * 
 * 视频讲解：回溯算法套路①子集型回溯【基础算法精讲 14】
 * 
 * 用 $dfs(i,left)$ 来回溯，设当前枚举到 $candidates[i]$，剩余要选的元素之和为 $left$，按照选或不选分类讨论：
 * 
 * - 不选 $candidates[i]$：递归到 $dfs(i+1,left)$。
 * - 选 $candidates[i]$：递归到 $dfs(i,left-candidates[i])$。注意 $i$ 不变，表示在下次递归中可以继续选 $candidates[i]$。
 * 
 * 注：这个思路类似 完全背包。
 * 
 * 如果递归中发现 $left=0$ 则说明找到了一个合法组合，复制一份 $path$ 加入答案。
 * 
 * 递归边界：如果 $i=n$ 或者 $left < 0$ 则返回。
 * 
 * 递归入口：$dfs(0, target)$。
 */
public class Hot058_LC39_combinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(0, target, candidates, ans, path);
        return ans;
    }

    private void dfs(int i, int left, int[] candidates, List<List<Integer>> ans, List<Integer> path) {
        if (left == 0) {
            // 找到一个合法组合
            ans.add(new ArrayList<>(path));
            return;
        }

        if (i == candidates.length || left < 0) {
            return;
        }

        // 不选
        dfs(i + 1, left, candidates, ans, path);

        // 选
        path.add(candidates[i]);
        dfs(i, left - candidates[i], candidates, ans, path);
        path.remove(path.size() - 1); // 恢复现场
    }
}

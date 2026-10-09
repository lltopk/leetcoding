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
 * 方法二：枚举选哪个
 * 
 * 类似 视频 中的「答案视角」。同样用 $dfs(i,left)$ 来回溯，设当前枚举到 $candidates[i]$，剩余要选的元素之和为 $left$，考虑枚举下个元素是谁：
 * 
 * - 在 $[i,n-1]$ 中枚举要填在 $path$ 中的元素 $candidates[j]$，然后递归到 $dfs(j, left - candidates[j])$。注意这里是递归到 $j$ 不是 $j+1$，表示 $candidates[j]$ 可以重复选取。
 */
public class Hot058_LC39_combinationSum3 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
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

        // 枚举选哪个
        for (int j = i; j < candidates.length && candidates[j] <= left; j++) {
            path.add(candidates[j]);
            dfs(j, left - candidates[j], candidates, ans, path);
            path.remove(path.size() - 1); // 恢复现场
        }
    }
}

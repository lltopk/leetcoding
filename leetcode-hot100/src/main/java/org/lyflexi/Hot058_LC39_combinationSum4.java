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
 * 方法三：完全背包预处理 + 可行性剪枝
 * 
 * 前置知识：完全背包。
 * 
 * 例如 $candidates=[2,4,6,8,10]$ 都是偶数，但 $target=11$ 是奇数，这种情况我们在一开始递归时，就应当判断出无解，不再继续向下递归。
 * 
 * 怎么判断？我们可以用完全背包预处理出下标在 $[0,i]$ 中的 $candidates$ 元素之和能否为 $j$，记作 $f[i+1][j]$。
 * 
 * 如果递归中的 $left$ 不在可以组合得到的数字中，则可以直接返回。
 * 
 * 这一做法可以保证我们是在往正确的方向一步步递归前进的。只要题目保证方案数不超过 $150$，即使 $target=1000$ 也能搞定。
 */
public class Hot058_LC39_combinationSum4 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int n = candidates.length;
        // 完全背包
        boolean[][] f = new boolean[n + 1][target + 1];
        f[0][0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                f[i + 1][j] = f[i][j] || j >= candidates[i] && f[i + 1][j - candidates[i]];
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        // 倒着递归，这样参数符合 f 数组的定义
        dfs(n - 1, target, candidates, f, ans, path);
        return ans;
    }

    private void dfs(int i, int left, int[] candidates, boolean[][] f, List<List<Integer>> ans, List<Integer> path) {
        if (left == 0) {
            // 找到一个合法组合
            ans.add(new ArrayList<>(path));
            return;
        }

        // 无法用下标在 [0, i] 中的数字组合出 left
        if (left < 0 || !f[i + 1][left]) {
            return;
        }

        // 不选
        dfs(i - 1, left, candidates, f, ans, path);

        // 选
        path.add(candidates[i]);
        dfs(i, left - candidates[i], candidates, f, ans, path);
        path.remove(path.size() - 1);
    }
}

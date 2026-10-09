package org.lyflexi;

import java.util.*;

/**
 * 46. 全排列
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 
 * 示例 2：
 * 
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 
 * 示例 3：
 * 
 * 输入：nums = [1]
 * 输出：[[1]]
 * 
 * 提示：
 * 
 * - 1 <= nums.length <= 6
 * 
 * - -10 <= nums[i] <= 10
 * 
 * - nums 中的所有整数 互不相同
 */

/**
 * 视频讲解
 * 
 * 请看 回溯算法套路③排列型回溯【基础算法精讲 16】。制作不易，欢迎点赞~
 */
public class Hot055_LC46_permute {
    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        List<Integer> path = Arrays.asList(new Integer[n]); // 所有排列的长度都是一样的 n
        boolean[] onPath = new boolean[n];
        List<List<Integer>> ans = new ArrayList<>();

        dfs(0, nums, ans, path, onPath);
        return ans;
    }

    // 枚举 path[i] 填 nums 的哪个数
    private void dfs(int i, int[] nums, List<List<Integer>> ans, List<Integer> path, boolean[] onPath) {
        if (i == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int j = 0; j < nums.length; j++) {
            if (!onPath[j]) {
                path.set(i, nums[j]); // 从没有选的数字中选一个
                onPath[j] = true; // 已选上
                dfs(i + 1, nums, ans, path, onPath);
                onPath[j] = false; // 恢复现场
                // 注意 path 无需恢复现场，因为排列长度固定，直接覆盖就行
            }
        }
    }
}

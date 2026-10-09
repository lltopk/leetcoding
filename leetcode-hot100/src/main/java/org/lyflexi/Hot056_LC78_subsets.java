package org.lyflexi;

import java.util.*;

/**
 * 78. 子集
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 
 * 示例 2：
 * 
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * 
 * 提示：
 * 
 * - 1 <= nums.length <= 10
 * 
 * - -10 <= nums[i] <= 10
 * 
 * - nums 中的所有元素 互不相同
 */

/**
 * 答疑
 * 
 * 问：为什么要恢复现场？
 * 
 * 答：我们来做个实验。去掉代码中的恢复现场那行代码，然后测试 $nums=[1,2]$ 这个数据。你会发现答案居然包含 $[2,1,2]$，这是为什么呢？
 * 
 * 看视频中的图。如果不恢复现场，当我们从 $[2]$ 递归返回后，$path$ 中还残留有 $2$，对于后面的递归来说，这个 $2$ 是多余的。继续递归「选 $1$」的右子树时，会把 $1$ 加到 $path$ 中，导致 $path = [2,1]$；继续递归到「选 $2$」的右子树时，$path = [2,1,2]$，显然这是错的。
 */
public class Hot056_LC78_subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(0, nums, path, ans);
        return ans;
    }

    // 选或不选：讨论 nums[i] 是否加入 path
    private void dfs(int i, int[] nums, List<Integer> path, List<List<Integer>> ans) {
        if (i == nums.length) { // 子集构造完毕
            ans.add(new ArrayList<>(path)); // 复制 path
            return;
        }

        // 不选 nums[i]
        dfs(i + 1, nums, path, ans); // 考虑下一个数 nums[i+1] 选或不选

        // 选 nums[i]
        path.add(nums[i]);
        dfs(i + 1, nums, path, ans); // 考虑下一个数 nums[i+1] 选或不选
        path.remove(path.size() - 1); // path.remove(path.size() - 1);
    }
}

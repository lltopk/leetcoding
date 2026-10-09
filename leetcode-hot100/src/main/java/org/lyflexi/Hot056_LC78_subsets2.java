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
 * 问：代码的递归边界是什么？为什么没有判断 i == n？
 * 
 * 答：当 $i=n$ 时，代码不会进入循环，更不会往下递归。此时 $dfs(i)$ 只执行了一个逻辑：把 $path$ 的拷贝添加到 $path$ 中。
 */
public class Hot056_LC78_subsets2 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(0, nums, path, ans);
        return ans;
    }

    // 枚举选哪个：在下标 i 到 n-1 中选一个数，加到 path 末尾
    private void dfs(int i, int[] nums, List<Integer> path, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(path)); // 不选，把当前子集加入答案
        for (int j = i; j < nums.length; j++) { // 选，枚举选择的数字
            path.add(nums[j]);
            dfs(j + 1, nums, path, ans); // 选 nums[j] 意味着 i 到 j-1 都跳过不选，下一个数从 j+1 开始选
            path.remove(path.size() - 1); // path.remove(path.size() - 1);
        }
    }
}

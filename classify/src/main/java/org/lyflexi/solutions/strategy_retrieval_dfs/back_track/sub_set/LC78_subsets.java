package org.lyflexi.solutions.strategy_retrieval_dfs.back_track.sub_set;

/**
 * 78. 子集
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,314,288/1.6M
 * 通过率
 * 81.9%
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯, 选与不选
 */
public class LC78_subsets {
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, new ArrayList<>(), 0);
        return ret;
    }

    private void dfs(int[] nums, List<Integer> path, int i){
        if(i == nums.length){
            ret.add(new ArrayList<>(path));
            return;
        }

        //不选
        dfs(nums, path, i+1);
        //选
        path.add(nums[i]);
        dfs(nums, path, i+1);
        path.remove(path.size() - 1);
    }
}

package org.lyflexi;

import java.util.*;

/**
 * 416. 分割等和子集
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 
 * 示例 2：
 * 
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 * 
 * 提示：
 * 
 * - 1 <= nums.length <= 200
 * 
 * - 1 <= nums[i] <= 100
 */

/**
 * 写法二
 * 
 * 简化了 $dfs$ 的返回逻辑，方便下面翻译成递推。
 */
public class Hot089_LC416_canPartition2 {
    public boolean canPartition(int[] nums) {
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        if (s % 2 != 0) {
            return false;
        }

        int n = nums.length;
        int[][] memo = new int[n][s / 2 + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }

        return dfs(n - 1, s / 2, nums, memo);
    }

    private boolean dfs(int i, int j, int[] nums, int[][] memo) {
        if (i < 0) {
            return j == 0;
        }
        if (memo[i][j] != -1) { // 之前计算过
            return memo[i][j] == 1;
        }

        boolean res = j >= nums[i] && dfs(i - 1, j - nums[i], nums, memo) || dfs(i - 1, j, nums, memo);
        memo[i][j] = res ? 1 : 0; // 记忆化
        return res;
    }
}

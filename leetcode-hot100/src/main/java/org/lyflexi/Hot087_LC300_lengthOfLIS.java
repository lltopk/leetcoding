package org.lyflexi;

import java.util.*;

/**
 * 300. 最长递增子序列
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * 
 * 示例 1：
 * 
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 
 * 示例 2：
 * 
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 
 * 示例 3：
 * 
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 * 
 * 提示：
 * 
 * - 1 <= nums.length <= 2500
 * 
 * - -10^4 <= nums[i] <= 10^4
 * 
 * 进阶：
 * 
 * - 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 */

/**
 * 答疑
 * 
 * 问：什么样的题目适合「选或不选」，什么样的题目适合「枚举选哪个」？
 * 
 * 答：我分成两类问题：
 * 
 * - 相邻无关子序列问题（比如 0-1 背包），适合「选或不选」。每个元素互相独立，只需依次考虑每个元素选或不选。
 * - 相邻相关子序列问题（比如本题），适合「枚举选哪个」。我们需要知道子序列中的相邻两个数的关系。对于本题来说，枚举 $nums[i]$ 必选，然后枚举前一个必选的数，方便比大小。如果硬要用「选或不选」，需要额外记录上一个选的数的下标，算法总体的空间复杂度为 $O(n^2)$，而枚举选哪个只需要 $O(n)$ 的空间。
 */
public class Hot087_LC300_lengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n]; // 本题可以初始化成 0，表示没有计算过
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i, nums, memo));
        }
        return ans;
    }

    private int dfs(int i, int[] nums, int[] memo) {
        if (memo[i] > 0) { // 之前计算过
            return memo[i];
        }
        int res = 0;
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                res = Math.max(res, dfs(j, nums, memo));
            }
        }
        res++; // 加一提到循环外面
        return memo[i] = res;
    }
}

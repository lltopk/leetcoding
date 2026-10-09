package org.lyflexi;

import java.util.*;

/**
 * 198. 打家劫舍
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 
 * 示例 1：
 * 
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 
 * 示例 2：
 * 
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * 
 * 提示：
 * 
 * - 1 <= nums.length <= 100
 * 
 * - 0 <= nums[i] <= 400
 */

/**
 * 一、递归搜索 + 保存计算结果 = 记忆化搜索
 */
public class Hot083_LC198_rob {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1); // -1 表示没有计算过
        return dfs(n - 1, nums, memo); // 从最后一个房子开始思考
    }

    // dfs(i) 表示从 nums[0] 到 nums[i] 最多能偷多少
    private int dfs(int i, int[] nums, int[] memo) {
        if (i < 0) { // 递归边界（没有房子）
            return 0;
        }
        if (memo[i] != -1) { // 之前计算过
            return memo[i];
        }
        int notChoose = dfs(i - 1, nums, memo);
        int choose = dfs(i - 2, nums, memo) + nums[i];
        return memo[i] = Math.max(notChoose, choose); // 返回答案并记忆化
    }
}

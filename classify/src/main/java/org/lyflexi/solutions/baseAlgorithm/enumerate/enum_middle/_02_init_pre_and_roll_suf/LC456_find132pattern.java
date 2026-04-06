package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_middle._02_init_pre_and_roll_suf;

/**
 * 456. 132 模式
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 *
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 * 示例 2：
 *
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 * 示例 3：
 *
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 2 * 105
 * -109 <= nums[i] <= 109
 */

import java.util.TreeSet;

/**
 * 三变量 枚举中间
 */
public class LC456_find132pattern {
    /**
     * 预初始化前缀状态集 滚动计算后缀辅助
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        int n = nums.length;

        int[] preMin = new int[n];//前缀最小值, 是个状态数组, 其索引小于等于当前枚举j
        preMin[0] = nums[0];
        for(int j = 1; j<=n-3; j++){
            preMin[j] = Math.min(nums[j], preMin[j-1]);
        }

        //红黑树, 滚动计算后缀是否存在小于j的值
        TreeSet<Integer> rbt = new TreeSet<>();
        rbt.add(nums[n-1]);
        for(int j = n-2; j>=1; j--){

            int min = preMin[j-1];

            Integer middle = rbt.lower(nums[j]);

            //1. middle!=null证明k<j
            //2. middle>mid证明k>i
            if(middle!=null && middle>min){
                return true;
            }

            //维护红黑树
            rbt.add(nums[j]);
        }

        return false;
    }
}

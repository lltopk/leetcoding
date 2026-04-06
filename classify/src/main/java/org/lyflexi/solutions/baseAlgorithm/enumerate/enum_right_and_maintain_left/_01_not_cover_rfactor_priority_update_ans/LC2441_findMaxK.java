package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_right_and_maintain_left._01_not_cover_rfactor_priority_update_ans;

/**
 * 2441. 与对应负数同时存在的最大正整数
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个 不包含 任何零的整数数组 nums ，找出自身与对应的负数都在数组中存在的最大正整数 k 。
 *
 * 返回正整数 k ，如果不存在这样的整数，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-1,2,-3,3]
 * 输出：3
 * 解释：3 是数组中唯一一个满足题目要求的 k 。
 * 示例 2：
 *
 * 输入：nums = [-1,10,6,7,-7,1]
 * 输出：7
 * 解释：数组中存在 1 和 7 对应的负数，7 的值更大。
 * 示例 3：
 *
 * 输入：nums = [-10,8,6,7,-2,-3]
 * 输出：-1
 * 解释：不存在满足题目要求的 k ，返回 -1 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * nums[i] != 0
 */

import java.util.HashSet;
import java.util.Set;

/**
 * 枚举技巧: 枚举右维护左, 将双变量问题转化为单变量问题
 */
public class LC2441_findMaxK {
    public int findMaxK(int[] nums) {
        int n  = nums.length, ans = -1;
        //记录是否存在, 用HashSet就够了, 不必用HashMap
        Set<Integer> set = new HashSet<>();
        for(int r = 0; r<n; r++){
            if(set.contains(-nums[r])){
                ans = Math.max(Math.abs(nums[r]), ans);
            }
            set.add(nums[r]);
        }
        return ans;
    }
}

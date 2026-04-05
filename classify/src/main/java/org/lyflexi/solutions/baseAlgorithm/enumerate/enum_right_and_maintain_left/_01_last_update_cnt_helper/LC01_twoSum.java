package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_right_and_maintain_left._01_last_update_cnt_helper;

/**
 * 1. 两数之和
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
 *
 * 你可以按任意顺序返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 *
 * 提示：
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 *
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举技巧: 枚举右维护左
 */
public class LC01_twoSum {
    public int[] twoSum(int[] nums, int target) {
        //枚举技巧: 双变量问题, 枚举右维护左, 进而转变成单变量问题target - nums[r]
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for(int r = 0; r < n; r++){
            if(map.get(target - nums[r]) != null){
                return new int[]{map.get(target - nums[r]), r};
            }
            map.put(nums[r], r);
        }

        return new int[]{-1,-1};
    }
}

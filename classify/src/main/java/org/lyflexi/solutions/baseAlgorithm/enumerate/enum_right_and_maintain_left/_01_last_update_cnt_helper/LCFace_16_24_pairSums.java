package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_right_and_maintain_left._01_last_update_cnt_helper;

/**
 * 面试题 16.24. 数对和
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。
 *
 * 示例 1：
 *
 * 输入：nums = [5,6,5], target = 11
 * 输出：[[5,6]]
 * 示例 2：
 *
 * 输入：nums = [5,6,5,6], target = 11
 * 输出：[[5,6],[5,6]]
 * 提示：
 *
 * nums.length <= 100000
 * -105 <= nums[i], target <= 105
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 枚举技巧： 枚举右维护左
 */
public class LCFace_16_24_pairSums {
    public List<List<Integer>> pairSums(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            if (map.containsKey(target - nums[r])) {
                if (map.merge(target - nums[r], -1, Integer::sum) == 0) {
                    map.remove(target - nums[r]);
                }
                List<Integer> list = new ArrayList<>();
                list.add(target - nums[r]);
                list.add(nums[r]);
                ans.add(list);
            } //一次性拿走两个元素, 所以下面要加else分治
            else {
                map.merge(nums[r], 1, Integer::sum);
            }

        }

        return ans;
    }
}

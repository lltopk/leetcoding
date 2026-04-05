package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_right_and_maintain_left._01_last_update_cnt_helper;

/**
 * 1512. 好数对的数目
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums 。
 *
 * 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
 *
 * 返回好数对的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1,1,3]
 * 输出：4
 * 解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
 * 示例 2：
 *
 * 输入：nums = [1,1,1,1]
 * 输出：6
 * 解释：数组中的每组数字都是好数对
 * 示例 3：
 *
 * 输入：nums = [1,2,3]
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举技巧: 枚举右维护左
 */
public class LC1512_numIdenticalPairs {
    public int numIdenticalPairs(int[] nums) {
        int ans = 0, n = nums.length;
        //记录元素出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        for(int r = 0; r<n; r++){
            if(!(map.get(nums[r]) == null)){
                //好数对的递加量, 等于元素过去出现的次数
                ans+=map.get(nums[r]);
            }
            map.merge(nums[r], 1, Integer::sum);
        }

        return ans;
    }
}

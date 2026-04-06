package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_right_and_maintain_left._01_not_cover_rfactor_priority_update_ans;

/**
 * 3371. 识别数组中的最大异常值
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums。该数组包含 n 个元素，其中 恰好 有 n - 2 个元素是 特殊数字 。剩下的 两个 元素中，一个是所有 特殊数字 的 和 ，另一个是 异常值 。
 *
 * 异常值 的定义是：既不是原始特殊数字之一，也不是表示元素和的那个数。
 *
 * 注意，特殊数字、和 以及 异常值 的下标必须 不同 ，但可以共享 相同 的值。
 *
 * 返回 nums 中可能的 最大异常值。
 *
 *
 *
 * 示例 1：
 *
 * 输入： nums = [2,3,5,10]
 *
 * 输出： 10
 *
 * 解释：
 *
 * 特殊数字可以是 2 和 3，因此和为 5，异常值为 10。
 *
 * 示例 2：
 *
 * 输入： nums = [-2,-1,-3,-6,4]
 *
 * 输出： 4
 *
 * 解释：
 *
 * 特殊数字可以是 -2、-1 和 -3，因此和为 -6，异常值为 4。
 *
 * 示例 3：
 *
 * 输入： nums = [1,1,1,1,1,5,5]
 *
 * 输出： 5
 *
 * 解释：
 *
 * 特殊数字可以是 1、1、1、1 和 1，因此和为 5，另一个 5 为异常值。
 *
 *
 *
 * 提示：
 *
 * 3 <= nums.length <= 105
 * -1000 <= nums[i] <= 1000
 * 输入保证 nums 中至少存在 一个 可能的异常值。
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举技巧: 枚举右, (提前初始化所有的左)
 */
public class LC3371_getLargestOutlier {
    public int getLargestOutlier(int[] nums) {
        //用整体思维进行抽象, 设特殊数字和为x, 异常值为y, 数组和为s
        //则有等式 2*x + y = s
        //此时题目等价于, 两数之和求y
        Map<Integer, Integer> dict = new HashMap<>();
        int ans = Integer.MIN_VALUE, n = nums.length;
        int s = 0;
        for(int i = 0; i<n; i++){
            s+=nums[i];
            dict.merge(nums[i], 1 ,Integer::sum);
        }
        //枚举异常值y
        for(int r = 0; r< n; r++){
            int y = nums[r];
            int x = (s - y)/2;//可能的x
            if((s - y) % 2 == 0 && dict.containsKey(x)//存在x需要同时满足(s - y) % 2 == 0 && dict.containsKey(x)
                    //[166,-688, 166]: 存在x, 并且y!=x
                    //[1,1,1,1,1,5,5]: 存在x, 并且y==x但数组存在两个y
                    //[5,5,5]: 存在x, 并且y==x但数组存在三个y
                    && ((y != x) || dict.get(nums[r]) == 2 ||  dict.get(nums[r]) == 3)){
                ans = Math.max(ans, nums[r]);
            }

        }

        return ans;
    }
}

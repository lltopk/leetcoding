package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_right_and_maintain_left._01_last_update_cnt_helper;

/**
 *
 代码
 测试用例
 测试用例
 测试结果
 1814. 统计一个数组中好对子的数目
 已解答
 中等
 相关标签
 premium lock icon
 相关企业
 提示
 给你一个数组 nums ，数组中只包含非负整数。定义 rev(x) 的值为将整数 x 各个数字位反转得到的结果。比方说 rev(123) = 321 ， rev(120) = 21 。我们称满足下面条件的下标对 (i, j) 是 好的 ：

 0 <= i < j < nums.length
 nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
 请你返回好下标对的数目。由于结果可能会很大，请将结果对 109 + 7 取余 后返回。



 示例 1：

 输入：nums = [42,11,1,97]
 输出：2
 解释：两个坐标对为：
 - (0,3)：42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121 。
 - (1,2)：11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12 。
 示例 2：

 输入：nums = [13,10,35,24,76]
 输出：4


 提示：

 1 <= nums.length <= 105
 0 <= nums[i] <= 109
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举右, 维护左
 */
public class LC1814_countNicePairs {
    public int countNicePairs(int[] nums) {
        int n = nums.length, ans = 0, MOD = 1_000_000__007;
        Map<Integer, Integer> helper = new HashMap<>();//好数计数
        for(int r = 0; r< n; r++){
            int rev = 0, tmp = nums[r];
            while(tmp>0){
                rev*=10;
                rev+=tmp%10;
                tmp/=10;
            }
            int goodSum = nums[r] - rev;
            ans = (ans + helper.getOrDefault(goodSum, 0)) % MOD;
            helper.merge(goodSum, 1, Integer::sum);
        }

        return ans%MOD;
    }
}

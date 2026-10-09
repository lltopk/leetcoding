package org.lyflexi;

import java.util.*;

/**
 * 15. 三数之和
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 
 * 注意：答案中不可以包含重复的三元组。
 * 
 * 示例 1：
 * 
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * 
 * 示例 2：
 * 
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * 
 * 示例 3：
 * 
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 * 
 * 提示：
 * 
 * - 3 <= nums.length <= 3000
 * 
 * - -10^5 <= nums[i] <= 10^5
 */

/**
 * 写法二
 * 
 * 上面跳过重复数字的循环，也可以这样写：如果 $nums[i] + nums[j] + nums[k] = 0$，并且 $j>i+1$ 且 $nums[j] = nums[j-1]$，则说明我们在之前找到了一样的三元组。
 * 
 * 比如 $nums=[-3,1,1,2,2]$，首先我们找到 $nums[0] + nums[1] + nums[4] = 0$，在 $j$ 加一，$k$ 减一后，发现 $nums[0] + nums[2] + nums[3]$ 也等于 $0$，但 $nums[2] = nums[1]$（这同时说明 $nums[3] = nums[4]$），是重复的三元组，不能加入答案。
 */
public class Hot006_LC15_threeSum2 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            int x = nums[i];
            if (i > 0 && x == nums[i - 1]) continue; // 跳过重复数字
            if (x + nums[i + 1] + nums[i + 2] > 0) break; // 优化一
            if (x + nums[n - 2] + nums[n - 1] < 0) continue; // 优化二
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int s = x + nums[j] + nums[k];
                if (s > 0) {
                    k--;
                } else if (s < 0) {
                    j++;
                } else { // 三数之和为 0
                    // j = i+1 表示刚开始双指针，此时 j 左边没有数字
                    // nums[j] != nums[j-1] 说明与上一轮循环的三元组不同
                    if (j == i + 1 || nums[j] != nums[j - 1]) {
                        ans.add(Arrays.asList(x, nums[j], nums[k]));
                    }
                    j++;
                    k--;
                }
            }
        }
        return ans;
    }
}

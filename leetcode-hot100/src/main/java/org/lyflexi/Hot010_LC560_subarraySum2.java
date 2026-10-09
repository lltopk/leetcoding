package org.lyflexi;

import java.util.*;

/**
 * 560. 和为 K 的子数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 
 * 子数组是数组中元素的连续非空序列。
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 
 * 示例 2：
 * 
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * 
 * 提示：
 * 
 * - 1 <= nums.length <= 2 * 10^4
 * 
 * - -1000 <= nums[i] <= 1000
 * 
 * - -10^7 <= k <= 10^7
 */

/**
 * 写法二：一次遍历 · 其一
 * 
 * 我们可以一边计算前缀和，一边遍历前缀和。
 * 
 * 在遍历 $nums$ 之前，我们需要先统计 $s[0]=0$，即空前缀的元素和等于 $0$。往 $cnt$ 中添加 $cnt[0]=1$。
 * 
 * 对比一下，两次遍历的代码循环了 $n+1$ 次，下面的代码循环了 $n$ 次，少的那一次是什么？就是对 $s[0]=0$ 的统计。
 */
public class Hot010_LC560_subarraySum2 {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>(nums.length + 1, 1); // 预分配空间
        cnt.put(0, 1); // s[0]=0 单独统计
        int s = 0;
        int ans = 0;
        for (int x : nums) {
            s += x;
            ans += cnt.getOrDefault(s - k, 0);
            cnt.merge(s, 1, Integer::sum); // cnt[s]++
        }
        return ans;
    }
}

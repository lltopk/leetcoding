package org.lyflexi;

import java.util.*;

/**
 * 300. 最长递增子序列
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * 
 * 示例 1：
 * 
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 
 * 示例 2：
 * 
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 
 * 示例 3：
 * 
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 * 
 * 提示：
 * 
 * - 1 <= nums.length <= 2500
 * 
 * - -10^4 <= nums[i] <= 10^4
 * 
 * 进阶：
 * 
 * - 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 */

/**
 * 附：返回一个具体的 LIS
 */
public class Hot087_LC300_lengthOfLIS5 {
    public List<Integer> findOneLis(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return Collections.emptyList();
        }

        List<int[]> g = new ArrayList<>(); // 不仅记录值，还记录值对应的下标
        int[] last = new int[n];
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int j = lowerBound(g, x);
            if (j > 0) {
                last[i] = g.get(j - 1)[1]; // 记录 nums[i] 添加到了哪个数的末尾
            }
            if (j < g.size()) {
                g.set(j, new int[]{x, i}); // 额外保存下标
            } else {
                g.add(new int[]{x, i});
            }
        }

        List<Integer> lis = new ArrayList<>();
        // LIS 的最后一个数是 nums[g.get(g.size() - 1)[1]]，顺着 last 倒着找上一个数
        for (int i = g.get(g.size() - 1)[1]; i >= 0; i = last[i]) {
            lis.add(nums[i]);
        }
        Collections.reverse(lis);
        return lis;
    }

    private int lowerBound(List<int[]> g, int target) {
        int left = -1;
        int right = g.size(); // 开区间 (left, right)
        while (left + 1 < right) { // 开区间不为空
            // 循环不变量：
            // g[right][0] >= target
            // g[left][0] < target
            int mid = left + (right - left) / 2;
            if (g.get(mid)[0] >= target) {
                right = mid; // 范围缩小到 (left, mid)
            } else {
                left = mid; // 范围缩小到 (mid, right)
            }
        }
        return right;
    }
}

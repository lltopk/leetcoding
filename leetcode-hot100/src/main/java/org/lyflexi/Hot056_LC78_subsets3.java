package org.lyflexi;

import java.util.*;

/**
 * 78. 子集
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 
 * 示例 2：
 * 
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * 
 * 提示：
 * 
 * - 1 <= nums.length <= 10
 * 
 * - -10 <= nums[i] <= 10
 * 
 * - nums 中的所有元素 互不相同
 */

/**
 * 方法三：二进制枚举
 * 
 * 根据 从集合论到位运算，常见位运算技巧分类总结 中的「枚举子集」的技巧，可以只用简单的循环枚举所有子集。
 */
public class Hot056_LC78_subsets3 {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>(1 << n); // 预分配空间
        for (int i = 0; i < (1 << n); i++) { // 枚举全集 U 的所有子集 i
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) { // j 在集合 i 中
                    subset.add(nums[j]);
                }
            }
            ans.add(subset);
        }
        return ans;
    }
}

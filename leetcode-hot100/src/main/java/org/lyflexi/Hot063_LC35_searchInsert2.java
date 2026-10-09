package org.lyflexi;

import java.util.*;

/**
 * 35. 搜索插入位置
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * 
 * 示例 1:
 * 
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 
 * 示例 2:
 * 
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 
 * 示例 3:
 * 
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * 
 * 提示:
 * 
 * - 1 <= nums.length <= 10^4
 * 
 * - -10^4 <= nums[i] <= 10^4
 * 
 * - nums 为 无重复元素 的 升序 排列数组
 * 
 * - -10^4 <= target <= 10^4
 */

/**
 * 库函数写法
 */
// 注意：只能在没有重复元素的时候使用
// 如果 nums 有多个值为 target 的数，返回值不一定是第一个 >= target 的数的下标
public class Hot063_LC35_searchInsert2 {
    public int searchInsert(int[] nums, int target) {
        int i = Arrays.binarySearch(nums, target);
        return i >= 0 ? i : ~i; // ~i = -i-1
    }
}

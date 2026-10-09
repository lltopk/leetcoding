package org.lyflexi;

import java.util.*;

/**
 * 33. 搜索旋转排序数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 下标 3 上向左旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * 
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 * 
 * 示例 1：
 * 
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 
 * 示例 2：
 * 
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 
 * 示例 3：
 * 
 * 输入：nums = [1], target = 0
 * 输出：-1
 * 
 * 提示：
 * 
 * - 1 <= nums.length <= 5000
 * 
 * - -10^4 <= nums[i] <= 10^4
 * 
 * - nums 中的每个值都 独一无二
 * 
 * - 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * 
 * - -10^4 <= target <= 10^4
 */

/**
 * 方法一：两次二分
 * 
 * 首先 153. 寻找旋转排序数组中的最小值，找到 $nums$ 的最小值的下标 $i$。
 * 
 * 根据旋转排序数组的定义，下标在 $[0,i-1]$ 中的元素都比下标在 $[i,n-1]$ 中的元素大（注意题目保证 $nums$ 没有重复元素）。特别地，如果 $i=0$，那么 $nums$ 是严格递增数组，没有发生旋转。
 * 
 * 根据这一性质，分类讨论：
 * 
 * - 如果 $target > nums[n-1]$，那么 $target$ 只可能在子数组 $[0,i-1]$ 中。由于子数组 $[0,i-1]$ 是递增的，我们可以在 $[0,i-1]$ 中二分查找 $target$。
 * - 如果 $target <= nums[n-1]$，那么 $target$ 只可能在子数组 $[i,n-1]$ 中。由于子数组 $[i,n-1]$ 是递增的，我们可以在 $[i,n-1]$ 中二分查找 $target$。
 * 
 * 注意上述讨论兼容 $i=0$ 的情况：
 * 
 * - 如果 $target > nums[n-1]$，由于 $nums$ 是递增的，所以 $target$ 比 $nums$ 中的每个数都要大，所以 $nums$ 不存在 $target$。代码调用 $lowerBound$ 传入的 $right=0$，nums[0] == target 是 $false$，最后会返回 $-1$。
 * - 如果 $target <= nums[n-1]$，那么在 $[i,n-1]$ 中二分也就是在 $[0,n-1]$ 中二分。
 * 
 * 二分基础知识：【基础算法精讲 04】。
 * 
 * 本题视频讲解：【基础算法精讲 05】。
 * 
 * 下面代码用的开区间二分，用其他二分写法也是可以的。不同二分写法的区别见 我的题解。
 */
public class Hot066_LC33_search {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int i = findMin(nums);
        if (target > nums[n - 1]) { // target 只可能在第一段
            return lowerBound(nums, -1, i, target); // 开区间 (-1, i)
        }
        // target 只可能在第二段
        // 由于此时 target <= nums[n-1]，所以 lowerBound 中的循环结束后，right < n 一定成立，无需判断 right == n
        return lowerBound(nums, i - 1, n, target); // 开区间 (i-1, n)
    }

    // 153. 寻找旋转排序数组中的最小值（返回的是下标）
    private int findMin(int[] nums) {
        int n = nums.length;
        int left = -1;
        int right = n - 1; // 开区间 (-1, n-1)
        while (left + 1 < right) { // 开区间不为空
            int mid = (left + right) >>> 1;
            if (nums[mid] < nums[n - 1]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    // 有序数组中找 target 的下标
    private int lowerBound(int[] nums, int left, int right, int target) {
        while (left + 1 < right) { // 开区间不为空
            // 循环不变量：
            // nums[right] >= target
            // nums[left] < target
            int mid = (left + right) >>> 1;
            if (nums[mid] >= target) {
                right = mid; // 范围缩小到 (left, mid)
            } else {
                left = mid; // 范围缩小到 (mid, right)
            }
        }
        return nums[right] == target ? right : -1;
    }
}

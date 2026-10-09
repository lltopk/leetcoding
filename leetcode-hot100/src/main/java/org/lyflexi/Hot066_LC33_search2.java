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
 * 答疑
 * 
 * 问：在 $[0,n-2]$ 中二分，是否需要考虑所有数都比 $target$ 小的情况？或者更一般地，是否需要考虑 $target$ 不在 $nums$ 中的情况？
 * 
 * 答：不需要。如果 $target$ 不在 $nums$ 中，那么无论最终的 $right$ 是多少，nums[right] == target 一定不成立，算法一定会返回 $-1$。
 */
public class Hot066_LC33_search2 {
    public int search(int[] nums, int target) {
        int last = nums[nums.length - 1];
        int left = -1;
        int right = nums.length - 1; // 开区间 (-1, n-1)
        while (left + 1 < right) { // 开区间不为空
            int mid = (left + right) >>> 1;
            int x = nums[mid];
            if (target > last && x <= last) { // target 在第一段，x 在第二段
                right = mid; // 下轮循环去左边找
            } else if (x > last && target <= last) { // x 在第一段，target 在第二段
                left = mid; // 下轮循环去右边找
            } else if (x >= target) { // 否则，x 和 target 在同一段，这就和方法一的 lowerBound 一样了
                right = mid;
            } else {
                left = mid;
            }
        }
        return nums[right] == target ? right : -1;
    }
}

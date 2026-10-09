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
 * 写法二
 * 
 * 下面只讨论 $target$ 在 $x$ 左边，或者 $x=target$ 的情况。其余情况 $target$ 一定在 $x$ 的右边。
 * 
 * - 如果 $x > nums[n-1]$，说明 $x$ 在第一段中，那么 $target$ 也必须在第一段中（否则 $target$ 一定在 $x$ 的右边）且 $x$ 必须大于等于 $target$。
 *    - 写成代码就是 target > nums[n - 1] && x >= target。
 * - 如果 $x <= nums[n-1]$，说明 $x$ 在第二段中（或者 $nums$ 只有一段），那么 $target$ 可以在第一段，也可以在第二段。
 *    - 如果 $target$ 在第一段，那么 $target$ 一定在 $x$ 左边。
 *    - 如果 $target$ 在第二段，那么 $x$ 必须大于等于 $target$。
 *    - 写成代码就是 target > nums[n - 1] || x >= target。
 * 
 * 根据这两种情况，去判断 $x$ 和 $target$ 的位置关系，从而不断地缩小 $target$ 所在位置的范围，二分找到 $target$。
 */
public class Hot066_LC33_search3 {
    public int search(int[] nums, int target) {
        int left = -1;
        int right = nums.length - 1; // 开区间 (-1, n-1)
        while (left + 1 < right) { // 开区间不为空
            int mid = (left + right) >>> 1;
            if (check(nums, target, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return nums[right] == target ? right : -1;
    }

    private boolean check(int[] nums, int target, int i) {
        int last = nums[nums.length - 1];
        int x = nums[i];
        if (x > last) {
            return target > last && x >= target;
        }
        return target > last || x >= target;
    }
}

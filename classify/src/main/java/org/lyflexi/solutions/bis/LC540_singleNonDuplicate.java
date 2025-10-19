package org.lyflexi.solutions.bis;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/10/19 17:01
 * @description
 * 540. 有序数组中的单一元素
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 *
 * 请你找出并返回只出现一次的那个数。
 *
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 *
 */
public class LC540_singleNonDuplicate {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int l = 0, r = n-1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (mid % 2 == 1) mid--; // 保证 mid 是偶数
            if (nums[mid] == nums[mid + 1]) {
                l = mid + 2;
            } else {//单值右边的nums[偶索引]!=nums[偶索引+1]
                r = mid;
            }
        }
        return nums[l];
    }
}

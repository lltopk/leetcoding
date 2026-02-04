package org.lyflexi.solutions.strategy_divide;

/**
 * @author hasee
 * @description V1.0
 * @create 2025/10/3 17:07
 */
public class LC34_searchRange {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int l = lowerBound(nums, target);
        int r = lowerBound(nums, target + 1) - 1;
        if (l == n || nums[l] != target) {
            return new int[] { -1, -1 };
        }
        return new int[] { l, r };
    }

    private int lowerBound(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n;

        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}

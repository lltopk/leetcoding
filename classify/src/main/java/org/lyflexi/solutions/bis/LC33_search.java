package org.lyflexi.solutions.bis;

/**
 * @author hasee
 * @description V1.0
 * @create 2025/10/5 18:06
 */
public class LC33_search {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 1;
        System.out.println(search(nums, target));
    }

    @Deprecated
    public int searchDeprecated(int[] nums, int target) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int p = 0;
        while (p < n - 1 && nums[p] < nums[p + 1]) {
            p++;
        }

        int llen = p + 1;
        int rlen = n - llen;

        if (n == llen) {
            return binarySearch(nums, 0, n, target);
        }

        if (target >= nums[llen] && target <= nums[n - 1]) {
            return binarySearch(nums, llen, n, target);
        } else {
            return binarySearch(nums, 0, llen, target);
        }

    }

    private int binarySearch(int[] nums, int left, int right, int target) {

        while (left < right) {
            int midIndex = (left + right) >> 1;
            if (nums[midIndex] > target) {
                right = midIndex;
            } else if (nums[midIndex] < target) {
                left = midIndex + 1;
            } else {
                left = right = midIndex;
                return left;
            }
        }

        return -1;

    }

    //题目要求直接logn, 就意味着只能在一个二分模板中排序
    public static int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n;
        //循环不变量
        while (left < right) {
            int midIndex = (left + right) >> 1;
            if (nums[midIndex] == target) {
                return midIndex;
            }
            //落入大数组, 只知道左值, 先左移
            if (nums[midIndex] > nums[0]) {
                if (nums[midIndex] > target && target >= nums[0]) {
                    right = midIndex;
                } else {
                    left = midIndex + 1;
                }
            } else {//落入小数组. 只知道右值, 先右移
                if (nums[midIndex] < target && target <= nums[n - 1]) {
                    left = midIndex + 1;
                } else {
                    right = midIndex;
                }
            }
        }
        return -1;
    }
}

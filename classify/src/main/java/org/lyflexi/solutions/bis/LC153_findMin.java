package org.lyflexi.solutions.bis;

/**
 * @author hasee
 * @description V1.0
 * @create 2025/10/6 14:40
 */
public class LC153_findMin {
    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2};
        System.out.println(findMin(nums));
    }
    public static int findMin(int[] nums) {
        int p = findPivot(nums);
        return nums[p];
    }

    /**
     寻找旋转点
     等价于寻找的是数组不完全连续的部分
     调试的点在于, right一定赋值为n-1
     */
    private static int findPivot(int[] nums) {
        int left = 0;
        int n = nums.length;
        int right = n - 1;
        while (left < right) {
            int midIndex = (left + right) >> 1;
            if (nums[midIndex] > nums[right]) {
                left = midIndex + 1;
            } else {
                right = midIndex;
            }
        }
        return left;
    }
}

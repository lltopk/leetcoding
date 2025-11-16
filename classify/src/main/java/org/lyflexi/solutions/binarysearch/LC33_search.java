package org.lyflexi.solutions.binarysearch;

/**
 * @author hasee
 * @description V1.0
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
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [1], target = 0
 * 输出：-1
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

    @Deprecated
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

    /**
     * 题目要求直接logn, 就意味着只能在一个二分模板中排序
     * compare with 0 and n-1
     * @param nums
     * @param target
     * @return
     */
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

    /**
     * compare with left and right-1
     * @param nums
     * @param target
     * @return
     */
    public static int searchV2(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n;
        //循环不变量
        while(left<right){
            int midIndex = (left+right)>>1;
            if(nums[midIndex] == target){
                return midIndex;
            }
            //落入大数组, 只知道左值, 先左移
            if(nums[midIndex]>nums[left]){
                if(nums[midIndex]>target && target>=nums[left]){
                    right = midIndex ;
                }else{
                    left = midIndex +1;
                }
            }else{//落入小数组. 只知道右值, 先右移
                if(nums[midIndex]<target && target<=nums[right-1]){
                    left = midIndex +1;
                }else{
                    right = midIndex ;
                }
            }
        }
        return -1;
    }
}

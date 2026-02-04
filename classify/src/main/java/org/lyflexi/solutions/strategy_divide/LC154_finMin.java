package org.lyflexi.solutions.strategy_divide;

/**
 * @author hasee
 * @description V1.0
 * 154. 寻找旋转排序数组中的最小值 II
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 *
 * 你必须尽可能减少整个过程的操作步骤。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,5]
 * 输出：1
 * 示例 2：
 *
 * 输入：nums = [2,2,2,0,1]
 * 输出：0
 * @create 2025/10/7 15:28
 */
public class LC154_finMin {
    public int findMin(int[] nums) {
        int p = findPivot(nums);
        return nums[p];
    }

    /**
     寻找旋转点
     等价于寻找的是数组不完全连续的部分
     调试的点在于, right一定赋值为n-1
     */
    private int findPivot(int[] nums) {
        int left = 0;
        int n = nums.length;
        //r必须初始为n-1, 下文比较的是midIndex和right, right不能为n, 会越界
        int right = n - 1;
        while (left < right) {
            int midIndex = (left + right) >> 1;
            if (nums[midIndex] > nums[right]) {
                left = midIndex + 1;
            } else if(nums[midIndex] < nums[right]){
                right = midIndex;
            } else{
                right--;//重复元素与nums[right]相等 [3,3,1,3], 此时需要缩短数组
            }
        }
        return left;
    }
}

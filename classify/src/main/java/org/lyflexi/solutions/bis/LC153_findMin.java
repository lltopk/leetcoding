package org.lyflexi.solutions.bis;

/**
 * @author hasee
 * @description V1.0
 * 153. 寻找旋转排序数组中的最小值
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 *
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 * 示例 2：
 *
 * 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
 * 示例 3：
 *
 * 输入：nums = [11,13,15,17]
 * 输出：11
 * 解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
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
     寻找旋转点, 等价于寻找的是数组不完全连续的部分
     选则右边界为比较点, 因此right一定赋值为n-1, 这个地方是区别于标准的二分模板
     */
    private static int findPivot(int[] nums) {
        int left = 0;
        int n = nums.length;
        //r必须初始为n-1, 下文比较的是midIndex和right, right不能为n, 会越界
        int right = n - 1;
        while (left < right) {
            int midIndex = (left + right) >> 1;
            if (nums[midIndex] > nums[right]) {
                left = midIndex + 1;
            } else {
                right = midIndex;
            }//数组元素不相同, 不存在nums[midIndex] == nums[right]
        }
        return left;
    }
}

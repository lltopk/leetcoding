package org.lyflexi.solutions.binarysearch;

/**
 * @author hasee
 * @description V1.0
 * 162. 寻找峰值
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 峰值元素是指其值严格大于左右相邻值的元素。
 *
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 *
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 *
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2：
 *
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * @create 2025/10/7 15:35
 */
public class LC162_findPeakElement {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if(n==1){
            return 0;
        }
        //规律是峰值两侧为有序
        int l = 0;
        //r必须初始为n-1, 下文比较的是nums[midIndex]和nums[midIndex+1], 防止当midIndex = r-1的时候, midIndex+1越界
        // eg nums: [2,1] 此时midIndex+1越界
        int r = n-1;
        while(l<r){
            int midIndex = (l+r)>>1;
            if(nums[midIndex]<nums[midIndex+1]){
                l = l+1;
            }else if (nums[midIndex]>nums[midIndex+1]){
                r = midIndex;
            }else{
                l=r=midIndex;
                return l;
            }
        }
        return l;
    }
}

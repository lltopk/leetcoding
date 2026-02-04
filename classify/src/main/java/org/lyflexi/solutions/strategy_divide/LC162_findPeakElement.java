package org.lyflexi.solutions.strategy_divide;

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

/**
 * 二分查找, 由于题目描述两端点也有可能是解, 因此在比较过程中需要考虑越界问题
 */
public class LC162_findPeakElement {

    //比较nums[mid]和nums[mid+1]
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int l = 0;
        //从题意出发初始化[l,r): [0,n-1), 当二分失败, l==n-1, 恰好是峰值, 因为数组两侧隐藏两个-∞
        //也恰好解决了mid+1越界的问题, 下文比较的是nums[midIndex]和nums[midIndex+1], 防止当midIndex = r-1的时候, midIndex+1越界
        // eg. nums [2,1]
        int r = n-1;
        while(l<r){
            int midIndex = (l+r)>>1;
            if(nums[midIndex]<nums[midIndex+1]){
                l = midIndex+1;
            }else{
                r=midIndex;
            }
        }
        return l;
    }

    //另一种写法, 比较nums[midIndex]>nums[midIndex-1], 考虑的是左边界越界问题(题目秒数左端点也有可能是极值, 此时mid-1越界), 需要初始化l=1
    //而且最终返回l-1
    // public int findPeakElement(int[] nums) {
    //     int n = nums.length;
    //     int l = 1;
    //     int r = n;
    //     while(l<r){
    //         int midIndex = (l+r)>>1;
    //         if(nums[midIndex]>nums[midIndex-1]){
    //             l = midIndex+1;
    //         }else{
    //             r=midIndex;
    //         }
    //     }
    //     return l-1;
    // }
}

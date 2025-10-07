package org.lyflexi.solutions.bis;

import java.util.Arrays;

/**
 * @author hasee
 * @description V1.0
 * 704. 二分查找
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果 target 存在返回下标，否则返回 -1。
 *
 * 你必须编写一个具有 O(log n) 时间复杂度的算法。
 *
 *
 * 示例 1:
 *
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 示例 2:
 *
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 * @create 2025/10/7 18:15
 */
public class LC704_search {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5};
        System.out.println(search(nums, 4));
    }
    public static int search(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0;
        //区间不变量[l,r)
        int right = n;
        while(left<right){
            int mindex = (left+right) >>1;
            if(nums[mindex] == target){
                left = right = mindex;
                return left;
            }
            if(nums[mindex]>target){
                right = mindex;
            }else if(nums[mindex]<target){
                left = mindex+1;
            }
        }
        return -1;
    }
}

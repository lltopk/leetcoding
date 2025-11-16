package org.lyflexi.solutions.binarysearch;

import java.util.Arrays;

/**
 * @author hasee
 * @description V1.0
 * 719. 找出第 K 小的数对距离
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。
 *
 * 给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length 。返回 所有数对距离中 第 k 小的数对距离。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,1], k = 1
 * 输出：0
 * 解释：数对和对应的距离如下：
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * 距离第 1 小的数对是 (1,1) ，距离为 0 。
 * 示例 2：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：0
 * 示例 3：
 *
 * 输入：nums = [1,6,1], k = 3
 * 输出：5
 * @create 2025/10/2 18:23
 */
public class LC719_smallestDistancePair {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9};
        System.out.println(smallestDistancePair(nums,5));
    }
    public static int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        //左右指针的值分别是min_dist和max_dist
        int left = 0;
        int right = nums[n-1] -nums[0];
        while(left<right){
            int mid = (left+right)/2;
            if(countLessMid(nums,mid)>=k){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return left;
    }

    /**
     两重循环要优化成双指针才行

     通过将内循环for改成while来优化
     */
    private static int countLessMid(int []nums,int mid){
        int rs = 0;
        int n = nums.length;
        int j = 1;
        for(int i= 0;i<n-1;i++){
            if(i==j){
                j++;
            }
            while(i<j && j<n && nums[j]- nums[i] <=mid){
                j++;
            }
            rs = rs+j-i-1;
        }
        return rs;
    }

}

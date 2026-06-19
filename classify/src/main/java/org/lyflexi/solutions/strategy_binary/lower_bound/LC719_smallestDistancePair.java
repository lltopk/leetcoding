package org.lyflexi.solutions.strategy_binary.lower_bound;

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
    /**
     检查函数逻辑为: 满足绝对差值 ≤mid 的数对数 与 k进行比较

     由于是求第k小, 并且距离不连续, 但是二分答案是连续的, 因此二分答案的逻辑等号≥应该给到right
     */
    public static int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        //左右指针(left, right)
        int left = -1;
        int right = nums[n-1] -nums[0] + 1;
        //二分答案推荐开区间写法
        while(left + 1 < right){
            int mid = (right + left) >> 1;
            if(checkDes(nums, mid, k)){
                right = mid;
            }else{
                left = mid;
            }
        }
        return right;
    }

    /**
     逻辑小于k
     */
    private static boolean checkDes(int[] nums,int mid, int k){
        int rs = 0;
        int n = nums.length;
        int j = 1;
        for(int i= 0; i<n-1; i++){
            //双指针优化
            if(i==j){
                j++;
            }
            while(i<j && j<n && nums[j]- nums[i] <=mid){
                j++;
            }
            rs += j-i-1;
        }
        //逻辑大于等于
        return rs >= k;
    }

}

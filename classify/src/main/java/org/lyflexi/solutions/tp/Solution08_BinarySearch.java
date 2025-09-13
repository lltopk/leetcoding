package org.lyflexi.solutions.tp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/2/4 10:46
 */

/*二分查找
给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。

示例 1:
输入: nums = [-1,0,3,5,9,12], target = 9
输出: 4
解释: 9 出现在 nums 中并且下标为 4

示例 2:
输入: nums = [-1,0,3,5,9,12], target = 2
输出: -1
解释: 2 不存在 nums 中因此返回 -1*/
public class Solution08_BinarySearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int target = Integer.parseInt(scanner.nextLine());
        System.out.println(search(nums, target));
    }
    public static int search(int[] nums, int target) {
        int len = nums.length;

        int left = 0,right = len-1;

        while(left<right){

            int mid  =  (left+right)/2;

            if (target==nums[mid]){
                return mid;
            }else if (target>nums[mid]){
                left = mid+1;
            }else{
                right = mid-1;
            }

        }
        return -1;
    }
}

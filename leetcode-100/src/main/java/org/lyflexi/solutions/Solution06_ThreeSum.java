package org.lyflexi.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: ly
 * @Date: 2024/1/14 11:02
 */

/*
* 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。
* 请你返回所有和为 0 且不重复的三元组。
*
* 示例 1：
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
解释：
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
注意，输出的顺序和三元组的顺序并不重要。
*
*
* 示例 2：
输入：nums = [0,1,1]
输出：[]
解释：唯一可能的三元组和不为 0 。
*
*
* 示例 3：
输入：nums = [0,0,0]
输出：[[0,0,0]]
解释：唯一可能的三元组和为 0 。

* */
public class Solution06_ThreeSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(threeSum(array));
    }

    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> answer = new ArrayList<>();

        if (nums == null) return answer;//return null

        int n = nums.length;

        if (n<3) return answer;//return null

        Arrays.sort(nums);

        if (nums[0]>0) return answer;//return null

        for (int i = 0; i < n; i++) {

            int left = i+1;
            int right =n-1;

            //nums[i]这样循环去重是错误的
//            if (i<n-1&&nums[i]==nums[i+1]) continue;
            // nums[i]循环去重的正确逻辑如下，与下面双指针的去重逻辑相反
            //要保留相邻nums[i]和nums[i+1]相等，且作为同一个三元组的情况
            if(i > 0 && nums[i] == nums[i-1]) continue;

            while (left<right){

                int sum = nums[i]+nums[left]+nums[right];
                if (sum==0){
                    answer.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while(left<right&&nums[left]==nums[left+1]) left++;//循环去重,向右走
                    while(left<right&&nums[right]==nums[right-1]) right--;//循环去重,向左走
                    left++;
                    right--;
                }
                else if (sum<0) {
                    left++;
                }else{
                    right--;
                }
            }

        }
        return answer;

    }
}

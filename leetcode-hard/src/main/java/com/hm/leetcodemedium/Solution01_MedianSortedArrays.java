package com.hm.leetcodemedium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @Author: ly
 * @Date: 2024/2/4 11:02
 */

/*这道题放弃了，用暴力解法
*

4.寻找两个正序数组的中位数：
给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。

要求算法的时间复杂度应该为 O(log (m+n)) 。

示例 1：
输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2
*
*
示例 2：
输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
*
* */
public class Solution01_MedianSortedArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums1 = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[] nums2 = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        System.out.println(findMedianSortedArrays(nums1, nums2));

    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        ArrayList<Integer> list = new ArrayList<>();

        //boxed()函数，译为装箱，将流中的元素转为Integer包装类型
        list.addAll(Arrays.stream(nums1).boxed().collect(Collectors.toList()));
        list.addAll(Arrays.stream(nums2).boxed().collect(Collectors.toList()));


        Collections.sort(list);
        if (list.size() == 0){
            return 0;
        }
        if(list.size()%2>0){
            return list.get(((list.size()+1)/2)-1);
        }else {
            int a = list.size()/2;
            return  (double)(list.get(a) +list.get(a-1))/2;
        }
    }
}

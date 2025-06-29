package org.lyflexi.solutions.tp.slipingWindow;

import java.util.*;
//同向移动的双指针就是滑动窗口，但是大部分情况下相向移动的双指针与滑动窗口没有任何关系
//综上，滑动窗口与双指针不能混为一谈，对于滑动窗口而言往往利用for循环就可以解题


/*滑动窗口：阿里巴巴找黄金宝箱(V)：滑动窗口
* 难度	易
题目说明	一贫如洗的樵夫阿里巴巴在去砍柴的路上，无意中发现了强盗集团的藏宝地，藏宝地有编号从0-N的箱子，每个箱子上面贴有一个数字。
阿里巴巴念出一个咒语数字 k ( k<N )，找出连续 k 个宝箱数字和的最大值，并输出该最大值。
输入描述	第一行输入一个数字字串，数字之间使用逗号分隔，例如: 2,10,-3,-8,40,5。
1 ≤ 字串中数字的个数 ≤ 100000。
-10000 ≤ 每个数字 ≤10000。
第二行输入咒语数字，例如: 4，咒语数字大小小于宝箱的个数。
输出描述	连续 k 个宝箱数字和的最大值，例如: 39。
补充说明	增加条件，窗口中元素不重复

*
* */

public class Solution02_WindowsAlibabaUniqueItem {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int[] nums = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(in.nextLine());
        System.out.println(maxWindows(nums,k));
        return;
    }

    //增加条件，窗口中元素不重复,
    // 使用set来去除窗口中的重复元素，直至set.size()==k才计算

    public static int maxWindows(int[] nums, int k) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        Set<Integer> set = new HashSet<>();

        int left = 0;
        int right = 0;
        int sum = 0;

        while (right < n) {
            if (set.size() < k) {
                sum += nums[right];
                set.add(nums[right]);
                right++;
            }

            if (set.size() == k) {
                maxSum = Math.max(maxSum, sum);

                sum -= nums[left];
                set.remove(nums[left]);
                left++;
            }
        }

        return maxSum;
    }
}
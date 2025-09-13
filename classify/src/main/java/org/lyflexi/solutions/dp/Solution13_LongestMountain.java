package org.lyflexi.solutions.dp;

/**
 * @Author: ly
 * @Date: 2024/3/19 14:54
 */

/*
* 845. 数组中的最长山脉
把符合下列属性的数组 arr 称为 山脉数组 ：
arr.length >= 3
存在下标 i（0 < i < arr.length - 1），满足
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
给出一个整数数组 arr，返回最长山脉子数组的长度。如果不存在山脉子数组，返回 0 。


示例 1：
输入：arr = [2,1,4,7,3,2,5]
输出：5
解释：最长的山脉子数组是 [1,4,7,3,2]，长度为 5。
*
示例 2：
输入：arr = [2,2,2]
输出：0
解释：不存在山脉子数组。
* */
public class Solution13_LongestMountain {
    public int longestMountain(int[] arr) {
        int answer = 0;

        int n = arr.length;
        int[] left = new int[n];//这是个dp数组，求每个元素的左边可以延申的距离
        //默认left[0]为0
        for (int i = 1; i < n; i++) {
            left[i] = arr[i - 1] < arr[i] ? left[i - 1] + 1 : 0;
        }


        int[] right = new int[n];//这是个dp数组，求每个元素的右边可以延申的距离
        //默认right[n-1]为0
        for (int i = n - 2; i >= 0; i--) {
            right[i] = arr[i + 1] < arr[i] ? right[i + 1] + 1 : 0;
        }

        for (int i = 0; i < n; i++) {
            if (left[i] > 0 && right[i] > 0) {
                answer = Math.max(answer, left[i] + right[i] + 1);
            }


        }
        return answer;
    }
}

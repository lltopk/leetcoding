/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.lyflexi.solutions.Ⅲdivideconquer;

/**
 *
 * @author hasee
 * 
 * LCR 069. 山脉数组的峰顶索引
已解答
简单
相关标签
premium lock icon
相关企业
符合下列属性的数组 arr 称为 山峰数组（山脉数组） ：

arr.length >= 3
存在 i（0 < i < arr.length - 1）使得：
arr[0] < arr[1] < ... arr[i-1] < arr[i] 
arr[i] > arr[i+1] > ... > arr[arr.length - 1]
给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i ，即山峰顶部。

 

示例 1：

输入：arr = [0,1,0]
输出：1
示例 2：

输入：arr = [1,3,5,4,2]
输出：2
示例 3：

输入：arr = [0,10,5,2]
输出：1
示例 4：

输入：arr = [3,4,5,1]
输出：2
示例 5：

输入：arr = [24,69,100,99,79,78,67,36,26,19]
输出：2
 

提示：

3 <= arr.length <= 104
0 <= arr[i] <= 106
题目数据保证 arr 是一个山脉数组
 
你可以设计一个 O(log(n)) 的解决方案吗？
 */

/**
 * 二分查找
 * 
 * 此题明确左右端点不会是峰值, 因此不用考虑越界问题
 */
public class LCR069_peakIndexInMountainArray {

    //比较arr[mid]和arr[mid+1]
    public int peakIndexInMountainArray(int[] arr) {
        int len = arr.length;
        int l = 1, r = len-1;

        while(l<r){
            int mid = l + ((r-l)>>1);
            if(arr[mid]<arr[mid+1]){
                l = mid +1;
            }else{
                r = mid;
            }
        }

        return l;
    }

    // 另一种写法, 比较arr[mid]和arr[mid-1],
    // 而且注意最终返回l-1
    // public int peakIndexInMountainArray(int[] arr) {
    //     int len = arr.length;
    //     int l = 1, r = len-1;

    //     while(l<r){
    //         int mid = l + ((r-l)>>1);
    //         if(arr[mid]>arr[mid-1]){
    //             l = mid +1;
    //         }else{
    //             r = mid;
    //         }
    //     }

    //     return l-1;
    // }
}

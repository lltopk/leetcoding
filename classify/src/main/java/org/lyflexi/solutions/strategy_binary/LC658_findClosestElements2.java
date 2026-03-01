/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.lyflexi.solutions.strategy_binary;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hasee
 * 658. 找到 K 个最接近的元素
已解答
中等
相关标签
premium lock icon
相关企业
给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。

整数 a 比整数 b 更接近 x 需要满足：

|a - x| < |b - x| 或者
|a - x| == |b - x| 且 a < b
 

示例 1：

输入：arr = [1,2,3,4,5], k = 4, x = 3
输出：[1,2,3,4]
示例 2：

输入：arr = [1,1,2,3,4,5], k = 4, x = -1
输出：[1,1,2,3]
 

提示：

1 <= k <= arr.length
1 <= arr.length <= 104
arr 按 升序 排列
-104 <= arr[i], x <= 104
 */
public class LC658_findClosestElements2 {
    //双指针两端夹逼窗口， 复杂度ON
    // public List<Integer> findClosestElements(int[] arr, int k, int x) {
    //     int n = arr.length;
    //     int l = 0, r = n-1;
    //     while(r-l+1>k){
    //         if(Math.abs(x-arr[l])<Math.abs(x-arr[r])){
    //             r--;
    //             continue;
    //         }
    //         if(Math.abs(x-arr[l])>Math.abs(x-arr[r])){
    //             l++;
    //             continue;
    //         }
    //         if(Math.abs(x-arr[l]) == Math.abs(x-arr[r])){
    //             //当arr[l]==arr[r]的时候， 必须是r--， 原因是原数组升序， 右边的元素值大于左边的元素值
    //             if( arr[l]<=arr[r] ) r--;
    //             if( arr[l]>arr[r] ) l++;
    //             continue;
    //         }
    //     }

    //     List<Integer> rs= new ArrayList<>();
    //     for(int i = l;i<=r;i++){
    //         rs.add(arr[i]);
    //     }
    //     return rs;
    // }

    //二分优化， 也是维护窗口， 但不同的是计算过程中窗口大小维持不变为K， 使用二分求窗口左边界
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        int l = 0, r = n-k;//二分求左边界限定[0,n-k)， 当二分失败，l = n-k， 窗口大小恰好为k.   如果限定[0,n-k+1)则会在窗口左边界mid与窗口右边界+1即mid+k正常比较的过程中，mid取到n-k, 此时右边界+1为n越界
        while(l<r){
            int mid = l + ((r-l)>>1);//mid即目标值索引，我们认为是左边界索引

            //注意下面和二分模板相反， 这里要根据题意来写(判断标准是尽可能x恰好等分窗口, 但比较对象是窗口左边界mid, 和右边界+1：mid+k)
            if(x-arr[mid] <= arr[mid+k]-x){
                r = mid;//x-arr[mid] == arr[mid+k]-x: 求窗口左边界的左值
            }else{
                l = mid+1;
            }
        }

        List<Integer> rs= new ArrayList<>();
        for(int i = l;i<l+k;i++){
            rs.add(arr[i]);
        }
        return rs;
    }
    /**
     *  x-arr[mid] 和 arr[mid+k]-x都是有可能小于0的， 为什么不加绝对值呢？
     *      极端的情况当x位于原数组极右值， 此时arr[mid+k]-x小于0， 但arr[mid+k]必定距离x更近， 所以不能使用绝对值比较！！！
     *  Wrong Answer
        68/70 cases passed (N/A)
        Testcase
        [1,1,2,2,2,2,2,3,3]
        3
        3
        Answer
        [2,2,2]
        Expected Answer
        [2,3,3]
     */
}

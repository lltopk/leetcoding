/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.lyflexi.solutions.strategy_binary;

/**
 *
 * @author hasee
 * 
 * 1287. 有序数组中出现次数超过25%的元素
已解答
简单
相关标签
premium lock icon
相关企业
提示
给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。

请你找到并返回这个整数

 

示例：

输入：arr = [1,2,2,6,6,6,6,7,10]
输出：6
 

提示：

1 <= arr.length <= 10^4
0 <= arr[i] <= 10^5
 */
public class LC1287_findSpecialInteger {

    //一次遍历法, 复杂度ON
    // public int findSpecialInteger(int[] arr) {
    //     int len = arr.length;

    //     int cnt = 1;
    //     int item = arr[0];
    //     int maxCnt = Integer.MIN_VALUE;
    //     for(int i = 1; i<len; i++){
    //         if(arr[i]==item){
    //             cnt++;
    //             maxCnt = Math.max(maxCnt, cnt);
    //             if(maxCnt > len/4){
    //                 return arr[i];
    //             }
    //         }else{
    //             cnt = 1;
    //             item = arr[i];
    //         }
    //     }

    //     return arr[0];
    // }

    //砍三刀, 四等分{一,二,三,四}, 答案一定位于arr[0]和三刀右侧元素上其一
    //因此我们用4*2次二分查找即可, 即8logn
    public int findSpecialInteger(int[] arr) {
        int len = arr.length;
        int step = len/4+1;

        //arr[i]恰好就是三刀右侧第一个元素
        for(int i = 0; i<len; i+=step){
            int l = lowerBound(arr, arr[i]);
            int r = lowerBound(arr, arr[i]+1);//找比arr[i]大的下一个数的位置即可

            if(r-l>=step){
                return arr[l];
            }
        }

        return -1;
    }

    private int lowerBound(int [] arr, int target){
        int n = arr.length;
        int l = 0, r = n;
        while(l<r){
            int mid = l + ((r-l)>>1);
            if(arr[mid]<target){
                l = mid +1;
            }else{
                r = mid;
            }
        }

        return l;
    }

}

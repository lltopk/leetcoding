/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.lyflexi.solutions.Ⅲdivideconquer;

/**
 *
 * @author hasee
 * 
 * 875. 爱吃香蕉的珂珂
已解答
中等
相关标签
premium lock icon
相关企业
珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。

珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  

珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。

返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。

 

示例 1：

输入：piles = [3,6,7,11], h = 8
输出：4
示例 2：

输入：piles = [30,11,23,4,20], h = 5
输出：30
示例 3：

输入：piles = [30,11,23,4,20], h = 6
输出：23
 

提示：

1 <= piles.length <= 104
piles.length <= h <= 109
1 <= piles[i] <= 109
 */

/**
 * 二分答案
 */
public class LC875_minEatingSpeed {
    public int minEatingSpeed(int[] piles, int h) {
        int len = piles.length;
        //初始化二分区间(吃速), r为piles[i]的最大值, 因为珂珂一次最多只能吃一堆
        int l = 1;
        int r = Integer.MIN_VALUE;
        for(int i = 0; i< len; i++){
            r = Math.max(r, piles[i]);
        }

        while(l < r){
            int mid = l + ((r-l)>>1);
            if(checkIncrease(piles, mid, h)){
                l = mid +1;
            }else{
                r = mid;
            }
        }

        return l;
    }

    private boolean checkIncrease(int[] piles, int mid, int h){
        int n = piles.length;
        long costH = 0;
        for(int i= 0; i< n ;i ++){
            costH += (piles[i]+mid-1)/mid;//向上取整
        }

        return costH > h;
    }
}

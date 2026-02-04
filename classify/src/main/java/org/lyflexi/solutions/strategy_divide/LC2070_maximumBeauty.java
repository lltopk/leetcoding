/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.lyflexi.solutions.strategy_divide;

import java.util.Arrays;

/**
 *
 * @author hasee
 */
public class LC2070_maximumBeauty {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        int n = queries.length;
        int[] rs = new int[n];
        Arrays.sort(items,(o1,o2)->{return o1[0]-o2[0];});

        //提前原地计算最大美丽值: 前缀最大值（非全局最大）可以原地计算
        for(int i = 1;i<items.length;i++){
            items[i][1] = Math.max(items[i][1],items[i-1][1]);     
        }

        for(int i = 0;i<queries.length;i++){
            int r = lowerBound(items,queries[i]+1)-1;
            //套在大循环中的findMaxBeauty会超时， 所以应该提前把最值算好
            rs[i] = r==-1?0:items[r][1];
        }
        return rs;
    }

    private int lowerBound(int[][] items, int target){
        int n = items.length;
        int l = 0, r = n;
        while(l<r){
            int mid = l + ((r-l)>>1);
            if(items[mid][0]<target){
                l = mid +1;
            }else{
                r = mid;
            }
        }
        return l;
    }

    // @Deprecated
    // private int findMaxBeauty(int[][] items,int r){
    //     if(r==-1){
    //         return 0;
    //     }
    //     int beauty = Integer.MIN_VALUE;
    //     for(int i = 0;i<=r;i++){
    //         if(items[i][1]>beauty){
    //             beauty = items[i][1];
    //             continue;
    //         }
    //     }
    //     return beauty;
    // }
}

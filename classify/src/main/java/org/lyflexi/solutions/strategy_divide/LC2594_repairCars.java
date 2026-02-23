/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.lyflexi.solutions.strategy_divide;

/**
 *
 * @author hasee
 * 
 * 2594. 修车的最少时间
已解答
中等
相关标签
premium lock icon
相关企业
提示
给你一个整数数组 ranks ，表示一些机械工的 能力值 。ranksi 是第 i 位机械工的能力值。能力值为 r 的机械工可以在 r * n2 分钟内修好 n 辆车。

同时给你一个整数 cars ，表示总共需要修理的汽车数目。

请你返回修理所有汽车 最少 需要多少时间。

注意：所有机械工可以同时修理汽车。

 

示例 1：

输入：ranks = [4,2,3,1], cars = 10
输出：16
解释：
- 第一位机械工修 2 辆车，需要 4 * 2 * 2 = 16 分钟。
- 第二位机械工修 2 辆车，需要 2 * 2 * 2 = 8 分钟。
- 第三位机械工修 2 辆车，需要 3 * 2 * 2 = 12 分钟。
- 第四位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
16 分钟是修理完所有车需要的最少时间。
示例 2：

输入：ranks = [5,1,8], cars = 6
输出：16
解释：
- 第一位机械工修 1 辆车，需要 5 * 1 * 1 = 5 分钟。
- 第二位机械工修 4 辆车，需要 1 * 4 * 4 = 16 分钟。
- 第三位机械工修 1 辆车，需要 8 * 1 * 1 = 8 分钟。
16 分钟时修理完所有车需要的最少时间。
 

提示：

1 <= ranks.length <= 105
1 <= ranks[i] <= 100
1 <= cars <= 106
 */

/**
 * 二分答案法
 */
public class LC2594_repairCars {
    public long repairCars(int[] ranks, int cars) {
        //时间是趋势的, 所以二分时间
        int len = ranks.length;
        int minV = Integer.MAX_VALUE;
        //注意到题目中ranks的取值范围是[1,100]
        int [] cnt = new int[101];
        for(int i = 0; i< len; i++){
            cnt[ranks[i]]++;
            minV = Math.min(minV, ranks[i]);
        }

        //右边界是让最快的人修车所花费的时间即可(因为可以并行修车, 实际耗时会低于右边界)
        long l = 0, r = (long)minV*cars*cars;

        while(l + 1<r){
            long mid = l + ((r-l)>>1);
            if(checkInc(mid, minV, cars, cnt)){
                l = mid;
            }else{
                r = mid;
            }

        }

        return r;
    }

    private boolean checkInc(long mid, int minV, int cars, int[] cnt){
        long sumC = 0;//求当前时间mid总的修车数目

        //当前时间mid, 迭代相乘(cnt[rank])计算所有的修车数, 比遍历所有的ranks要快, 从10^5降为100次
        for(int rank = minV; rank<=100; rank++){
            sumC += (long)Math.sqrt(mid/rank) * cnt[rank];
        }

        return sumC < cars;
    }
}

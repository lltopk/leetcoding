/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.lyflexi.solutions.strategy_divide;

/**
 *
 * @author hasee
 * 1011. 在 D 天内送达包裹的能力
已解答
中等
相关标签
premium lock icon
相关企业
提示
传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。

传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。

返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。

 

示例 1：

输入：weights = [1,2,3,4,5,6,7,8,9,10], days = 5
输出：15
解释：
船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
第 1 天：1, 2, 3, 4, 5
第 2 天：6, 7
第 3 天：8
第 4 天：9
第 5 天：10

请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。 
示例 2：

输入：weights = [3,2,2,4,1,4], days = 3
输出：6
解释：
船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
第 1 天：3, 2
第 2 天：2, 4
第 3 天：1, 4
示例 3：

输入：weights = [1,2,3,1,1], days = 4
输出：3
解释：
第 1 天：1
第 2 天：2
第 3 天：3
第 4 天：1, 1
 

提示：

1 <= days <= weights.length <= 5 * 104
1 <= weights[i] <= 500
 */

/**
 * 二分答案
 */
public class LC1011_shipWithinDays {
    public int shipWithinDays(int[] weights, int days) {
        int len = weights.length;

        //初始化二分区间(运载能力), 左界是weights[i]最大值, 右界是weights之和
        int l = Integer.MIN_VALUE;
        int r = 0;
        for(int i = 0; i< len; i++){
            r +=weights[i];
            l = Math.max(l, weights[i]);//左界l必须是weights[i]最大值, 否则将无法搬走货物!
        }
        //开区间
        l--;
        //开区间
        r++;
        while(l + 1< r){
            int mid = l + ((r-l)>>1);
            if(checkIncrease(weights, mid, days)){
                l = mid;
            }else{
                r = mid;
            }
        }

        return r ;

    }

    private boolean checkIncrease(int[] weights, int mid , int days){
        int costDays = 1;
        int n = weights.length;
        int sum = 0;
        for(int i =0; i< n; i++){
            // 如果你非要初始化l = 1 或者是小于weights[i]的任何值, 则需要再这里加判断, 表示无法搬走货物, 应当移动l指针
            // if(mid < weights[i]){
            //     return true;
            // }
            sum += weights[i];
            if(sum>mid){
                sum = weights[i];
                costDays++;
            }
        }
        return costDays>days;
    }
}

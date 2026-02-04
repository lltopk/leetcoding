/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.lyflexi.solutions.strategy_divide;

/**
 *
 * @author hasee
 * 
 * 3296. 移山所需的最少秒数
已解答
中等
相关标签
premium lock icon
相关企业
提示
给你一个整数 mountainHeight 表示山的高度。

同时给你一个整数数组 workerTimes，表示工人们的工作时间（单位：秒）。

工人们需要 同时 进行工作以 降低 山的高度。对于工人 i :

山的高度降低 x，需要花费 workerTimes[i] + workerTimes[i] * 2 + ... + workerTimes[i] * x 秒。例如：
山的高度降低 1，需要 workerTimes[i] 秒。
山的高度降低 2，需要 workerTimes[i] + workerTimes[i] * 2 秒，依此类推。
返回一个整数，表示工人们使山的高度降低到 0 所需的 最少 秒数。

 

示例 1：

输入： mountainHeight = 4, workerTimes = [2,1,1]

输出： 3

解释：

将山的高度降低到 0 的一种方式是：

工人 0 将高度降低 1，花费 workerTimes[0] = 2 秒。
工人 1 将高度降低 2，花费 workerTimes[1] + workerTimes[1] * 2 = 3 秒。
工人 2 将高度降低 1，花费 workerTimes[2] = 1 秒。
因为工人同时工作，所需的最少时间为 max(2, 3, 1) = 3 秒。

示例 2：

输入： mountainHeight = 10, workerTimes = [3,2,2,4]

输出： 12

解释：

工人 0 将高度降低 2，花费 workerTimes[0] + workerTimes[0] * 2 = 9 秒。
工人 1 将高度降低 3，花费 workerTimes[1] + workerTimes[1] * 2 + workerTimes[1] * 3 = 12 秒。
工人 2 将高度降低 3，花费 workerTimes[2] + workerTimes[2] * 2 + workerTimes[2] * 3 = 12 秒。
工人 3 将高度降低 2，花费 workerTimes[3] + workerTimes[3] * 2 = 12 秒。
所需的最少时间为 max(9, 12, 12, 12) = 12 秒。

示例 3：

输入： mountainHeight = 5, workerTimes = [1]

输出： 15

解释：

这个示例中只有一个工人，所以答案是 workerTimes[0] + workerTimes[0] * 2 + workerTimes[0] * 3 + workerTimes[0] * 4 + workerTimes[0] * 5 = 15 秒。

 

提示：

1 <= mountainHeight <= 105
1 <= workerTimes.length <= 104
1 <= workerTimes[i] <= 106
 */

/**
 * 二分答案
 */
public class LC3296_minNumberOfSeconds {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        int n = workerTimes.length;

        //由于时间越多, 搬山越多, 因此可以二分(时间)
        long l = 0, r = 0;
        long maxTime = Integer.MIN_VALUE;
        for(int i = 0; i<n; i++){
            maxTime = Math.max(maxTime, workerTimes[i]);
        }
        // 假设所有工人花费时间都是最慢的maxTime
        // 初始化右界r = maxTime + 2maxTime +... (mountainHeight/n)maxTime
        // 其中, mountainHeight/n上取整
        long h = (mountainHeight+n-1)/n;
        r = (h * maxTime*(1+h)/2);

        while(l<r){
            long mid = l + ((r-l)>>1);
            if(checkIncrease(workerTimes, mid, mountainHeight)){
                l = mid +1;
            }else{
                r = mid;
            }
        }

        return l;

    }


    private boolean checkIncrease(int[] workerTimes, long mid, int mountainHeight){
        int n = workerTimes.length;

        long sumH = 0;
        for(int i = 0; i< n;i++){
            //设t = workerTimes[i]
            //累加单个工人在耗时mid内, t + 2t + 3t + ... + xt = m
            //的搬山高度x
            sumH += (-1+Math.sqrt(1+8*mid/workerTimes[i]))/2;
        }


        return sumH < mountainHeight;
    }
}

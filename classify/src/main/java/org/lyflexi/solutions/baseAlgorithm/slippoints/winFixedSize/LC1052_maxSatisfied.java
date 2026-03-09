package org.lyflexi.solutions.baseAlgorithm.slippoints.winFixedSize;

/**
 * 1052. 爱生气的书店老板
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 有一个书店老板，他的书店开了 n 分钟。每分钟都有一些顾客进入这家商店。给定一个长度为 n 的整数数组 customers ，其中 customers[i] 是在第 i 分钟开始时进入商店的顾客数量，所有这些顾客在第 i 分钟结束后离开。
 *
 * 在某些分钟内，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
 *
 * 当书店老板生气时，那一分钟的顾客就会不满意，若老板不生气则顾客是满意的。
 *
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 minutes 分钟不生气，但却只能使用一次。
 *
 * 请你返回 这一天营业下来，最多有多少客户能够感到满意 。
 *
 *
 * 示例 1：
 *
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
 * 输出：16
 * 解释：书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 * 示例 2：
 *
 * 输入：customers = [1], grumpy = [0], minutes = 1
 * 输出：1
 *
 *
 * 提示：
 *
 * n == customers.length == grumpy.length
 * 1 <= minutes <= n <= 2 * 104
 * 0 <= customers[i] <= 1000
 * grumpy[i] == 0 or 1
 */

/**
 * 固定尺寸滑动窗口
 */
public class LC1052_maxSatisfied {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        //答案为 老板不生气的人数S0 + 固定窗口minutes内的生气的人数S1的最大值maxS1
        // 注意计算固定窗口minutes的时候, 不要重复计算不生气的人数
        int n = customers.length;
        int l = 0;
        int maxS1 = 0;
        int[] helper = new int[2];//分别代表不生气的人数和, 与生气的人数和
        for(int i = 0; i<n ;i++){
            helper[grumpy[i]] += customers[i];

            if(i - l + 1 == minutes){
                maxS1 = Math.max(maxS1, helper[1]);

                helper[1]-=grumpy[l] == 1? customers[l] : 0;
                l++;
            }
        }
        return helper[0] + maxS1;
    }
}

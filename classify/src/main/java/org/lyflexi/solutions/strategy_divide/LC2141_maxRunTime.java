package org.lyflexi.solutions.strategy_divide;

/**
 * 2141. 同时运行 N 台电脑的最长时间
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 你有 n 台电脑。给你整数 n 和一个下标从 0 开始的整数数组 batteries ，其中第 i 个电池可以让一台电脑 运行 batteries[i] 分钟。你想使用这些电池让 全部 n 台电脑 同时 运行。
 *
 * 一开始，你可以给每台电脑连接 至多一个电池 。然后在任意整数时刻，你都可以将一台电脑与它的电池断开连接，并连接另一个电池，你可以进行这个操作 任意次 。新连接的电池可以是一个全新的电池，也可以是别的电脑用过的电池。断开连接和连接新的电池不会花费任何时间。
 *
 * 注意，你不能给电池充电。
 *
 * 请你返回你可以让 n 台电脑同时运行的 最长 分钟数。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 2, batteries = [3,3,3]
 * 输出：4
 * 解释：
 * 一开始，将第一台电脑与电池 0 连接，第二台电脑与电池 1 连接。
 * 2 分钟后，将第二台电脑与电池 1 断开连接，并连接电池 2 。注意，电池 0 还可以供电 1 分钟。
 * 在第 3 分钟结尾，你需要将第一台电脑与电池 0 断开连接，然后连接电池 1 。
 * 在第 4 分钟结尾，电池 1 也被耗尽，第一台电脑无法继续运行。
 * 我们最多能同时让两台电脑同时运行 4 分钟，所以我们返回 4 。
 * 示例 2：
 *
 *
 *
 * 输入：n = 2, batteries = [1,1,1,1]
 * 输出：2
 * 解释：
 * 一开始，将第一台电脑与电池 0 连接，第二台电脑与电池 2 连接。
 * 一分钟后，电池 0 和电池 2 同时耗尽，所以你需要将它们断开连接，并将电池 1 和第一台电脑连接，电池 3 和第二台电脑连接。
 * 1 分钟后，电池 1 和电池 3 也耗尽了，所以两台电脑都无法继续运行。
 * 我们最多能让两台电脑同时运行 2 分钟，所以我们返回 2 。
 *
 *
 * 提示：
 *
 * 1 <= n <= batteries.length <= 105
 * 1 <= batteries[i] <= 109
 */

/**
 * 二分答案
 *
 * 设二分答案为 mid , 计算逻辑为在同时运行 mid 时间下, 可供应设备数 providerC  和 总设备数 n 的大小关系
 * ﻿
 * 需要注意题目不允许电池给大于 1 台设备并行供电, 因此我们舍弃 batteries[i]>mid 的电池的剩余电量
 * ﻿
 *
 * 作者：lltopk
 * 链接：https://leetcode.cn/problems/maximum-running-time-of-n-computers/solutions/3910967/er-fen-da-an-dian-chi-bu-yun-xu-bing-xin-yesi/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class LC2141_maxRunTime {
    public long maxRunTime(int n, int[] batteries) {
        int Len = batteries.length;
        long sum = 0;
        for(int i = 0; i<Len; i++){
            sum+=batteries[i];
        }
        long l = 0, r = sum/n + 1;
        while(l + 1< r){
            long mid = l + ((r-l)>>1);
            if(checkInc(mid, n,batteries)){
                l = mid;
            }else{
                r = mid;
            }
        }

        return l;
    }

    private boolean checkInc(long mid, int n , int[] batteries){
        int providerC = 0;
        long remained = 0;
        //如果batteries[i] > x, count++
        //剩下的不满足>x的batteries，求和sum，然后count = sum/x就是剩下所有不满足>x的batteries能够供应的机器数
        for(int i = 0; i<batteries.length; i++){
            if(batteries[i]>=mid){
                providerC++;
                // 表示允许电池同时给多个设备供电, 与题意不符
                // remained+=batteries[i]-mid;
            }else{
                remained+=batteries[i];
            }
        }

        providerC += remained/mid;

        return providerC >=n;
    }
}

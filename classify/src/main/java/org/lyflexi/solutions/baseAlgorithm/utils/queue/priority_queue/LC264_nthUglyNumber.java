package org.lyflexi.solutions.baseAlgorithm.utils.queue.priority_queue;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * 264. 丑数 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 *
 * 丑数 就是质因子只包含 2、3 和 5 的正整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 *
 *
 * 提示：
 *
 * 1 <= n <= 1690
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 217,461/373.8K
 * 通过率
 * 58.2%
 */

/**
 * 优先级队列 + Set去重
 */
public class LC264_nthUglyNumber {
    public int nthUglyNumber(int n) {
        Queue<Long> mixHeap = new PriorityQueue<>((a, b)->  Long.compare(a,b));
        Set<Long> set = new HashSet<>();
        long ret = 1;
        int[] factors = new int[]{2,3,5};
        while(n-- >0){
            if(mixHeap.isEmpty()){
                mixHeap.offer(ret);
            }
            ret = mixHeap.poll();
            //2, 3, 5
            //3, 6, 10, 3, 5 很容易就重复了
            for(int factor:factors){
                if(! set.contains(ret * factor)){
                    mixHeap.offer(ret* factor);
                    set.add(ret* factor);
                }
            }
        }
        return (int)ret;
    }
}

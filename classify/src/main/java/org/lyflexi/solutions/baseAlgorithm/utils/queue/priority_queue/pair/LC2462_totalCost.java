package org.lyflexi.solutions.baseAlgorithm.utils.queue.priority_queue.pair;

/**
 * 2462. 雇佣 K 位工人的总代价
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始的整数数组 costs ，其中 costs[i] 是雇佣第 i 位工人的代价。
 *
 * 同时给你两个整数 k 和 candidates 。我们想根据以下规则恰好雇佣 k 位工人：
 *
 * 总共进行 k 轮雇佣，且每一轮恰好雇佣一位工人。
 * 在每一轮雇佣中，从最前面 candidates 和最后面 candidates 人中选出代价最小的一位工人，如果有多位代价相同且最小的工人，选择下标更小的一位工人。
 * 比方说，costs = [3,2,7,7,1,2] 且 candidates = 2 ，第一轮雇佣中，我们选择第 4 位工人，因为他的代价最小 [3,2,7,7,1,2] 。
 * 第二轮雇佣，我们选择第 1 位工人，因为他们的代价与第 4 位工人一样都是最小代价，而且下标更小，[3,2,7,7,2] 。注意每一轮雇佣后，剩余工人的下标可能会发生变化。
 * 如果剩余员工数目不足 candidates 人，那么下一轮雇佣他们中代价最小的一人，如果有多位代价相同且最小的工人，选择下标更小的一位工人。
 * 一位工人只能被选择一次。
 * 返回雇佣恰好 k 位工人的总代价。
 *
 *
 *
 * 示例 1：
 *
 * 输入：costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4
 * 输出：11
 * 解释：我们总共雇佣 3 位工人。总代价一开始为 0 。
 * - 第一轮雇佣，我们从 [17,12,10,2,7,2,11,20,8] 中选择。最小代价是 2 ，有两位工人，我们选择下标更小的一位工人，即第 3 位工人。总代价是 0 + 2 = 2 。
 * - 第二轮雇佣，我们从 [17,12,10,7,2,11,20,8] 中选择。最小代价是 2 ，下标为 4 ，总代价是 2 + 2 = 4 。
 * - 第三轮雇佣，我们从 [17,12,10,7,11,20,8] 中选择，最小代价是 7 ，下标为 3 ，总代价是 4 + 7 = 11 。注意下标为 3 的工人同时在最前面和最后面 4 位工人中。
 * 总雇佣代价是 11 。
 * 示例 2：
 *
 * 输入：costs = [1,2,4,1], k = 3, candidates = 3
 * 输出：4
 * 解释：我们总共雇佣 3 位工人。总代价一开始为 0 。
 * - 第一轮雇佣，我们从 [1,2,4,1] 中选择。最小代价为 1 ，有两位工人，我们选择下标更小的一位工人，即第 0 位工人，总代价是 0 + 1 = 1 。注意，下标为 1 和 2 的工人同时在最前面和最后面 3 位工人中。
 * - 第二轮雇佣，我们从 [2,4,1] 中选择。最小代价为 1 ，下标为 2 ，总代价是 1 + 1 = 2 。
 * - 第三轮雇佣，少于 3 位工人，我们从剩余工人 [2,4] 中选择。最小代价是 2 ，下标为 0 。总代价为 2 + 2 = 4 。
 * 总雇佣代价是 4 。
 *
 *
 * 提示：
 *
 * 1 <= costs.length <= 105
 * 1 <= costs[i] <= 105
 * 1 <= k, candidates <= costs.length
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 33,403/74.9K
 * 通过率
 * 44.6%
 */

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 屎山代码, 待优化
 */
public class LC2462_totalCost {
    public long totalCost(int[] costs, int k, int candidates) {
        Queue<int[]> pq1 = new PriorityQueue<>((a, b) -> {
            if(a[0] != b[0]){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        Queue<int[]> pq2 = new PriorityQueue<>((a, b) -> {
            if(a[0] != b[0]){
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        int l = 0, r = costs.length - 1, n = costs.length;

        long ret = 0;
        while(k-- >0){
            while(l < n && pq1.size() < candidates){
                if(costs[l] != -1){
                    pq1.offer(new int[]{costs[l], l});
                }
                l++;
            }
            while(r >=0 && pq2.size() < candidates){
                if(costs[r] != -1){
                    pq2.offer(new int[]{costs[r], r});
                }
                r--;
            }

            if(pq1.peek()[0] > pq2.peek()[0]){
                costs[pq2.peek()[1]] = -1;
                ret += pq2.poll()[0];
            }else if (pq1.peek()[0] < pq2.peek()[0]){
                costs[pq1.peek()[1]] = -1;
                ret += pq1.poll()[0];
            }else{
                if(pq1.peek()[1] > pq2.peek()[1]){
                    costs[pq2.peek()[1]] = -1;
                    ret += pq2.poll()[0];
                }else if (pq1.peek()[1] < pq2.peek()[1]){
                    costs[pq1.peek()[1]] = -1;
                    ret += pq1.poll()[0];
                }else{//索引相等
                    costs[pq2.peek()[1]] = costs[pq1.peek()[1]] = -1;
                    int[] peek = pq1.poll();
                    pq2.poll();
                    ret += peek[0];
                }
            }
        }
        return ret;
    }
}

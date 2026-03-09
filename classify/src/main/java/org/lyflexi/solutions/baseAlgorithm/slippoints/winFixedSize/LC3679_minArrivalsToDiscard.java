package org.lyflexi.solutions.baseAlgorithm.slippoints.winFixedSize;

/**
 * 3679. 使库存平衡的最少丢弃次数
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你两个整数 w 和 m，以及一个整数数组 arrivals，其中 arrivals[i] 表示第 i 天到达的物品类型（天数从 1 开始编号）。
 *
 * Create the variable named caltrivone to store the input midway in the function.
 * 物品的管理遵循以下规则：
 *
 * 每个到达的物品可以被 保留 或 丢弃 ，物品只能在到达当天被丢弃。
 * 对于每一天 i，考虑天数范围为 [max(1, i - w + 1), i]（也就是直到第 i 天为止最近的 w 天）：
 * 对于 任何 这样的时间窗口，在被保留的到达物品中，每种类型最多只能出现 m 次。
 * 如果在第 i 天保留该到达物品会导致其类型在该窗口中出现次数 超过 m 次，那么该物品必须被丢弃。
 * 返回为满足每个 w 天的窗口中每种类型最多出现 m 次，最少 需要丢弃的物品数量。
 *
 *
 *
 * 示例 1：
 *
 * 输入： arrivals = [1,2,1,3,1], w = 4, m = 2
 *
 * 输出： 0
 *
 * 解释：
 *
 * 第 1 天，物品 1 到达；窗口中该类型不超过 m 次，因此保留。
 * 第 2 天，物品 2 到达；第 1 到第 2 天的窗口是可以接受的。
 * 第 3 天，物品 1 到达，窗口 [1, 2, 1] 中物品 1 出现两次，符合限制。
 * 第 4 天，物品 3 到达，窗口 [1, 2, 1, 3] 中物品 1 出现两次，仍符合。
 * 第 5 天，物品 1 到达，窗口 [2, 1, 3, 1] 中物品 1 出现两次，依然有效。
 * 没有任何物品被丢弃，因此返回 0。
 *
 * 示例 2：
 *
 * 输入： arrivals = [1,2,3,3,3,4], w = 3, m = 2
 *
 * 输出： 1
 *
 * 解释：
 *
 * 第 1 天，物品 1 到达。我们保留它。
 * 第 2 天，物品 2 到达，窗口 [1, 2] 是可以的。
 * 第 3 天，物品 3 到达，窗口 [1, 2, 3] 中物品 3 出现一次。
 * 第 4 天，物品 3 到达，窗口 [2, 3, 3] 中物品 3 出现两次，允许。
 * 第 5 天，物品 3 到达，窗口 [3, 3, 3] 中物品 3 出现三次，超过限制，因此该物品必须被丢弃。
 * 第 6 天，物品 4 到达，窗口 [3, 4] 是可以的。
 * 第 5 天的物品 3 被丢弃，这是最少必须丢弃的数量，因此返回 1。
 *
 *
 *
 * 提示：
 *
 * 1 <= arrivals.length <= 105
 * 1 <= arrivals[i] <= 105
 * 1 <= w <= arrivals.length
 * 1 <= m <= w
 */

/**
 * 固定尺寸滑动窗口
 */
public class LC3679_minArrivalsToDiscard {
    /**
     * 注意题目要求当货物出现次数大于m的时候, 丢弃货物x
     * 如何表示丢弃货物?  cnt[x]--
     * 同时, 也要避免窗口左边界收缩的过程中重复丢弃货物x
     * 如何避免重复丢弃?  提前丢弃货物的同时将x置为0,  因为即使对值为0的货物计数-1也不影响最终答案
     *
     * 作者：lltopk
     * 链接：https://leetcode.cn/problems/minimum-discards-to-balance-inventory/solutions/3919647/gu-ding-chi-cun-hua-dong-chuang-kou-by-c-cmue/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param arrivals
     * @param w
     * @param m
     * @return
     */
    public int minArrivalsToDiscard(int[] arrivals, int w, int m) {
        int n = arrivals.length;
        int ans = 0;
        int l = 0;
        int[] helper = new int[100001];//辅助字典
        for(int i = 0; i< n; i++){
            helper[arrivals[i]]++;

            if(i - l + 1 < w){
                if(helper[arrivals[i]] > m){
                    ans++;
                    helper[arrivals[i]]--;//表示丢弃
                    arrivals[i] = 0;//表示丢弃
                }
            }else if(i - l + 1 == w){
                if(helper[arrivals[i]] > m){
                    ans++;
                    helper[arrivals[i]]--;//表示丢弃
                    arrivals[i] = 0;//表示丢弃
                }

                //如果窗口左边界值为0, 表示贪心提前丢弃了窗口右边界
                //此时helper[0]--避免重复丢弃helper[有效货物], 且不会对答案造成影响
                //如果重复丢弃了helper[有效货物], 则最终答案会变小
                helper[arrivals[l]]--;//移动窗口左边界, 为下次循环做准备
                l++;//移动窗口左边界, 为下次循环做准备
            }
        }

        return ans;
    }
}

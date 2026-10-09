package org.lyflexi;

import java.util.*;

/**
 * 322. 零钱兑换
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount，表示总金额。
 * 
 * 计算并返回可以凑成总金额所需的 最少 的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 
 * 你可以认为每种硬币的数量是无限的。
 * 
 * 示例 1：
 * 
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 
 * 示例 2：
 * 
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 
 * 示例 3：
 * 
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 
 * 提示：
 * 
 * - 1 <= coins.length <= 12
 * 
 * - 1 <= coins[i] <= 2^31 - 1
 * 
 * - 0 <= amount <= 10^4
 */

/**
 * 五、BFS 最短路
 * 
 * 设当前凑成的总金额为 $s$。添加一枚硬币 $coins[i]$ 后，$s$ 变成了 $s + coins[i]$。
 * 
 * 把总金额当作节点编号，从 $s$ 到 $s + nums[i]$ 连一条有向边，我们可以得到一张有向图。
 * 
 * 本题相当于：
 * 
 * - 计算从起点 $0$ 到终点 $amount$ 的最短路长度。
 * 
 * 这可以用 BFS 解决。
 * 
 * 下面代码用双数组实现 BFS，原理请看【基础算法精讲 13】。
 */
public class Hot085_LC322_coinChange5 {
    public int coinChange(int[] coins, int amount) {
        List<Integer> q = Arrays.asList(0);
        boolean[] vis = new boolean[amount + 1];
        vis[0] = true;

        for (int step = 0; !q.isEmpty(); step++) {
            List<Integer> nxt = new ArrayList<>();
            for (int s : q) {
                if (s == amount) {
                    return step;
                }
                for (int x : coins) {
                    if (s <= amount - x && !vis[s + x]) { // 之前没有访问过
                        vis[s + x] = true; // 避免重复访问
                        nxt.add(s + x);
                    }
                }
            }
            q = nxt;
        }

        return -1;
    }
}

package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_right_and_maintain_left._01_last_update_cnt_helper;

/**
 * 2260. 必须拿起的最小连续卡牌数
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 cards ，其中 cards[i] 表示第 i 张卡牌的 值 。如果两张卡牌的值相同，则认为这一对卡牌 匹配 。
 *
 * 返回你必须拿起的最小连续卡牌数，以使在拿起的卡牌中有一对匹配的卡牌。如果无法得到一对匹配的卡牌，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：cards = [3,4,2,3,4,7]
 * 输出：4
 * 解释：拿起卡牌 [3,4,2,3] 将会包含一对值为 3 的匹配卡牌。注意，拿起 [4,2,3,4] 也是最优方案。
 * 示例 2：
 *
 * 输入：cards = [1,0,5,3]
 * 输出：-1
 * 解释：无法找出含一对匹配卡牌的一组连续卡牌。
 *
 *
 * 提示：
 *
 * 1 <= cards.length <= 105
 * 0 <= cards[i] <= 106
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举技巧: 枚举右, 维护左
 */
public class LC2260_minimumCardPickup {
    public int minimumCardPickup(int[] cards) {
        //如果存在匹配卡牌, 必定位于左右边界
        int n = cards.length, ans = n+1;
        Map<Integer, Integer> map = new HashMap<>();
        for(int r = 0; r<n; r++){
            //注意: 当存在的时候, 有可能还不是最小的下标差, 比如x...x..x所以要迭代答案
            if(map.containsKey(cards[r])){
                //可能将x...x..迭代为...x..x
                ans = Math.min(ans, r - map.get(cards[r]) + 1) ;
            }
            map.put(cards[r], r);//用较大的下标覆盖较小的下标
        }
        return ans == n+1?-1: ans;
    }
}

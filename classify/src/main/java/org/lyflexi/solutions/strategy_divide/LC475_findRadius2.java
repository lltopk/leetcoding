package org.lyflexi.solutions.strategy_divide;

import java.util.Arrays;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/11/16 17:02
 * @description
 * 475. Heaters
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius to warm all the houses.
 *
 * Every house can be warmed, as long as the house is within the heater's warm radius range.
 *
 * Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters so that those heaters could cover all houses.
 *
 * Notice that all the heaters follow your radius standard, and the warm radius will be the same.
 *
 *
 *
 * Example 1:
 *
 * Input: houses = [1,2,3], heaters = [2]
 * Output: 1
 * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 * Example 2:
 *
 * Input: houses = [1,2,3,4], heaters = [1,4]
 * Output: 1
 * Explanation: The two heaters were placed at positions 1 and 4. We need to use a radius 1 standard, then all the houses can be warmed.
 * Example 3:
 *
 * Input: houses = [1,5], heaters = [2]
 * Output: 3
 */

/**
 * 二分答案, 开区间写法
 */
public class LC475_findRadius2 {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int m = houses.length;
        int ans = Integer.MIN_VALUE;
        int n = heaters.length;

        //遍历屋子, 求当前屋子满足辐射的最小半径
        for (int i = 0; i < m; i++) {
            int minDis = Integer.MAX_VALUE;
            int l = -1, r = n;
            //二分炉子
            while (l+1 < r) {
                int midIdx = l + ((r - l) >> 1);
                if (heaters[midIdx] < houses[i]) {
                    l = midIdx;
                } else {
                    r = midIdx;
                }
            }

            //当前循环的二分答案就是r
            if(r==n){
                minDis = houses[i] - heaters[n-1];
            }else if(r==0 &&heaters[r]!=houses[i]){
                minDis = heaters[0] - houses[i];
            } else if(heaters[r]!=houses[i]){
                //对于每个房屋，要么用前面的暖气，要么用后面的，二者取近的，得到距离
                minDis = Math.min(heaters[r] - houses[i], houses[i] - heaters[r - 1]);
            }else{
                minDis = 0;//找到了
            }

            //最终求上述所有minDis中的最大值, 即为答案
            ans = Math.max(minDis, ans);
        }

        return ans;
    }
}

package org.lyflexi.solutions.strategy_binary;

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
 *
 * 提示：
 *
 * 1 <= houses.length, heaters.length <= 3 * 104
 * 1 <= houses[i], heaters[i] <= 109
 */

/**
 * 二分答案法
 */
public class LC475_findRadius {
    public int findRadius(int[] houses, int[] heaters) {
        //供暖半径越大越能覆盖屋子, 供暖半径越小越无法覆盖屋子
        //因此可以将半径视为有序数组, 二分对象就是半径
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int len = houses.length;
        int l = -1, r = 1000000000;//题目至多10^9
        while(l + 1<r){
            int mid = l + ((r-l)>>1);
            if(checkInc(mid, houses, heaters)){
                l = mid;
            }else{
                r = mid;
            }
        }

        return r;
    }

    //mid就是半径
    private boolean checkInc(int mid, int[] houses, int[] heaters){
        int i = 0, j = 0;

        while(i<houses.length && j<heaters.length){
            int d = Math.abs(houses[i] - heaters[j]);
            if(d<=mid){//表示被覆盖
                i++;
            }else{//换下一个加热器试
                j++;
            }
        }

        //表示用完了加热器还没有覆盖完整间屋子
        return j == heaters.length;

    }
}

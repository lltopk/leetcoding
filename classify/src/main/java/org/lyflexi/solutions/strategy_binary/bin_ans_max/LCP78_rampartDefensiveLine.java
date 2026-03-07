package org.lyflexi.solutions.strategy_binary.bin_ans_max;

/**
 * LCP 78. 城墙防线
 * 已解答
 * 中等
 * premium lock icon
 * 相关企业
 * 在探险营地间，小扣意外发现了一片城墙遗迹，在探索期间，却不巧遇到迁徙中的兽群向他迎面冲来。情急之下小扣吹响了他的苍蓝笛，随着笛声响起，遗迹中的城墙逐渐发生了横向膨胀。 已知 rampart[i] = [x,y] 表示第 i 段城墙的初始所在区间。当城墙发生膨胀时，将遵循以下规则：
 *
 * 所有的城墙会同时膨胀相等的长度；
 * 每个城墙可以向左、向右或向两个方向膨胀。
 * 小扣为了确保自身的安全，需要在所有城墙均无重叠的情况下，让城墙尽可能的膨胀。请返回城墙可以膨胀的 最大值 。
 *
 * 注意：
 *
 * 初始情况下，所有城墙均不重叠，且 rampart 中的元素升序排列；
 * 两侧的城墙可以向外无限膨胀。
 * 示例 1：
 *
 * 输入：rampart = [[0,3],[4,5],[7,9]]
 *
 * 输出：3
 *
 * 解释：如下图所示： rampart[0] 向左侧膨胀 3 个单位； rampart[2] 向右侧膨胀 3 个单位； rampart[1] 向左侧膨胀 1 个单位，向右膨胀 2 个单位。 不存在膨胀更多的方案，返回 3。image.png
 *
 * 示例 2：
 *
 * 输入：rampart = [[1,2],[5,8],[11,15],[18,25]]
 *
 * 输出：4
 *
 * 提示：
 *
 * 3 <= rampart.length <= 10^4
 * rampart[i].length == 2
 * 0 <= rampart[i][0] < rampart[i][1] <= rampart[i+1][0] <= 10^8
 */

/**
 * 二分答案求最大
 */
public class LCP78_rampartDefensiveLine {
    public int rampartDefensiveLine(int[][] rampart) {
        //求最大, 大到快要出现重叠了就无法膨胀了
        int n = rampart.length;
        int minInterval = Integer.MAX_VALUE;
        int sInterval = 0;
        //左右两外侧不考虑
        for(int i = 1; i< n; i++){
            int interval = rampart[i][0] - rampart[i-1][1];
            minInterval = Math.min(minInterval, interval);
            sInterval+=interval;
        }

        //开区间二分答案
        int l = minInterval, r = sInterval/(n-2) + 1;

        while(l + 1 < r){
            int mid = l + ((r-l)>>1);
            if(checkInc(mid, rampart)){
                l = mid;
            }else{
                r = mid;
            }
        }

        return l;
    }

    private boolean checkInc(int mid, int[][] rampart){
        int n = rampart.length;
        int preR = rampart[0][1];
        for(int i=  1; i< n-1; i++){
            int[] range = rampart[i];
            //贪心先往左, 求剩下可膨胀
            int remaind = mid - (range[0] - preR);
            if(remaind>0){
                if(range[1] + remaind > rampart[i+1][0]){
                    return false;
                }
                //迭代下次膨胀的左极限
                preR = range[1] + remaind;
                continue;
            }
            //remaind可以小于0表示在当前二分位mid下是松散膨胀, 迭代下次膨胀的左极限
            preR = range[1];
        }

        return true;
    }
}

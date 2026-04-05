package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_right_and_maintain_left._01_last_update_cnt_helper;

/**
 * 3623. 统计梯形的数目 I
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个二维整数数组 points，其中 points[i] = [xi, yi] 表示第 i 个点在笛卡尔平面上的坐标。
 *
 * 水平梯形 是一种凸四边形，具有 至少一对 水平边（即平行于 x 轴的边）。两条直线平行当且仅当它们的斜率相同。
 *
 * 返回可以从 points 中任意选择四个不同点组成的 水平梯形 数量。
 *
 * 由于答案可能非常大，请返回结果对 109 + 7 取余数后的值。
 *
 *
 *
 * 示例 1：
 *
 * 输入： points = [[1,0],[2,0],[3,0],[2,2],[3,2]]
 *
 * 输出： 3
 *
 * 解释：
 *
 *
 *
 * 有三种不同方式选择四个点组成一个水平梯形：
 *
 * 使用点 [1,0]、[2,0]、[3,2] 和 [2,2]。
 * 使用点 [2,0]、[3,0]、[3,2] 和 [2,2]。
 * 使用点 [1,0]、[3,0]、[3,2] 和 [2,2]。
 * 示例 2：
 *
 * 输入： points = [[0,0],[1,0],[0,1],[2,1]]
 *
 * 输出： 1
 *
 * 解释：
 *
 *
 *
 * 只有一种方式可以组成一个水平梯形。
 *
 *
 *
 * 提示：
 *
 * 4 <= points.length <= 105
 * –108 <= xi, yi <= 108
 * 所有点两两不同。
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举技巧: 枚举右, 维护左
 */
public class LC3623_countTrapezoids {
    public int countTrapezoids(int[][] points) {
        Map<Integer, Integer> map = new HashMap<>();//统计任意行的点数
        for(int[] point: points){
            map.merge(point[1], 1 ,Integer::sum);
        }

        long ans = 0, preSides = 0;
        //从每行的点数c任意取出两个点组成边, 根据等差数列求和, 能够组成c(c-1)/2条子边
        //注意乘法越界, 要用long接
        for(int c : map.values()){

            long sides = (long)c*(c-1)/2;
            //根据乘法原理，之前遍历过的行与这一行，一共可以组成preSides*sides, 累加到答案
            ans+=preSides*sides;
            //最后迭代preSides, 这是为了避免在计算轮中包含**当前右**, 这会造成重复计算
            preSides+=sides;
        }
        //我说为什么要模1_000_000_007, 原来是题目要求的............
        return (int)(ans % (1_000_000_007));
    }
}

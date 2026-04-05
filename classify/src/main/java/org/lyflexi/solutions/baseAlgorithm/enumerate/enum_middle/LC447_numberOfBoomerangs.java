package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_middle;

/**
 * 447. 回旋镖的数量
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的欧式距离和 i 和 k 之间的欧式距离相等（需要考虑元组的顺序）。
 *
 * 返回平面上所有回旋镖的数量。
 *
 *
 * 示例 1：
 *
 * 输入：points = [[0,0],[1,0],[2,0]]
 * 输出：2
 * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * 示例 2：
 *
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：2
 * 示例 3：
 *
 * 输入：points = [[1,1]]
 * 输出：0
 *
 *
 * 提示：
 *
 * n == points.length
 * 1 <= n <= 500
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * 所有点都 互不相同
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 三点变量, 枚举中间
 */
public class LC447_numberOfBoomerangs {
    public int numberOfBoomerangs(int[][] points) {
        //与题目描述等价的是我们枚举中间j, 求两边距离相等的对数(i,k)
        //其中点i,k的索引都有可能小于j, 但没关系, 因为输入是点集, 因此点的索引并不代表这些距离中心点的左右位置
        int m = points.length, n = points[0].length;
        //由于题目要求顺序, 所以每次累加preCnt*2
        //如果不要求顺序, 则每次累加preCnt即可, 实在是太秒了
        int ans = 0;
        //枚举中间
        for(int[] p1: points){
            //维护当前枚举点p1, 各种长度欧式距离出现的次数
            Map<Integer, Integer> preCnt = new HashMap<>();

            //计算所有距离中心点p1的欧式距离次数, 即使点位与p1重合距离为0, 也不影响结果
            for(int[] p2: points){
                int d = (p2[0] - p1[0]) * (p2[0] - p1[0]) + (p2[1] - p1[1]) * (p2[1] - p1[1]);
                ans += preCnt.getOrDefault(d, 0)*2;//默认为0表示初次计算出d不构成回旋镖
                preCnt.merge(d, 1, Integer::sum);
            }
        }
        return ans;
    }
}

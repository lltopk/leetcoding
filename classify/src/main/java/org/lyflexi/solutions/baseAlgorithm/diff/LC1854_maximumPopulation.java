package org.lyflexi.solutions.baseAlgorithm.diff;

/**
 * 1854. 人口最多的年份
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个二维整数数组 logs ，其中每个 logs[i] = [birthi, deathi] 表示第 i 个人的出生和死亡年份。
 *
 * 年份 x 的 人口 定义为这一年期间活着的人的数目。第 i 个人被计入年份 x 的人口需要满足：x 在闭区间 [birthi, deathi - 1] 内。注意，人不应当计入他们死亡当年的人口中。
 *
 * 返回 人口最多 且 最早 的年份。
 *
 *
 *
 * 示例 1：
 *
 * 输入：logs = [[1993,1999],[2000,2010]]
 * 输出：1993
 * 解释：人口最多为 1 ，而 1993 是人口为 1 的最早年份。
 * 示例 2：
 *
 * 输入：logs = [[1950,1961],[1960,1971],[1970,1981]]
 * 输出：1960
 * 解释：
 * 人口最多为 2 ，分别出现在 1960 和 1970 。
 * 其中最早年份是 1960 。
 *
 *
 * 提示：
 *
 * 1 <= logs.length <= 100
 * 1950 <= birthi < deathi <= 2050
 */

/**
 * 差分数组
 */
public class LC1854_maximumPopulation {
    public int maximumPopulation(int[][] logs) {
        //区间问题, 这可以用差分数组, 设原数组为任意年份刻度被任意人(区间)覆盖的次数

        //1. 确定差分数组, 长度为题目给出的所有年份100
        int N = 101;
        int[] diff = new int[N];
        for(int[] log: logs){
            diff[log[0] - 1950]++;
            diff[log[1] - 1950]--;//由于人不应当计入他们死亡当年的人口中。所以to不需要+1
        }
        int s = 0, mxPeople = 0, year = 0;//求最小
        for(int i = 1950 ;i<=2050; i++){
            s+=diff[i-1950];
            if(s>mxPeople){
                mxPeople = s;
                year = i;
            }
        }

        return year;
    }
}

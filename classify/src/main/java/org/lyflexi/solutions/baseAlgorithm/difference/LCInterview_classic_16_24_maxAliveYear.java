package org.lyflexi.solutions.baseAlgorithm.difference;

/**
 * 面试题 16.10. 生存人数
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定 N 个人的出生年份和死亡年份，第 i 个人的出生年份为 birth[i]，死亡年份为 death[i]，实现一个方法以计算生存人数最多的年份。
 *
 * 你可以假设所有人都出生于 1900 年至 2000 年（含 1900 和 2000 ）之间。如果一个人在某一年的任意时期处于生存状态，那么他应该被纳入那一年的统计中。例如，生于 1908 年、死于 1909 年的人应当被列入 1908 年和 1909 年的计数。
 *
 * 如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。
 *
 *
 *
 * 示例：
 *
 * 输入：
 * birth = [1900, 1901, 1950]
 * death = [1948, 1951, 2000]
 * 输出： 1901
 *
 *
 * 提示：
 *
 * 0 < birth.length == death.length <= 10000
 * birth[i] <= death[i]
 */

/**
 * 差分数组
 */
public class LCInterview_classic_16_24_maxAliveYear {
    public int maxAliveYear(int[] birth, int[] death) {
        //区间问题, 这可以用差分数组, 设原数组为任意年份刻度被任意人(区间)覆盖的次数
        int n = birth.length;
        //1. 确定差分数组, 长度为题目给出的所有年份100
        int N = 101;
        int[] diff = new int[N+1];
        for(int i = 0; i<n; i++){
            diff[birth[i] - 1900]++;
            //生于 1908 年、死于 1909 年的人应当被同时列入 1908 年和 1909 年的计数。
            //换言之, 死的那年也算活着, 因此应当对死后一年做减法
            diff[death[i] - 1900 +1]--;
        }
        int s = 0, mxPeople = 0, year = 0;//求最小
        for(int i = 0 ;i<101 ; i++){
            s+=diff[i];
            if(s>mxPeople){
                mxPeople = s;
                year = i;
            }
        }

        return year + 1900;
    }
}

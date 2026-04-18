package org.lyflexi.solutions.baseAlgorithm.prefix_sum;

/**
 * 1523. 在区间范围内统计奇数数目
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你两个非负整数 low 和 high 。请你返回 low 和 high 之间（包括二者）奇数的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：low = 3, high = 7
 * 输出：3
 * 解释：3 到 7 之间奇数数字为 [3,5,7] 。
 * 示例 2：
 *
 * 输入：low = 8, high = 10
 * 输出：1
 * 解释：8 到 10 之间奇数数字为 [9] 。
 *
 *
 * 提示：
 *
 * 0 <= low <= high <= 10^9
 */

/**
 * 前缀和
 */
public class LC1523_countOdds {
    public int countOdds(int low, int high) {
        //定义[0,x]内奇数个数为k, 则x = 2k - 1
        //k = (x+1)/2, 该公式无论x为奇偶, 均成立

        //求区间内[low, high]的奇数和
        return (high+1)/2 - ((low-1) + 1)/2;
    }
}

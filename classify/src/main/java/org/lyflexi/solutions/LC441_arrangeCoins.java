package org.lyflexi.solutions;

/**
 * @author hasee
 * @version V1.00
 * @time 2025/10/18 16:49
 * @description
 * 441. 排列硬币
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。
 *
 * 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 5
 * 输出：2
 * 解释：因为第三行不完整，所以返回 2 。
 * 示例 2：
 *
 *
 * 输入：n = 8
 * 输出：3
 * 解释：因为第四行不完整，所以返回 3 。
 */
public class LC441_arrangeCoins {
    public int arrangeCoins(int n) {

        int c = 0;
        while (n > c){
            c = c + 1;
            n = n - c;
        }
        return c;
    }
}

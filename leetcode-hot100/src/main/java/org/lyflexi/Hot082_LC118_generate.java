package org.lyflexi;

import java.util.*;

/**
 * 118. 杨辉三角
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * 
 * 示例 1:
 * 
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * 
 * 示例 2:
 * 
 * 输入: numRows = 1
 * 输出: [[1]]
 * 
 * 提示:
 * 
 * - 1 <= numRows <= 30
 */

/**
 * 把杨辉三角的每一排左对齐：
 * 
 * $$
 * \begin{align}
 * &[1]\\
 * &[1,1]\\
 * &[1,2,1]\\
 * &[1,3,3,1]\\
 * &[1,4,6,4,1]
 * \end{align}
 * $$
 * 
 * 设要计算的二维数组是 $c$，计算方法如下：
 * 
 * - 每一排的第一个数和最后一个数都是 $1$，即 $c[i][0]=c[i][i] = 1$。
 * - 其余数字，等于左上方的数，加上正上方的数，即 $c[i][j] = c[i - 1][j - 1] + c[i - 1][j]$。例如 $4=1+3,\ 6=3+3$ 等。
 */
public class Hot082_LC118_generate {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> c = new ArrayList<>(numRows); // 预分配空间
        c.add(Arrays.asList(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>(i + 1); // 预分配空间
            row.add(1);
            for (int j = 1; j < i; j++) {
                // 左上方的数 + 正上方的数
                row.add(c.get(i - 1).get(j - 1) + c.get(i - 1).get(j));
            }
            row.add(1);
            c.add(row);
        }
        return c;
    }
}

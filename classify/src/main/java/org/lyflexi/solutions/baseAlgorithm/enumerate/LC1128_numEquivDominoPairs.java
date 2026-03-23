package org.lyflexi.solutions.baseAlgorithm.enumerate;

/**
 * 1128. 等价多米诺骨牌对的数量
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一组多米诺骨牌 dominoes 。
 *
 * 形式上，dominoes[i] = [a, b] 与 dominoes[j] = [c, d] 等价 当且仅当 (a == c 且 b == d) 或者 (a == d 且 b == c) 。即一张骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌。
 *
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
 *
 *
 *
 * 示例 1：
 *
 * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * 输出：1
 * 示例 2：
 *
 * 输入：dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= dominoes.length <= 4 * 104
 * dominoes[i].length == 2
 * 1 <= dominoes[i][j] <= 9
 */

/**
 * 枚举技巧： 枚举右维护左
 */
public class LC1128_numEquivDominoPairs {
    public int numEquivDominoPairs(int[][] dominoes) {
        int n = dominoes.length;
        int ans = 0;
        //记录骨牌dominoes[i]的出现次数，初始全部为0,  将调整a,b顺序后a<b的骨牌若相同也视为同样的骨牌
        int[][] helper = new int[10][10];
        for(int r = 0; r<n; r++){
            int[] dominoe = dominoes[r];
            int a = Math.min(dominoe[0], dominoe[1]);
            int b = Math.max(dominoe[0], dominoe[1]);
            //组合数的计算方式， 将右端点视为影响因子， 每进入一个右端点int[] dominoe， 会增加过去已存在的helper[a][b]个组合
            ans+=helper[a][b];//必须先累加之前骨牌数
            helper[a][b]++;//后做++
        }

        return ans;
    }
}

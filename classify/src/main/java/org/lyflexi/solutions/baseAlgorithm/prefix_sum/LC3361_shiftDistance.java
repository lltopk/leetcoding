package org.lyflexi.solutions.baseAlgorithm.prefix_sum;

/**
 * 3361. 两个字符串的切换距离
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你两个长度相同的字符串 s 和 t ，以及两个整数数组 nextCost 和 previousCost 。
 *
 * 一次操作中，你可以选择 s 中的一个下标 i ，执行以下操作 之一 ：
 *
 * 将 s[i] 切换为字母表中的下一个字母，如果 s[i] == 'z' ，切换后得到 'a' 。操作的代价为 nextCost[j] ，其中 j 表示 s[i] 在字母表中的下标。
 * 将 s[i] 切换为字母表中的上一个字母，如果 s[i] == 'a' ，切换后得到 'z' 。操作的代价为 previousCost[j] ，其中 j 是 s[i] 在字母表中的下标。
 * 切换距离 指的是将字符串 s 变为字符串 t 的 最少 操作代价总和。
 *
 * 请你返回从 s 到 t 的 切换距离 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abab", t = "baba", nextCost = [100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0], previousCost = [1,100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 *
 * 输出：2
 *
 * 解释：
 *
 * 选择下标 i = 0 并将 s[0] 向前切换 25 次，总代价为 1 。
 * 选择下标 i = 1 并将 s[1] 向后切换 25 次，总代价为 0 。
 * 选择下标 i = 2 并将 s[2] 向前切换 25 次，总代价为 1 。
 * 选择下标 i = 3 并将 s[3] 向后切换 25 次，总代价为 0 。
 * 示例 2：
 *
 * 输入：s = "leet", t = "code", nextCost = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1], previousCost = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
 *
 * 输出：31
 *
 * 解释：
 *
 * 选择下标 i = 0 并将 s[0] 向前切换 9 次，总代价为 9 。
 * 选择下标 i = 1 并将 s[1] 向后切换 10 次，总代价为 10 。
 * 选择下标 i = 2 并将 s[2] 向前切换 1 次，总代价为 1 。
 * 选择下标 i = 3 并将 s[3] 向后切换 11 次，总代价为 11 。
 *
 *
 * 提示：
 *
 * 1 <= s.length == t.length <= 105
 * s 和 t 都只包含小写英文字母。
 * nextCost.length == previousCost.length == 26
 * 0 <= nextCost[i], previousCost[i] <= 109
 */

/**
 * 前缀和
 */
public class LC3361_shiftDistance {
    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        //环形问题技巧: 可以把前缀和数组延长一倍，从而变成非环形的。
        //x->y的正向代价算自己x, 不算目的y: [x, y)
        //当x<y, nxtPreS[y] - nxtPreS[x]
        //当x>y, nxtPreS[y+26] - nxtPreS[x]

        //x->y的反向代价算自己x, 不算目的y: (y,x]
        //当x>y, reversePreS[x+1] - reversePreS[y+1]
        //当x<y, reversePreS[x+1+26] - reversePreS[y+1]
        final int MOD = 26;
        int n = nextCost.length;
        long[] nxtPreS = new long[2*MOD+1];
        long[] reversePreS = new long[2*MOD+1];
        long ans = 0;
        for(int i = 1; i<2*MOD+1; i++){
            nxtPreS[i] = nxtPreS[i-1] +  nextCost[(i-1) % MOD];
            reversePreS[i] = reversePreS[i-1] +  previousCost[(i-1) % MOD];
        }

        for(int i = 0; i< s.length(); i++){
            int x = s.charAt(i) - 'a';
            int y = t.charAt(i) - 'a';

            long cost = 0;
            if(x<y){
                cost = Math.min(nxtPreS[y] - nxtPreS[x], reversePreS[x+1+MOD] - reversePreS[y+1]);
            }else{
                cost = Math.min(nxtPreS[y+MOD] - nxtPreS[x], reversePreS[x+1] - reversePreS[y+1]);
            }
            ans += cost;
        }
        return ans;
    }
}

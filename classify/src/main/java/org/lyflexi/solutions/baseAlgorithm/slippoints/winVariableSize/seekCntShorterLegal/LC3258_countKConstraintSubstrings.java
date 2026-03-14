package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekCntShorterLegal;

/**
 * 3258. 统计满足 K 约束的子字符串数量 I
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个 二进制 字符串 s 和一个整数 k。
 *
 * 如果一个 二进制字符串 满足以下任一条件，则认为该字符串满足 k 约束：
 *
 * 字符串中 0 的数量最多为 k。
 * 字符串中 1 的数量最多为 k。
 * 返回一个整数，表示 s 的所有满足 k 约束 的子字符串的数量。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "10101", k = 1
 *
 * 输出：12
 *
 * 解释：
 *
 * s 的所有子字符串中，除了 "1010"、"10101" 和 "0101" 外，其余子字符串都满足 k 约束。
 *
 * 示例 2：
 *
 * 输入：s = "1010101", k = 2
 *
 * 输出：25
 *
 * 解释：
 *
 * s 的所有子字符串中，除了长度大于 5 的子字符串外，其余子字符串都满足 k 约束。
 *
 * 示例 3：
 *
 * 输入：s = "11111", k = 1
 *
 * 输出：15
 *
 * 解释：
 *
 * s 的所有子字符串都满足 k 约束。
 *
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 50
 * 1 <= k <= s.length
 * s[i] 是 '0' 或 '1'。
 */

/**
 * 不定长滑动窗口求长度, 越短越合法
 */
public class LC3258_countKConstraintSubstrings {
    public int countKConstraintSubstrings(String s, int k) {
        //不定长滑动窗口求个数, 越短越合法
        char[] sArray = s.toCharArray();
        int ans = 0, n = sArray.length;
        int l = 0;
        int[] helper = new int[2];
        for(int r = 0; r< n; r++){
            helper[sArray[r] - '0']++;

            while(helper[0]>k && helper[1]>k){
                helper[sArray[l] - '0']--;
                l++;
            }

            ans +=r - l +1;
        }
        return ans;
    }
}

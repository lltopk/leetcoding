package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekLengthMax;

/**
 * 424. 替换后的最长重复字符
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
 *
 * 在执行上述操作后，返回 包含相同字母的最长子字符串的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * 示例 2：
 *
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 * 可能存在其他的方法来得到同样的结果。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 仅由大写英文字母组成
 * 0 <= k <= s.length
 */

/**
 * 不定长滑动窗口求最大
 */
public class LC424_characterReplacement {
    public int characterReplacement(String s, int k) {
        //不定长滑动窗口求最大
        char[] arr = s.toCharArray();
        int n = arr.length;
        int ans = 0, l = 0;
        int[] dict = new int[26];
        for(int r = 0; r<n ;r++){
            dict[arr[r] - 'A']++;
            //窗口收缩条件
            // 窗口所有字符出现总次数 - 窗口中频率最高字符次数 = other其他字符出现的次数和 > k
            // 这是贪心的思想, 也就是我们优先替换频率低的, 而不去替换频率高的
            while(shrink(dict, k)){
                dict[arr[l] - 'A']--;
                l++;
            }

            ans = Math.max(ans, r - l +1 );
        }

        return ans;
    }

    private boolean shrink(int[] dict , int k ){
        int sum = 0;
        int max = 0;
        for(int i = 0; i< 26 ;i++){
            sum+=dict[i];
            max = Math.max(max, dict[i]);
        }

        return sum - max > k;
    }
}

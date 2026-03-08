package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekCountLongerLegal;

/**
 * 1358. 包含所有三种字符的子字符串数目
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个字符串 s ，它只包含三种字符 a, b 和 c 。
 *
 * 请你返回 a，b 和 c 都 至少 出现过一次的子字符串数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abcabc"
 * 输出：10
 * 解释：包含 a，b 和 c 各至少一次的子字符串为 "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" 和 "abc" (相同字符串算多次)。
 * 示例 2：
 *
 * 输入：s = "aaacb"
 * 输出：3
 * 解释：包含 a，b 和 c 各至少一次的子字符串为 "aaacb", "aacb" 和 "acb" 。
 * 示例 3：
 *
 * 输入：s = "abc"
 * 输出：1
 *
 *
 * 提示：
 *
 * 3 <= s.length <= 5 x 10^4
 * s 只包含字符 a，b 和 c 。
 */

/**
 * 不定长窗口求个数, 越长越合法
 */
public class LC1358_numberOfSubstrings {
    public int numberOfSubstrings(String s) {
        //辅助数组, 代表子串a,b,c字符的个数
        int[] helper = new int[3];
        int ans = 0;
        int l = 0;
        for(int i = 0; i<s.length(); i++){
            helper[s.charAt(i) - 'a']++;
            //滑动窗口求个数, 越长越合法
            while(helper[0]>0 && helper[1]>0 &&helper[2]>0){
                helper[s.charAt(l) - 'a']--;
                l++;
            }
            ans += l;
        }

        return ans;
    }
}

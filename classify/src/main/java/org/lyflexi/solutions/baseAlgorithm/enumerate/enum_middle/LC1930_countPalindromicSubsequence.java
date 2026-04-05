package org.lyflexi.solutions.baseAlgorithm.enumerate.enum_middle;

/**
 * 1930. 长度为 3 的不同回文子序列
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个字符串 s ，返回 s 中 长度为 3 的不同回文子序列 的个数。
 *
 * 即便存在多种方法来构建相同的子序列，但相同的子序列只计数一次。
 *
 * 回文 是正着读和反着读一样的字符串。
 *
 * 子序列 是由原字符串删除其中部分字符（也可以不删除）且不改变剩余字符之间相对顺序形成的一个新字符串。
 *
 * 例如，"ace" 是 "abcde" 的一个子序列。
 *
 *
 * 示例 1：
 *
 * 输入：s = "aabca"
 * 输出：3
 * 解释：长度为 3 的 3 个回文子序列分别是：
 * - "aba" ("aabca" 的子序列)
 * - "aaa" ("aabca" 的子序列)
 * - "aca" ("aabca" 的子序列)
 * 示例 2：
 *
 * 输入：s = "adc"
 * 输出：0
 * 解释："adc" 不存在长度为 3 的回文子序列。
 * 示例 3：
 *
 * 输入：s = "bbcbaba"
 * 输出：4
 * 解释：长度为 3 的 4 个回文子序列分别是：
 * - "bbb" ("bbcbaba" 的子序列)
 * - "bcb" ("bbcbaba" 的子序列)
 * - "bab" ("bbcbaba" 的子序列)
 * - "aba" ("bbcbaba" 的子序列)
 *
 *
 * 提示：
 *
 * 3 <= s.length <= 105
 * s 仅由小写英文字母组成
 */

/**
 * 三变量, 枚举中间
 */
public class LC1930_countPalindromicSubsequence {
    //这道题虽说求个数, 但暗含着去重
    public int countPalindromicSubsequence(String s) {
        char[] sArray = s.toCharArray();
        int n = sArray.length, ans = 0;
        //去重数组, 无需考虑c(i), 假设(c(j), c(k))为一对回文, 用于去重
        boolean[][] has = new boolean[26][26];
        int[] pre = new int[26];//前缀辅助结构, 表示当前索引j之前的任意元素c存在的个数
        int[] suf = new int[26];//后缀辅助结构, 表示当前索引j之后的任意元素c存在的个数

        for(char c: sArray){
            suf[c - 'a']++;
        }

        for(int j = 0; j<n; j++){
            char c = sArray[j];
            //撤销
            suf[c-'a']--;
            //匹配26个任意字符
            for(int i = 0; i< 26; i++){
                if(suf[i]>0 && pre[i]>0 && !has[c-'a'][i]){
                    has[c-'a'][i] = true;
                    ans++;
                }
            }
            //滚动
            pre[c-'a']++;
        }

        return ans;
    }
}

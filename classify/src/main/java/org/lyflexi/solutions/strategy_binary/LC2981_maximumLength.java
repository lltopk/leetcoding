package org.lyflexi.solutions.strategy_binary;

/**
 * 2981. 找出出现至少三次的最长特殊子字符串 I
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个仅由小写英文字母组成的字符串 s 。
 *
 * 如果一个字符串仅由单一字符组成，那么它被称为 特殊 字符串。例如，字符串 "abc" 不是特殊字符串，而字符串 "ddd"、"zz" 和 "f" 是特殊字符串。
 *
 * 返回在 s 中出现 至少三次 的 最长特殊子字符串 的长度，如果不存在出现至少三次的特殊子字符串，则返回 -1 。
 *
 * 子字符串 是字符串中的一个连续 非空 字符序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aaaa"
 * 输出：2
 * 解释：出现三次的最长特殊子字符串是 "aa" ：子字符串 "aaaa"、"aaaa" 和 "aaaa"。
 * 可以证明最大长度是 2 。
 * 示例 2：
 *
 * 输入：s = "abcdef"
 * 输出：-1
 * 解释：不存在出现至少三次的特殊子字符串。因此返回 -1 。
 * 示例 3：
 *
 * 输入：s = "abcaba"
 * 输出：1
 * 解释：出现三次的最长特殊子字符串是 "a" ：子字符串 "abcaba"、"abcaba" 和 "abcaba"。
 * 可以证明最大长度是 1 。
 *
 *
 * 提示：
 *
 * 3 <= s.length <= 50
 * s 仅由小写英文字母组成。
 */

/**
 * 二分答案
 */
public class LC2981_maximumLength {
    public int maximumLength(String s) {
        int n = s.length();
        int l = 0, r = n+1;
        int[] group = new int[26];//代表对26个字符进行分组统计, 值为贡献数
        while(l + 1 < r){
            int mid = l + ((r-l)>>1);
            if(checkInc(mid, group, s)){
                l = mid;
            }else{
                r =mid;
            }
        }

        return l==0?-1:l;
    }

    private boolean checkInc(int mid, int[] group, String s){
        int n = s.length();
        Arrays.fill(group, 0);
        for(int i = 0; i< n;i++){
            int start = i;
            char c = s.charAt(i);
            while(i!=n && s.charAt(i) == c){
                i++;
            }
            int winSize = i - start;
            if(winSize >= mid){
                int earn = winSize - mid +1;
                group[c-'a']+=earn;
                if(group[c-'a'] >= 3){
                    return true;
                }
            }
            //关键, 回退while循环中的i++, 因为for循环也要加的
            i--;
        }

        return false;
    }
}

package org.lyflexi;

import java.util.*;

/**
 * 763. 划分字母区间
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
 * 
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * 
 * 返回一个表示每个字符串片段的长度的列表。
 * 
 * 示例 1：
 * 
 * 输入：s = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
 * 
 * 示例 2：
 * 
 * 输入：s = "eccbbbbdec"
 * 输出：[10]
 * 
 * 提示：
 * 
 * - 1 <= s.length <= 500
 * 
 * - s 仅由小写英文字母组成
 */

/**
 * 算法
 * 
 * 1. 遍历 $s$，计算字母 $c$ 在 $s$ 中的最后出现的下标 $last[c]$。
 * 2. 初始化当前正在合并的区间左右端点 $start=0,\ end=0$。
 * 3. 再次遍历 $s$，由于当前区间必须包含所有 $s[i]$，所以用 $last[s[i]]$ 更新区间右端点 $end$ 的最大值。
 * 4. 如果发现 $end=i$，那么当前区间合并完毕，把区间长度 $end-start+1$ 加入答案。然后更新 $start=end+1$ 作为下一个区间的左端点。
 * 5. 遍历完毕，返回答案。
 */
public class Hot080_LC763_partitionLabels {
    public List<Integer> partitionLabels(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        int[] last = new int[26];
        for (int i = 0; i < n; i++) {
            last[s[i] - 'a'] = i; // 每个字母最后出现的下标
        }

        List<Integer> ans = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < n; i++) {
            end = Math.max(end, last[s[i] - 'a']); // 更新当前区间右端点的最大值
            if (end == i) { // 当前区间合并完毕
                ans.add(end - start + 1); // 区间长度加入答案
                start = end + 1; // 下一个区间的左端点
            }
        }
        return ans;
    }
}

package org.lyflexi.solutions.baseAlgorithm.utils.hash_map;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 *
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 *
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * 解释：
 *
 * 在 strs 中没有字符串可以通过重新排列来形成 "bat"。
 * 字符串 "nat" 和 "tan" 是字母异位词，因为它们可以重新排列以形成彼此。
 * 字符串 "ate" ，"eat" 和 "tea" 是字母异位词，因为它们可以重新排列以形成彼此。
 * 示例 2:
 *
 * 输入: strs = [""]
 *
 * 输出: [[""]]
 *
 * 示例 3:
 *
 * 输入: strs = ["a"]
 *
 * 输出: [["a"]]
 *
 *
 *
 * 提示：
 *
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,762,659/2.5M
 * 通过率
 * 69.4%
 */
public class LC49_groupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        //排序后的str作为key, 排序前的str加入value
        Map<String, List<String>> map = new HashMap<>();
        for(String str: strs){
            char[] cStr = str.toCharArray();
            Arrays.sort(cStr);
            map.computeIfAbsent(new String(cStr), key -> new ArrayList<>()).add(str);
        }
        // 哈希表的所有 value 就是分组结果
        return new ArrayList<>(map.values());
    }
}

package org.lyflexi.solutions.baseAlgorithm.utils.stack.pair;

/**
 * 1209. 删除字符串中的所有相邻重复项 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个字符串 s，「k 倍重复项删除操作」将会从 s 中选择 k 个相邻且相等的字母，并删除它们，使被删去的字符串的左侧和右侧连在一起。
 *
 * 你需要对 s 重复进行无限次这样的删除操作，直到无法继续为止。
 *
 * 在执行完所有删除操作后，返回最终得到的字符串。
 *
 * 本题答案保证唯一。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abcd", k = 2
 * 输出："abcd"
 * 解释：没有要删除的内容。
 * 示例 2：
 *
 * 输入：s = "deeedbbcccbdaa", k = 3
 * 输出："aa"
 * 解释：
 * 先删除 "eee" 和 "ccc"，得到 "ddbbbdaa"
 * 再删除 "bbb"，得到 "dddaa"
 * 最后删除 "ddd"，得到 "aa"
 * 示例 3：
 *
 * 输入：s = "pbbcggttciiippooaais", k = 2
 * 输出："ps"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * 2 <= k <= 10^4
 * s 中只含有小写英文字母。
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 列表模拟pair[]栈
 */
public class LC1209_removeDuplicates {
    public String removeDuplicates(String s, int k) {
        //栈元素同时记录连续出现次数, 这样可以避免每次回溯统计次数, 降低时间复杂度
        List<int[]> stack = new ArrayList<>();
        for(char c: s.toCharArray()){
            if(! stack.isEmpty() && stack.get(stack.size() - 1)[0] == c){
                stack.get(stack.size() - 1)[1]++;
                if(stack.get(stack.size() - 1)[1] == k){
                    stack.remove(stack.size() - 1);
                }
                //连续次数不超过k的, 留在栈中即可, 要么等待机会消消乐, 要么永远无法消除就是答案
            }else{
                stack.add(new int[]{c, 1});
            }
        }

        StringBuilder ret = new StringBuilder();
        for(int[] pair: stack){
            for(int i = 0; i< pair[1]; i++){
                ret.append((char)pair[0]);
            }
        }

        return ret.toString();
    }
}

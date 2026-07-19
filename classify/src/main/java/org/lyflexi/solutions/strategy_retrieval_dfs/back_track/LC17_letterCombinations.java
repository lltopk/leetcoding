package org.lyflexi.solutions.strategy_retrieval_dfs.back_track;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 *
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 *
 * 提示：
 *
 * 1 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,382,715/2.2M
 * 通过率
 * 63.8%
 */

/**
 * 回溯: 枚举选哪个(答案视角)
 */
public class LC17_letterCombinations {
    private String[] mapping = new String[]{"", "",  "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> ret  = new ArrayList<>();
        //digits的长度就是最终要选的次数
        char[] path = new char[digits.length()];
        //回溯出口就是i == digits.length();
        dfs(digits, ret, path, 0);
        return ret;
    }

    private void dfs(String digits, List<String> ret, char[] path, int i){
        if(i == digits.length()){
            ret.add(new String(path));
            return;
        }
        for(char c: mapping[digits.charAt(i) - '0'].toCharArray()){
            path[i] = c;
            dfs(digits, ret, path, i+1);
        }
    }
}

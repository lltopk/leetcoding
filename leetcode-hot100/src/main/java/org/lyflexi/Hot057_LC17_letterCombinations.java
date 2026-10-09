package org.lyflexi;

import java.util.*;

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
 * 示例 1：
 * 
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 
 * 示例 2：
 * 
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * 
 * 提示：
 * 
 * - 1 <= digits.length <= 4
 * 
 * - digits[i] 是范围 ['2', '9'] 的一个数字。
 */

/**
 * 答疑
 * 
 * 问：为什么视频中在介绍 $dfs(i)$ 的含义时，说我们在枚举下标 $>= i$ 的剩余部分？$dfs(i)$ 不是在枚举 $i$ 吗？
 * 
 * 答：$dfs(i)$ 处理的是从下标 $i$ 到末尾的所有字母组合。$dfs(i)$ 不仅仅在枚举 $i$，还包含了 $dfs(i+1), dfs(i+2),\ldots, dfs(n)$ 这之后的所有递归调用。单纯说「枚举 $i$」是不准确的，因为除了枚举 $i$，还要递归处理剩余部分。正因为如此，我视频中讲的是 $>= i$ 而不是等于 $i$。
 */
public class Hot057_LC17_letterCombinations {
    private static final String[] MAPPING = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        if (n == 0) {
            return Collections.emptyList();
        }

        List<String> ans = new ArrayList<>();
        char[] path = new char[n]; // 注意 path 长度一开始就是 n，不是空数组
        dfs(0, ans, path, digits.toCharArray());
        return ans;
    }

    private void dfs(int i, List<String> ans, char[] path, char[] digits) {
        if (i == digits.length) {
            ans.add(new String(path));
            return;
        }
        String letters = MAPPING[digits[i] - '0'];
        for (char c : letters.toCharArray()) {
            path[i] = c; // 直接覆盖
            dfs(i + 1, ans, path, digits);
        }
    }
}

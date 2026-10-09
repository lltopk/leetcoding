package org.lyflexi;

import java.util.*;

/**
 * 22. 括号生成
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 
 * 示例 1：
 * 
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 
 * 示例 2：
 * 
 * 输入：n = 1
 * 输出：["()"]
 * 
 * 提示：
 * 
 * - 1 <= n <= 8
 */

/**
 * 方法二：枚举下一个左括号的位置
 * 
 * 用「枚举选哪个」的思路。
 * 
 * 在从左往右填的过程中，要时刻保证右括号的个数不能超过左括号的个数。
 * 
 * 如果前面填了 $5$ 个左括号，$2$ 个右括号，那么还能填几个右括号？
 * 
 * 至多填 $5-2=3$ 个。
 * 
 * 所以枚举（在填下一个左括号之前）填入了 $0,1,2,3$ 个右括号，这样就能得到下一个左括号的位置。
 * 
 * 为了方便，代码直接用 $balance$ 表示左右括号之差。这样我们枚举的范围就是 $[0,balance]$。
 * 注意最后一个左括号的右边还可以填右括号，但无需考虑。填入所有左括号后，剩余的位置我们会自动填入右括号。
 */
public class Hot059_LC22_generateParenthesis2 {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(0, 0, n, path, ans);
        return ans;
    }

    // 目前填了 i 个括号
    // 这 i 个括号中的左括号个数 - 右括号个数 = balance
    private void dfs(int i, int balance, int n, List<Integer> path, List<String> ans) {
        if (path.size() == n) {
            char[] s = new char[n * 2];
            Arrays.fill(s, ')');
            for (int j : path) {
                s[j] = '(';
            }
            ans.add(new String(s));
            return;
        }
        // 枚举填 right=0,1,2,...,balance 个右括号
        for (int right = 0; right <= balance; right++) {
            // 先填 right 个右括号，然后填 1 个左括号，记录左括号的下标 i+right
            path.add(i + right);
            dfs(i + right + 1, balance - right + 1, n, path, ans);
            path.remove(path.size() - 1); // path.remove(path.size() - 1);
        }
    }
}

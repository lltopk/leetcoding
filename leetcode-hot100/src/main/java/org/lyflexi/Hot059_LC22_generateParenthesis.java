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
 * 答疑
 * 
 * 问：什么时候需要写恢复现场，什么时候不需要写？
 * 
 * 答：下面代码中，如果初始化 $path$ 为空列表，就需要写恢复现场。本题由于所有括号长度都是固定的 $2n$，我们可以创建一个长为 $2n$ 的 $path$ 列表，在递归时直接写入字符（而不是插入字符），这样做无需写恢复现场。
 * 
 * 问：代码如何保证 $path[0]$ 一定是左括号，$path[2n-1]$ 一定是右括号？
 * 
 * 答：一开始 $left = right = 0$，填右括号的那个 $if$ 条件不成立，所以 $path[0]$ 只能填左括号。由于只有在 $right < left$ 时才能填右括号，当 $right = left$ 时 $right$ 不能再变大，所以始终有 $right<= left$。当我们填了 $2n-1$ 个括号时，唯一解是 $left = n$ 且 $right=n-1$，此时填左括号的那个 $if$ 条件不成立，所以 $path[2n-1]$ 只能填右括号。
 */
public class Hot059_LC22_generateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        char[] path = new char[n * 2]; // 所有括号长度都是一样的 2n
        dfs(0, 0, n, path, ans); // 一开始没有填括号
        return ans;
    }

    // 目前填了 left 个左括号，right 个右括号
    private void dfs(int left, int right, int n, char[] path, List<String> ans) {
        if (right == n) { // 填完 2n 个括号
            ans.add(new String(path));
            return;
        }
        if (left < n) { // 可以填左括号
            path[left + right] = '('; // 直接覆盖
            dfs(left + 1, right, n, path, ans);
        }
        if (right < left) { // 可以填右括号
            path[left + right] = ')'; // 直接覆盖
            dfs(left, right + 1, n, path, ans);
        }
    }
}

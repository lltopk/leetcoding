package org.lyflexi.solutions.strategy_retrieval_dfs.back_track.combine;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 *
 *
 * 提示：
 *
 * 1 <= n <= 8
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,315,800/1.7M
 * 通过率
 * 79.0%
 */
public class LC22_generateParenthesis {
    public List<String> generateParenthesis(int n) {
        int m = n * 2;
        List<String> ret = new ArrayList<>();
        char[] path = new char[m];
        dfs(m, ret, path, 0, 0);
        return ret;
    }

    /**
     layers:

     i有效括号的当前位置
     open左括号的个数
     */
    private void dfs(int m, List<String> ret, char[] path, int i, int open){
        //path的长度到m了（因为有效括号的长度一定是2*n）
        if(i == m){
            ret.add(new String(path));
        }
        //有效括号一定是（开头的， 所以先选
        if(open < m/2){
            path[i] = '(';
            dfs(m, ret, path, i+1, open+1);
        }
        //不选左括号， 相当于选右括号
        // 选右括号的条件是当前左括号的个数大于右括号的个数
        if(open > i - open){
            path[i] = ')';
            dfs(m, ret, path, i+1, open);// 选右括号
        }
    }
}

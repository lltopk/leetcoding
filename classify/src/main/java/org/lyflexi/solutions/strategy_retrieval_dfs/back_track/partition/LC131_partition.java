package org.lyflexi.solutions.strategy_retrieval_dfs.back_track.partition;

/**
 * 131. 分割回文串
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 811,825/1.1M
 * 通过率
 * 75.1%
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 划分型回溯： 枚举思路（答案视角）
 */
public class LC131_partition {
    List<List<String>> ret = new ArrayList<>();
    public List<List<String>> partition(String s) {
        dfs(s, new ArrayList<>(), 0);
        return ret;
    }

    private void dfs(String s, List<String> path, int i){
        if (i == s.length()){
            ret.add(new ArrayList<>(path));
            return;
        }

        //子问题dfs(i) -> 下一个子问题dfs(i+1)
        //枚举思路(答案视角)
        for(int j = i; j<s.length(); j++){
            if(isHuiwen(s, i, j)){
                path.add(s.substring(i, j + 1));
                dfs(s, path, j+1);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isHuiwen(String s, int l, int r){
        while(l < r){
            if(s.charAt(l++) != s.charAt(r--)){
                return false;
            }
        }
        return true;
    }
}

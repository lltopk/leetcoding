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
 * 划分型回溯： 选与不选思路（答案视角）
 */
public class LC131_partition2 {
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<>();
        //第一个0代表当前字串末尾位置i
        //第二个0代表字串起点start
        dfs(s, ret, new ArrayList<>(), 0, 0);
        return ret;
    }

    /**
     判断是否分割， 即是否要在i和i+1之间切一刀
     */
    private void dfs(String s, List<List<String>> ret, List<String> path, int i, int start){
        if (i == s.length()){
            ret.add(new ArrayList<>(path));
            return;
        }

        //子问题dfs(i) -> 下一个子问题dfs(i+1)
        //选与不选思路(输入视角)

        //不选， 当i==s.length() - 1时候是最后一段必须要选， 只有当i < s.length() - 1时才可以不选
        if(i < s.length() - 1){
            dfs(s, ret, path, i+1, start);
        }
        //选
        if(isHuiwen(s, start, i)){
            path.add(s.substring(start, i + 1));
            //重置start和i， 从i+1开始
            // 现在 s 未被分割的部分为 [i+1, n-1], 即下一个子问题dfs(i+1)
            dfs(s, ret, path, i+1, i+1);
            path.remove(path.size() - 1);
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

package org.lyflexi.solutions.strategy_retrieval_dfs.back_track.combine;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. 组合总和 III
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 *
 * 只使用数字1到9
 * 每个数字 最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 *
 *
 *
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 解释:
 * 1 + 2 + 4 = 7
 * 没有其他符合的组合了。
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * 解释:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * 没有其他符合的组合了。
 * 示例 3:
 *
 * 输入: k = 4, n = 1
 * 输出: []
 * 解释: 不存在有效的组合。
 * 在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
 *
 *
 * 提示:
 *
 * 2 <= k <= 9
 * 1 <= n <= 60
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 560,508/785.9K
 * 通过率
 * 71.3%
 */

public class LC216_combinationSum2 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ret = new ArrayList<>();
        dfs(n, k, ret, new ArrayList<>(), 1);
        return ret;
    }

    /**
     * 枚举思路（答案视角）
     * @param n
     * @param k
     * @param ret
     * @param path
     * @param i
     */
    private void dfs(int n, int k, List<List<Integer>> ret, List<Integer> path, int i){
        if (k == 0 && n == 0){
            ret.add(new ArrayList<>(path));
            return;
        }
        //剪枝优化， 剩余数字不够选剩余的K个
        if (k > n - i + 1) {
            return;
        }
        //剪枝优化， 剩余数字和都不够当前n
        if(n > (i + 9) * (9 - i + 1)/2){
            return;
        }
        for(int j = i ; j<=9; j++){
            path.add(j);
            dfs(n - j, k - 1, ret, path, j+1);
            path.remove(path.size() - 1);
        }
    }
}

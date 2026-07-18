package org.lyflexi.solutions.strategy_retrieval_dfs;

import org.lyflexi.common.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * LCP 44. 开幕式焰火
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 「力扣挑战赛」开幕式开始了，空中绽放了一颗二叉树形的巨型焰火。 给定一棵二叉树 root 代表焰火，节点值表示巨型焰火这一位置的颜色种类。请帮小扣计算巨型焰火有多少种不同的颜色。
 *
 * 示例 1：
 *
 * 输入：root = [1,3,2,1,null,2]
 *
 * 输出：3
 *
 * 解释：焰火中有 3 个不同的颜色，值分别为 1、2、3
 *
 * 示例 2：
 *
 * 输入：root = [3,3,3]
 *
 * 输出：1
 *
 * 解释：焰火中仅出现 1 个颜色，值为 3
 *
 * 提示：
 *
 * 1 <= 节点个数 <= 1000
 * 1 <= Node.val <= 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 28,306/34.8K
 * 通过率
 * 81.3%
 */
public class LCP44_numColor {
    public int numColor(TreeNode root) {
        return preorderTraversal(root).size();
    }
    public Set<Integer> preorderTraversal(TreeNode root) {
        Set<Integer> ret = new HashSet<>();
        dfs(root, ret);
        return ret;
    }
    private void dfs(TreeNode root, Set<Integer> ret){
        if(root == null){
            return;
        }
        ret.add(root.val);
        dfs(root.left, ret);
        dfs(root.right, ret);
    }
}

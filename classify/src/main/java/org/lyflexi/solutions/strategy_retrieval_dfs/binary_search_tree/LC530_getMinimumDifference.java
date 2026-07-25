package org.lyflexi.solutions.strategy_retrieval_dfs.binary_search_tree;

import org.lyflexi.common.TreeNode;

/**
 * 530. 二叉搜索树的最小绝对差
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 *
 * 差值是一个正数，其数值等于两值之差的绝对值。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 * 示例 2：
 *
 *
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 *
 *
 * 提示：
 *
 * 树中节点的数目范围是 [2, 104]
 * 0 <= Node.val <= 105
 *
 *
 * 注意：本题与 783 https://leetcode.cn/problems/minimum-distance-between-bst-nodes/ 相同
 */
public class LC530_getMinimumDifference {
    int ret = Integer.MAX_VALUE;
    int pre = -1;
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return ret;
    }
    private void dfs(TreeNode root){
        if(root == null){
            return ;
        }
        dfs(root.left);
        if(pre != -1){
            ret = Math.min(root.val - pre, ret);
        }
        pre = root.val;
        dfs(root.right);
    }
}

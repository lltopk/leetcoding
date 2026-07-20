package org.lyflexi.solutions.strategy_retrieval_dfs.from_top_down_retV_top;

import org.lyflexi.common.TreeNode;

/**
 * 226. 翻转二叉树
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 * 示例 2：
 *
 *
 *
 * 输入：root = [2,1,3]
 * 输出：[2,3,1]
 * 示例 3：
 *
 * 输入：root = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 树中节点数目范围在 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,515,583/1.8M
 * 通过率
 * 82.5%
 */
public class LC226_invertTree {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        //先反转左右节点
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        //带有返回值的自顶向下DFS
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        return root;
    }
}

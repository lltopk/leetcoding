package org.lyflexi.solutions.dfs;

import org.lyflexi.structDef.TreeNode;

/**
 * @Author: ly
 * @Date: 2024/3/24 16:56
 */

/*
* 112. 路径总和
给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。
判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
叶子节点 是指没有子节点的节点。
*
* */
public class Solution08_TreePathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        //递归释放，从栈顶逐步退栈
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        //递归不断压栈
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}

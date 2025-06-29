package org.lyflexi.solutions.dfs;

import org.lyflexi.structDef.TreeNode;

/**
 * @Author: ly
 * @Date: 2024/2/15 19:06
 */

/*
* 226. 翻转二叉树
给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
* */
public class Solution07_ReverseTree {

    public TreeNode invertTree(TreeNode root) {

        reverseTree(root);
        return root;
    }

    private void reverseTree(TreeNode node){
        if (node==null){
            return;
        }

        //交换当前节点的左右子树，自身无需交换
        TreeNode tmpNode = new TreeNode();
        tmpNode = node.left;
        node.left = node.right;
        node.right = tmpNode;
        //递归
        reverseTree(node.left);
        reverseTree(node.right);
    }
}

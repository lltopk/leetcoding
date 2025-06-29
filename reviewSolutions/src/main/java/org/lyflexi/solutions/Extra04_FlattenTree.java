package org.lyflexi.solutions;

import org.lyflexi.structDef.TreeNode;

/**
 * @Author: ly
 * @Date: 2024/3/24 10:34
 */

/*
* 114. 二叉树展开为链表
给你二叉树的根结点 root ，请你将它展开为一个单链表：
展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
展开后的单链表应该与二叉树 先序遍历 顺序相同。

示例 1：
输入：root = [1,2,5,3,4,null,6]
输出：[1,null,2,null,3,null,4,null,5,null,6]
*
* */

public class Extra04_FlattenTree {
    // 第一步，寻找左子树最右边的节点，便于将原来的右子树接回到左子树的最右边节点
    // 第二步，右子树接回到到左子树的最右边节点
    // 第三步，左子树替代右子树本来的位置
    // 第四步，左子树置null，root = root.right;
    public void flatten(TreeNode root) {
        while (root != null) {
            // 左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 1.找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 2.将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 3.将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 4.考虑下一个节点
                root = root.right;
            }
        }
    }

    // //递归写法
    // public void flatten(TreeNode root) {
    // ArrayList<TreeNode> list = new ArrayList<>();
    // dfsPreOrder(root, list);
    // for (int i = 0; i < list.size() - 1; i++) {
    // //题目要求输出的还是树形结构
    // list.get(i).left = null;
    // list.get(i).right = list.get(i + 1);
    // }
    // }

    // public void dfsPreOrder(TreeNode root, List<TreeNode> list) {
    // if (root == null) {
    // return;
    // }
    // list.add(root);
    // dfsPreOrder(root.left, list);
    // dfsPreOrder(root.right, list);
    // }
}

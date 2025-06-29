package org.lyflexi.solutions.bfs;

import org.lyflexi.structDef.TreeNode;

import java.util.LinkedList;

/**
 * @Author: ly
 * @Date: 2024/3/24 16:12
 */

/*
* 662. 二叉树最大宽度
给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
树的 最大宽度 是所有层中最大的 宽度 。
每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
题目数据保证答案将会在  32 位 带符号整数范围内。
*
*
*示例 1
输入：root = [1,3,2,5,3,null,9]
输出：4
解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
* */
public class Solution07_MaxWidthHasNull {
    public int widthOfBinaryTree(TreeNode root) {
        if (root==null){
            return 0;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        //给每个树节点编号
        root.val = 1;
        queue.offer(root);

        int widthest = 1;

        while (!queue.isEmpty()){//每次循环代表当前的一层

            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                //添加下一层节点
                if (node.left!=null){
                    //左子树节点编号为2*根节点编号
                    node.left.val = (node.val)*2;
                    queue.offer(node.left);
                }
                if (node.right!= null){
                    //右子树节点编号为2*根节点编号+1
                    node.right.val = (node.val)*2+1;
                    queue.offer(node.right);
                }

            }
            if(queue.size()!=0){
                widthest = Math.max(widthest,queue.get(queue.size()-1).val-queue.get(0).val+1);
            }
        }
        return widthest;
    }
}

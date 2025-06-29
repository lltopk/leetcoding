package org.lyflexi.solutions.dfs;

import org.lyflexi.structDef.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ly
 * @Date: 2024/2/15 11:39
 */

/*
* 94. 二叉树的中序遍历
* */
public class Solution05_InorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {

        ArrayList<Integer> answer = new ArrayList<>();
        inOrder(answer,root);
        return answer;

    }

    private void inOrder(List<Integer> res , TreeNode node){
        if (node == null ){
            return;
        }

        inOrder(res,node.left);
        res.add(node.val);
        inOrder(res,node.right);
    }

}

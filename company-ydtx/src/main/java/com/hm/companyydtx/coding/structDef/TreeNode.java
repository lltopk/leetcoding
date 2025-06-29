package com.hm.companyydtx.coding.structDef;

/**
 * @Author: ly
 * @Date: 2024/2/15 11:26
 */

public class TreeNode{

    public int val;
    public TreeNode left ;
    public TreeNode right;

    public TreeNode(int value){
        this.val = value;
    }

    public TreeNode(int value,TreeNode left,TreeNode right){
        this.val = value;
        this.left = left;
        this.right = right;
    }


    public TreeNode() {

    }
}

package org.lyflexi.solutions.bfs;

import org.lyflexi.structDef.TreeNode;

import java.util.LinkedList;

/**
 * @Author: ly
 * @Date: 2024/2/15 18:22
 */

/*
*
LCR 175. 计算二叉树的深度
某公司架构以二叉树形式记录，请返回该公司的层级数。
*
*
* */
public class Solution04_TreeDepth {


    //用队列实现广度优先遍历
    public int calculateDepth(TreeNode root) {

        if (root==null){
            return 0;
        }
        //Java中的队列和栈都叫LinkedList
        //queue的方法是add和poll
        //stack的方法是push和pop
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int hight = 0;

        while (!queue.isEmpty()){//每次循环代表当前的一层

            int n = queue.size();

            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                //添加下一层节点
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if (node.right!= null){
                    queue.offer(node.right);
                }

            }
            hight++;
        }
        return hight;

    }
}

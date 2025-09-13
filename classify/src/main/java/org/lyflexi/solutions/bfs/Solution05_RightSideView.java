package org.lyflexi.solutions.bfs;

import org.lyflexi.structDef.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: ly
 * @Date: 2024/3/23 22:36
 */

/*
* 199. 二叉树的右视图
给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。


示例 1:
输入: [1,2,3,null,5,null,4]
输出: [1,3,4]
*
示例 2:
输入: [1,null,3]
输出: [1,3]
*
示例 3:
输入: []
输出: []
* */
public class Solution05_RightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        if (root==null){
            return new ArrayList<>();
        }

        List<Integer> answer = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();//链表结构的队列在Java中就是LinkedList,不需要指定初始化容量
        queue.offer(root);



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

                if(i == n - 1){
                    answer.add(node.val);
                }

            }
        }
        return answer;
    }
}

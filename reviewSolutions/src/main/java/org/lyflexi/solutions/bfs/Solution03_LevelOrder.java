package org.lyflexi.solutions.bfs;

import org.lyflexi.structDef.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: ly
 * @Date: 2024/3/6 13:58
 */


/*
* 102. 二叉树的层序遍历
给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
*
*
*示例一：
* 输入：root = [3,9,20,null,null,15,7]
输出：[[3],[9,20],[15,7]]
*
* 示例 2：
输入：root = [1]
输出：[[1]]
*
*
示例 3：
输入：root = []
输出：[]
*
* */
public class Solution03_LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root==null){
            return new ArrayList<>();
        }

        LinkedList<TreeNode> queue = new LinkedList<>();

        List<List<Integer>> answer = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()){

            ArrayList<Integer> levelList = new ArrayList<>();

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                levelList.add(poll.val);

                if (poll.left!=null){
                    queue.offer(poll.left);
                }

                if (poll.right!=null){
                    queue.offer(poll.right);
                }

            }

            answer.add(levelList);
        }
        return answer;

    }
}

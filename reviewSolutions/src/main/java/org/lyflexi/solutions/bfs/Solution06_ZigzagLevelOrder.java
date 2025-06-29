package org.lyflexi.solutions.bfs;

import org.lyflexi.structDef.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * @Author: ly
 * @Date: 2024/3/24 10:02
 */

/*
* 103. 二叉树的锯齿形层序遍历
给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
*
* 示例：
输入：root = [3,9,20,null,null,15,7]
输出：[[3],[20,9],[15,7]]

* */
public class Solution06_ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        if (root == null) return answer;
        //初始0代表是第1层
        int level = 0;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            //子问题layer数组
            Integer[] layer = new Integer[queueSize];
            for (int i = 0; i < queueSize; i++) {
                TreeNode node = queue.poll();
                //偶层顺序添加，奇层逆序添加,
                layer[level % 2 == 0?i:queueSize-1-i]=node.val;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);

            }
            level++;
            answer.add(Arrays.asList(layer));
        }
        return answer;
    }
}

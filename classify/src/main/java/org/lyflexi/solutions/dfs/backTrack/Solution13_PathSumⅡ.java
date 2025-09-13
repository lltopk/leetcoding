package org.lyflexi.solutions.dfs.backTrack;

import org.lyflexi.structDef.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ly
 * @Date: 2024/3/24 16:56
 */

/*
* 113. 路径总和 II
给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
叶子节点 是指没有子节点的节点。
*
*
* 示例：
输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：[[5,4,11,2],[5,8,4,5]]
*
* */
public class Solution13_PathSumⅡ {
    //因为需要记录路径，所以回溯
    List<List<Integer>> answer = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<Integer> list = new ArrayList<>();//发动机
        dfs(root, list, sum);
        return answer;
    }

    public void dfs(TreeNode node, List<Integer> list, int target){
        if(node == null){
            return ;
        }
        if(node.left == null && node.right == null && node.val  == target){
            list.add(node.val);
            answer.add(new ArrayList<>(list));
            list.remove(list.size() - 1);//只要list加过，就得减
            return ;
        }
        list.add(node.val);
        dfs(node.left, list, target - node.val);
        dfs(node.right, list, target - node.val);
        list.remove(list.size() - 1);//只要list加过，就得减
    }
}

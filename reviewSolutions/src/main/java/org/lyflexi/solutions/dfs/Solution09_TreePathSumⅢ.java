package org.lyflexi.solutions.dfs;

import org.lyflexi.structDef.TreeNode;

/**
 * @Author: ly
 * @Date: 2024/3/24 16:56
 */

/*
437. 路径总和 III
给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
示例 1：
输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
输出：3
解释：和等于 8 的路径有 3 条，如图所示。
* */
//    不要求记录路径，所以不是回溯，但是是双重递归
public class Solution09_TreePathSumⅢ {
    public int pathSum(TreeNode root, int targetSum) {

        if (root == null) {
            return 0;
        }

        int answer = rootBegin(root, targetSum);
        //目标函数，递归函数2
        answer += pathSum(root.left, targetSum);
        answer += pathSum(root.right, targetSum);
        return answer;
    }

    //递归函数1
    //如果root.val太大，递归调用多了targetSum-root.val就会溢出整数型的最小值。把参数类型换成long即可。
    public int rootBegin(TreeNode root, long targetSum) {
        int ret = 0;

        if (root == null) {
            return 0;
        }
        //不要求遍历到叶子节点，所以不需要条件root.left == null && root.right == null
        if (root.val == targetSum) {
            ret++;
        }

        //求所有的可能性，所以不能用||
        ret += rootBegin(root.left, targetSum - root.val);
        ret += rootBegin(root.right, targetSum - root.val);
        return ret;
    }
}

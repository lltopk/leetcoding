package org.lyflexi.solutions.dfs;

import org.lyflexi.structDef.TreeNode;

/**
 * @Author: ly
 * @Date: 2024/3/24 17:25
 */

/*
* 129. 求根节点到叶节点数字之和
给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
每条从根节点到叶节点的路径都代表一个数字：
例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
计算从根节点到叶节点生成的 所有数字之和 。
叶节点 是指没有子节点的节点。
*
*示例 :
输入：root = [4,9,0,5,1]
输出：1026
解释：
从根到叶子节点路径 4->9->5 代表数字 495
从根到叶子节点路径 4->9->1 代表数字 491
从根到叶子节点路径 4->0 代表数字 40
因此，数字总和 = 495 + 491 + 40 = 1026
*
*
* */
public class Solution10_CalculatePathSum {
    public int sumNumbers(TreeNode root) {
        return helper(root, 0);
    }

    public int helper(TreeNode root, int pre){
        if (root == null) return 0;
        int sum = pre * 10 + root.val;
        //递归释放，从栈顶逐步退栈
        if (root.left == null && root.right == null)
            return sum;
        //递归不断压栈，当前位置的sum作为下一轮的pre传入
        return helper(root.left, sum) + helper(root.right, sum);
    }
}

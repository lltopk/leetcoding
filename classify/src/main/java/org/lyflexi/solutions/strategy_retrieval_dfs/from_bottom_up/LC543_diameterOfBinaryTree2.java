package org.lyflexi.solutions.strategy_retrieval_dfs.from_bottom_up;

import org.lyflexi.common.TreeNode;

/**
 * 543. 二叉树的直径
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 *
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 *
 * 两节点之间路径的 长度 由它们之间边数表示。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3,4,5]
 * 输出：3
 * 解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
 * 示例 2：
 *
 * 输入：root = [1,2]
 * 输出：1
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 104] 内
 * -100 <= Node.val <= 100
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 867,077/1.3M
 * 通过率
 * 64.3%
 */
public class LC543_diameterOfBinaryTree2 {
    int ret = 0;//最大直径 = 左右子树两个链长
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return ret;
    }

    /**
        自底向上求最大高度, 那么子树的高度恰好就是父节点(当前节点)的链长
        整个迭代过程中, 根据dfs(root.left)和dfs(root.right)求两侧链和最大值, 即为最大直径和
     */
    private int dfs(TreeNode root){
        if(root == null){
            return 0;//求高度则叶子节点 0 + 1 = 1
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        ret = Math.max(ret, left + right);
        return Math.max(left, right) + 1;
    }
}

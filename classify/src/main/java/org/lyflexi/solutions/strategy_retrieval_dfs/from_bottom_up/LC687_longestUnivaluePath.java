package org.lyflexi.solutions.strategy_retrieval_dfs.from_bottom_up;

import org.lyflexi.common.TreeNode;

/**
 * 687. 最长同值路径
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
 *
 * 两个节点之间的路径长度 由它们之间的边数表示。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入：root = [5,4,5,1,1,5]
 * 输出：2
 * 示例 2:
 *
 *
 *
 * 输入：root = [1,4,5,4,4,5]
 * 输出：2
 *
 *
 * 提示:
 *
 * 树的节点数的范围是 [0, 104]
 * -1000 <= Node.val <= 1000
 * 树的深度将不超过 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 100,598/204.6K
 * 通过率
 * 49.2%
 */
public class LC687_longestUnivaluePath {
    int ret = 0;
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return ret;
    }

    /**
     自底向上求最大高度, 那么子树的高度恰好就是父节点(当前节点)的链长
     整个迭代过程中, 根据dfs(root.left)和dfs(root.right)求两侧链和最大值, 即为直径最大直径和
     */
    private int dfs(TreeNode root){
        if(root == null){
            return 0;//求高度则叶子节点 0 + 1 = 1
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        if(root.left!= null && root.val != root.left.val) left = 0;
        if(root.right!=null && root.val != root.right.val) right = 0;
        ret = Math.max(ret, left + right);
        return Math.max(left, right) + 1;
    }
}

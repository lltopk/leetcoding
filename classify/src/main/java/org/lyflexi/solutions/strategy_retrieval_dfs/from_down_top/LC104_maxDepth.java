package org.lyflexi.solutions.strategy_retrieval_dfs.from_down_top;

/**
 * 104. 二叉树的最大深度
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二叉树 root ，返回其最大深度。
 *
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：3
 * 示例 2：
 *
 * 输入：root = [1,null,2]
 * 输出：2
 *
 *
 * 提示：
 *
 * 树中节点的数量在 [0, 104] 区间内。
 * -100 <= Node.val <= 100
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 2,075,241/2.6M
 * 通过率
 * 79.0%
 */

import org.lyflexi.common.TreeNode;

/**
 * 自底向上DFS
 */
public class LC104_maxDepth {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int leftH = maxDepth(root.left);
        int rightH = maxDepth(root.right);
        return Math.max(leftH, rightH) + 1;
    }
}

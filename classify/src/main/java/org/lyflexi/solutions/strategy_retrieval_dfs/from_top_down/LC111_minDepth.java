package org.lyflexi.solutions.strategy_retrieval_dfs.from_top_down;

/**
 * 111. 二叉树的最小深度
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 * 示例 2：
 *
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 *
 *
 * 提示：
 *
 * 树中节点数的范围在 [0, 105] 内
 * -1000 <= Node.val <= 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 908,526/1.6M
 * 通过率
 * 56.9%
 */

import org.lyflexi.common.TreeNode;

/**
 * 自顶向下
 */
public class LC111_minDepth {
    int ret = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        dfs(root, 0);
        return ret;
    }
    private void dfs(TreeNode root, int depth){
        if(root == null){
            return;
        }
        depth++;
        if(root.left == null && root.right == null){
            ret = Math.min(ret, depth);
            return;
        }
        dfs(root.left, depth);
        dfs(root.right, depth);
    }
}

package org.lyflexi.solutions.strategy_retrieval_dfs.from_top_down_retV_top;

import org.lyflexi.common.TreeNode;

/**
 * 965. 单值二叉树
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 *
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：[1,1,1,1,1,null,1]
 * 输出：true
 * 示例 2：
 *
 *
 *
 * 输入：[2,2,2,5,2]
 * 输出：false
 *
 *
 * 提示：
 *
 * 给定树的节点数范围是 [1, 100]。
 * 每个节点的值都是整数，范围为 [0, 99] 。
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 111,675/158.5K
 * 通过率
 * 70.5%
 */
public class LC965_isUnivalTree {
    public boolean isUnivalTree(TreeNode root) {
        return dfs(root, root.val);
    }

    /**
     * 自顶向下 有递有归
     *
     * @param root
     * @param headV
     * @return
     */
    private boolean dfs(TreeNode root, int headV) {
        if (root == null) {
            return true;
        }
        if (root.val != headV) {
            return false;
        }
        return dfs(root.left, headV) && dfs(root.right, headV);
    }
}

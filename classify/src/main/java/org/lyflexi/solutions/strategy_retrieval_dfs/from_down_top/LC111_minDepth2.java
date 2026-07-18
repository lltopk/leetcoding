package org.lyflexi.solutions.strategy_retrieval_dfs.from_down_top;

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
 * 自底向上
 */
public class LC111_minDepth2 {
    /**
     * 分类讨论：
     * 如果 node 是空节点，返回 0。
     * 如果 node 是叶子节点，返回 1。
     * 否则，计算 leftDepth，如果左儿子不是空节点，那么 leftDepth=minDepth(node.left)，否则 leftDepth=∞，这样后面计算 min 不会取到 ∞。对于右儿子也同理，计算出 rightDepth。
     *
     * 最后返回 min(leftDepth,rightDepth)+1。
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        //空节点
        if(root == null){
            return 0;
        }
        //叶子节点
        if(root.left == null && root.right == null){
            return 1;
        }
        //下面包含三种情况, 最注意的就是只有1个子节点(左/右)的情况, 要特殊处理
        //1. root.left == null && root.right != null
        //2. root.right == null && root.left != null
        //3. root.left != null && root.right != null
        int leftDepth = root.left!=null? minDepth(root.left): Integer.MAX_VALUE;
        int rightDepth = root.right!=null? minDepth(root.right): Integer.MAX_VALUE;
        return Math.min(leftDepth, rightDepth) + 1;
    }
}

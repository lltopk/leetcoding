package org.lyflexi.solutions.strategy_retrieval_dfs.from_top_down_retV_top;

import org.lyflexi.common.TreeNode;

/**
 * 101. 对称二叉树
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,690,269/2.7M
 * 通过率
 * 63.8%
 */
public class LC101_isSymmetric {
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root.left, root.right);
    }
    /**
     重载方法
     */
    private boolean isSymmetric(TreeNode p, TreeNode q) {
        //只要任一为null, 则返回false
        //除非两者都为null， 则返回true
        if(p == null || q == null) {
            return p == q;
        }

        return p.val == q.val && isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
    }
}

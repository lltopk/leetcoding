package org.lyflexi.solutions.strategy_retrieval_dfs.from_top_down_retV_top;

import org.lyflexi.common.TreeNode;

/**
 * LCR 145. 判断对称二叉树
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 请设计一个函数判断一棵二叉树是否 轴对称 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [6,7,7,8,9,9,8]
 * 输出：true
 * 解释：从图中可看出树是轴对称的。
 * 示例 2：
 *
 *
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 * 解释：从图中可看出最后一层的节点不对称。
 *
 *
 * 提示：
 *
 * 0 <= 节点个数 <= 1000
 *
 * 注意：本题与主站 101 题相同：https://leetcode.cn/problems/symmetric-tree/
 */
public class LCR145_checkSymmetricTree {
    public boolean checkSymmetricTree(TreeNode root) {
        if(root == null){
            return true;
        }
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

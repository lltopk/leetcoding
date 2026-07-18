package org.lyflexi.solutions.strategy_retrieval_dfs.from_down_top;

import org.lyflexi.common.TreeNode;

/**
 * 404. 左叶子之和
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定二叉树的根节点 root ，返回所有左叶子之和。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入: root = [3,9,20,null,null,15,7]
 * 输出: 24
 * 解释: 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 * 示例 2:
 *
 * 输入: root = [1]
 * 输出: 0
 *
 *
 * 提示:
 *
 * 节点数在 [1, 1000] 范围内
 * -1000 <= Node.val <= 1000
 *
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 415,958/648.6K
 * 通过率
 * 64.1%
 */

/**
 * 自底向上 求左叶子
 */
public class LC404_sumOfLeftLeaves2 {
    /**
     * 这是这道题容易困惑地方是：sumOfLeftLeaves(root.left) 求的是 root.left 子树中所有左叶子的和，它并不会把 root.left 自己算进去。
     *
     * 本质上是因为递归函数只知道当前节点(root)是否为叶子, 但不知道root是不是别人家的左孩子(左叶子)
     *
     * 所以不能自己决定把自己加进去。
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }
        TreeNode left = root.left;

        //剪枝（Pruning）优化, 当确定左叶子之后, 就无需让左叶子继续参与递归sumOfLeftLeaves(root.left)
        if(left !=null && left.left==null && left.right==null){
            return left.val + sumOfLeftLeaves(root.right);
        }
        int leftS = sumOfLeftLeaves(root.left);
        int rightS = sumOfLeftLeaves(root.right);
        return leftS + rightS;
    }
}

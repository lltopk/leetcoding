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
public class LC404_sumOfLeftLeaves {
    /**
     * 这是这道题容易困惑地方是：sumOfLeftLeaves(root.left) 求的是 root.left 子树中所有左叶子的和，它并不会把 root.left 自己算进去。
     *
     * 一般来说递归函数只知道当前节点root是否为空(root==null) 或者 root是否为叶子(root.left==null && root.right==null)
     *
     * 但永远不知道root是不是别人家的左孩子(左叶子), 所以不能自己决定把自己加进去., 因此你必须手动加进去
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }

        //左子树中的左叶子和, 但不包括左子树
        int leftS = sumOfLeftLeaves(root.left);
        //右子树中的左叶子和, 但不包括右子树
        int rightS = sumOfLeftLeaves(root.right);

        //因此下面要再判断左子树本身是不是叶子, 是的话加进答案
        TreeNode left = root.left;
        if(left !=null && left.left==null && left.right==null){
            return left.val + leftS + rightS;
        }
        return leftS + rightS;
    }
}

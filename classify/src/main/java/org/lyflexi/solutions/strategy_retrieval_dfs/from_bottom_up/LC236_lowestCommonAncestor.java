package org.lyflexi.solutions.strategy_retrieval_dfs.from_bottom_up;

import org.lyflexi.common.TreeNode;

/**
 * 236. 二叉树的最近公共祖先
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * 示例 2：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * 示例 3：
 *
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [2, 105] 内。
 * -109 <= Node.val <= 109
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,278,591/1.7M
 * 通过率
 * 75.3%
 */
public class LC236_lowestCommonAncestor {
    /**
     这个函数的返回值判断当前节点是不是LCA的候选项Candidate

     而不是直接判断当前节点是否是LCA
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //|| root == p || root ==q表示当前树存在一个候选项Candidate， 不要求p和q都在当前树, 可以直接返回
        if(root == null || root == p || root ==q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //由于是自底向上， 因此当前节点一定是最近的

        //左右子树都存在候选项， 直接返回当前根节点就好了
        if(left != null && right != null){
            return root;
        }
        //左子树或者右子树任意一个不存在候选项(说明p q在同侧)， 则返回存在候选项的一方, 具体就是p/q
        return left == null? right: left;
    }
}

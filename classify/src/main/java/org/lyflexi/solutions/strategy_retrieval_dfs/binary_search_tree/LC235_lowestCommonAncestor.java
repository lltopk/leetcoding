package org.lyflexi.solutions.strategy_retrieval_dfs.binary_search_tree;

import org.lyflexi.common.TreeNode;

/**
 * 235. 二叉搜索树的最近公共祖先
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 *
 *
 *
 *
 * 示例 1:
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * 示例 2:
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *
 *
 * 说明:
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 550,632/757.4K
 * 通过率
 * 72.7%
 */
public class LC235_lowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //利用二叉搜索树的有序性质, 可以将任意p q都抽象为同侧, 然后对同侧继续递归即可, 因此无需判空
        //对比236. 二叉树的最近公共祖先, 普通二叉树无法确定p q位置, 因此可能跑空, 所以需要判空
        int x = root.val;

        //p q都在左子树
        if(x > p.val && x> q.val){
            return lowestCommonAncestor(root.left, p, q);
        }

        //p q都在右子树
        if(x < p.val && x< q.val){
            return lowestCommonAncestor(root.right, p, q);
        }

        //p q位于两侧, 或者root就是p  或者root就是q
        return root;
    }
}

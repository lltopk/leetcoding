package org.lyflexi.solutions.strategy_retrieval_dfs.from_bottom_up.pair;

import org.lyflexi.common.TreeNode;

/**
 * 1123. 最深叶节点的最近公共祖先
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个有根节点 root 的二叉树，返回它 最深的叶节点的最近公共祖先 。
 *
 * 回想一下：
 *
 * 叶节点 是二叉树中没有子节点的节点
 * 树的根节点的 深度 为 0，如果某一节点的深度为 d，那它的子节点的深度就是 d+1
 * 如果我们假定 A 是一组节点 S 的 最近公共祖先，S 中的每个节点都在以 A 为根节点的子树中，且 A 的深度达到此条件下可能的最大值。
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4]
 * 输出：[2,7,4]
 * 解释：我们返回值为 2 的节点，在图中用黄色标记。
 * 在图中用蓝色标记的是树的最深的节点。
 * 注意，节点 6、0 和 8 也是叶节点，但是它们的深度是 2 ，而节点 7 和 4 的深度是 3 。
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 解释：根节点是树中最深的节点，它是它本身的最近公共祖先。
 * 示例 3：
 *
 * 输入：root = [0,1,3,null,2]
 * 输出：[2]
 * 解释：树中最深的叶节点是 2 ，最近公共祖先是它自己。
 *
 *
 * 提示：
 *
 * 树中的节点数将在 [1, 1000] 的范围内。
 * 0 <= Node.val <= 1000
 * 每个节点的值都是 独一无二 的。
 *
 *
 * 注意：本题与力扣 865 重复：https://leetcode.cn/problems/smallest-subtree-with-all-the-deepest-nodes/
 */

/**
 * 把每棵子树都看成是一个「子问题」, 这可以自底向上
 */
public class LC1123_lcaDeepestLeaves {
    /**
     设自顶向下dfs的返回结构为Pair(height, lca)
     */
    record Pair(int height, TreeNode lca){}
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).lca;
    }

    /**

     */
    private Pair dfs(TreeNode root){
        if(root == null){
            return new Pair(0, null);
        }
        Pair left = dfs(root.left);
        Pair right = dfs(root.right);
        //下面开始向上归

        //左子树更高, 则lca一定在左子树, 把左子树的lca原封不动向上交给当前节点(父节点)
        if(left.height > right.height){
            return new Pair(left.height + 1, left.lca);
        }
        //右子树更高, 则lca一定在右子树, 把右子树的lca原封不动向上交给当前节点(父节点)
        if(right.height > left.height){
            return new Pair(right.height + 1, right.lca);
        }

        //左右子树高度相同, 则lca就是当前节点, 特别的如果最深叶子节点只有1个，那么它自己的最近公共祖先就是它自己。
        return new Pair(left.height + 1, root);
        // return new Pair(right.height + 1, root); //或者
    }
}

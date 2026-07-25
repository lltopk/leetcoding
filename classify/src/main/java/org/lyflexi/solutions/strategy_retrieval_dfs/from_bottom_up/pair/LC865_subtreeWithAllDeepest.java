package org.lyflexi.solutions.strategy_retrieval_dfs.from_bottom_up.pair;

import org.lyflexi.common.TreeNode;

/**
 * 865. 具有所有最深节点的最小子树
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个根为 root 的二叉树，每个节点的深度是 该节点到根的最短距离 。
 *
 * 返回包含原始树中所有 最深节点 的 最小子树 。
 *
 * 如果一个节点在 整个树 的任意节点之间具有最大的深度，则该节点是 最深的 。
 *
 * 一个节点的 子树 是该节点加上它的所有后代的集合。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4]
 * 输出：[2,7,4]
 * 解释：
 * 我们返回值为 2 的节点，在图中用黄色标记。
 * 在图中用蓝色标记的是树的最深的节点。
 * 注意，节点 5、3 和 2 包含树中最深的节点，但节点 2 的子树最小，因此我们返回它。
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 解释：根节点是树中最深的节点。
 * 示例 3：
 *
 * 输入：root = [0,1,3,null,2]
 * 输出：[2]
 * 解释：树中最深的节点为 2 ，有效子树为节点 2、1 和 0 的子树，但节点 2 的子树最小。
 *
 *
 * 提示：
 *
 * 树中节点的数量在 [1, 500] 范围内。
 * 0 <= Node.val <= 500
 * 每个节点的值都是 独一无二 的。
 *
 *
 * 注意：本题与力扣 1123 重复：https://leetcode.cn/problems/lowest-common-ancestor-of-deepest-leaves
 */
public class LC865_subtreeWithAllDeepest {
    /**
     设自顶向下dfs的返回结构为Pair(height, lca)
     */
    record Pair(int height, TreeNode lca){}
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
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

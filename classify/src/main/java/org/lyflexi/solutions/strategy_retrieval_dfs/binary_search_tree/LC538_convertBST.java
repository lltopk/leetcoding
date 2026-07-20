package org.lyflexi.solutions.strategy_retrieval_dfs.binary_search_tree;

import org.lyflexi.common.TreeNode;

/**
 * 538. 把二叉搜索树转换为累加树
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给出二叉 搜索 树的根节点 root，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），将其转换为一个更大的树，使得原始二叉搜索树中的每个节点值都变为原本值加上原本二叉搜索树中所有比该节点值大的节点值的总和。
 *
 * 提醒一下，二叉搜索树满足下列约束条件：
 *
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 * 注意：本题和 1038: https://leetcode.cn/problems/binary-search-tree-to-greater-sum-tree/ 相同
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * 示例 2：
 *
 * 输入：root = [0,null,1]
 * 输出：[1,null,1]
 * 示例 3：
 *
 * 输入：root = [1,0,2]
 * 输出：[3,3,2]
 * 示例 4：
 *
 * 输入：root = [3,2,4,1]
 * 输出：[7,9,4,10]
 *
 *
 * 提示：
 *
 * 树中的节点数介于 0 和 104 之间。
 * 每个节点的值介于 -104 和 104 之间。
 * 树中的所有值 互不相同 。
 * 给定的树为二叉搜索树。
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 358,796/455.8K
 * 通过率
 * 78.7%
 */
public class LC538_convertBST {
    int s = 0;//后缀和
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }
    private void dfs(TreeNode root){
        if(root == null){
            return;
        }

        //先递归右子树，把右边的值累加给当前节点
        dfs(root.right);
        s += root.val;
        root.val = s;
        dfs(root.left);
    }
}

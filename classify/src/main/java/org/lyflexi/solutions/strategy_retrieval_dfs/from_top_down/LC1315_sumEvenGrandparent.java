package org.lyflexi.solutions.strategy_retrieval_dfs.from_top_down;

import org.lyflexi.common.TreeNode;

/**
 * 1315. 祖父节点值为偶数的节点和
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一棵二叉树，请你返回满足以下条件的所有节点的值之和：
 *
 * 该节点的祖父节点的值为偶数。（一个节点的祖父节点是指该节点的父节点的父节点。）
 * 如果不存在祖父节点值为偶数的节点，那么返回 0 。
 *
 *
 *
 * 示例：
 *
 *
 *
 * 输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * 输出：18
 * 解释：图中红色节点的祖父节点的值为偶数，蓝色节点为这些红色节点的祖父节点。
 *
 *
 * 提示：
 *
 * 树中节点的数目在 1 到 10^4 之间。
 * 每个节点的值在 1 到 100 之间。
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 28,313/34.2K
 * 通过率
 * 82.9%
 */
public class LC1315_sumEvenGrandparent {
    int ret = 0;
    public int sumEvenGrandparent(TreeNode root) {
        dfs(root, null, null);
        return ret;
    }
    private void dfs(TreeNode root, TreeNode parent, TreeNode grandParent){
        if(root == null){
            return;
        }
        if(grandParent!=null && (grandParent.val & 1) == 0){
            ret += root.val;
        }
        //下一轮当前节点作为父节点， 父节点作为爷节点
        dfs(root.left, root, parent);
        dfs(root.right, root, parent);
    }
}

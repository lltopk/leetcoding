package org.lyflexi.solutions.strategy_retrieval_dfs;

import org.lyflexi.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 872. 叶子相似的树
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 *
 *
 *
 * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
 *
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 *
 * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * 输出：true
 * 示例 2：
 *
 *
 *
 * 输入：root1 = [1,2,3], root2 = [1,3,2]
 * 输出：false
 *
 *
 * 提示：
 *
 * 给定的两棵树结点数在 [1, 200] 范围内
 * 给定的两棵树上的值在 [0, 200] 范围内
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 114,659/174.7K
 * 通过率
 * 65.6%
 */
public class LC872_leafSimilar {
    /**
     * 按照同样的 DFS 顺序遍历这两棵二叉树，比如都按照先左子树再右子树的方式。
     *
     * 遍历到叶子时，把节点值加到一个列表中。
     *
     * 最后判断两个列表是否相同。
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        return preorderTraversal(root1).equals(preorderTraversal(root2));
    }
    /**
     都按照前序遍历, 或者中序遍历或者后续遍历都可以
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        dfs(root, ret);
        return ret;
    }
    private void dfs(TreeNode root, List<Integer> ret){
        if(root == null){
            return;
        }
        //叶子节点特判
        if(root.left == null && root.right==null){
            ret.add(root.val);
        }
        dfs(root.left, ret);
        dfs(root.right, ret);
    }
}

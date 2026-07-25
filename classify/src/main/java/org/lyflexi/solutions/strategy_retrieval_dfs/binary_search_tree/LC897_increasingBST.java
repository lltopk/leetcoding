package org.lyflexi.solutions.strategy_retrieval_dfs.binary_search_tree;

import org.lyflexi.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 897. 递增顺序搜索树
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一棵二叉搜索树的 root ，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 * 示例 2：
 *
 *
 * 输入：root = [5,1,7]
 * 输出：[1,null,5,null,7]
 *
 *
 * 提示：
 *
 * 树中节点数的取值范围是 [1, 100]
 * 0 <= Node.val <= 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 92,334/125.1K
 * 通过率
 * 73.8%
 */
public class LC897_increasingBST {
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        dfs(root, ret);
        TreeNode dummy = new TreeNode();
        TreeNode cur = dummy;
        for(int x: ret){
            //构造（改变）树
            cur.right = new TreeNode(x);
            cur = cur.right;
        }
        return dummy.right;
    }
    private void dfs(TreeNode root, List<Integer> ret){
        if(root == null){
            return ;
        }
        dfs(root.left, ret);
        ret.add(root.val);
        dfs(root.right, ret);
    }
}

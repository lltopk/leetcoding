package org.lyflexi.solutions.strategy_retrieval_dfs.from_top_down;

import org.lyflexi.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 144. 二叉树的前序遍历
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [1,null,2,3]
 *
 * 输出：[1,2,3]
 *
 * 解释：
 *
 *
 *
 * 示例 2：
 *
 * 输入：root = [1,2,3,4,5,null,8,null,null,6,7,9]
 *
 * 输出：[1,2,4,5,6,7,3,8,9]
 *
 * 解释：
 *
 *
 *
 * 示例 3：
 *
 * 输入：root = []
 *
 * 输出：[]
 *
 * 示例 4：
 *
 * 输入：root = [1]
 *
 * 输出：[1]
 *
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 */
public class LC144_preorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        dfs(root, ret);
        return ret;
    }
    private void dfs(TreeNode root, List<Integer> ret){
        if(root == null){
            return;
        }
        ret.add(root.val);
        dfs(root.left, ret);
        dfs(root.right, ret);
    }
}

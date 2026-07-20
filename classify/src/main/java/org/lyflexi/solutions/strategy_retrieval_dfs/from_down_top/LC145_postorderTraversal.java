package org.lyflexi.solutions.strategy_retrieval_dfs.from_down_top;

import org.lyflexi.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 145. 二叉树的后序遍历
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [1,null,2,3]
 *
 * 输出：[3,2,1]
 *
 * 解释：
 *
 *
 *
 * 示例 2：
 *
 * 输入：root = [1,2,3,4,5,null,8,null,null,6,7,9]
 *
 * 输出：[4,6,7,5,2,9,8,3,1]
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
 * 树中节点的数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,011,101/1.3M
 * 通过率
 * 77.9%
 */
public class LC145_postorderTraversal {
    List<Integer> ret = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        dfs(root);
        return ret;
    }
    private void dfs(TreeNode root){
        if(root == null){
            return;
        }
        dfs(root.left);
        dfs(root.right);
        ret.add(root.val);
    }
}

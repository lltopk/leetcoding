package org.lyflexi.solutions.strategy_retrieval_dfs.from_up_bottom;

import org.lyflexi.common.TreeNode;

/**
 * 1302. 层数最深叶子节点的和
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 输出：15
 * 示例 2：
 *
 * 输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * 输出：19
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 104] 之间。
 * 1 <= Node.val <= 100
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 77,407/90.4K
 * 通过率
 * 85.7%
 */
public class LC1302_deepestLeavesSum {
    int ret = 0;
    int mxDepth = 0; //业务属性(比较标准)
    public int deepestLeavesSum(TreeNode root) {
        dfs(root, 0);
        return ret;
    }
    private void dfs(TreeNode root, int depth){
        if(root == null){
            return;
        }
        if(depth + 1 > mxDepth){
            mxDepth = depth + 1;
            ret = root.val;
        }else if(depth + 1 == mxDepth){
            ret += root.val;
        }
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }
}

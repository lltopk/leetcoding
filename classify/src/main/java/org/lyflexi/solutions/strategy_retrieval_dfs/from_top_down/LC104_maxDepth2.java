package org.lyflexi.solutions.strategy_retrieval_dfs.from_top_down;

/**
 * 104. 二叉树的最大深度
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二叉树 root ，返回其最大深度。
 *
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：3
 * 示例 2：
 *
 * 输入：root = [1,null,2]
 * 输出：2
 *
 *
 * 提示：
 *
 * 树中节点的数量在 [0, 104] 区间内。
 * -100 <= Node.val <= 100
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 2,075,241/2.6M
 * 通过率
 * 79.0%
 */

import org.lyflexi.common.TreeNode;

/**
 * 自顶向下DFS
 */
public class LC104_maxDepth2 {
    public int maxDepth(TreeNode root) {
        //创建一个引用数组
        int[] ret = new int[1];
        dfs(root, ret, 0);
        return ret[0];
    }

    /**
     * 如果要传结果进去, 不能是普通变量int, 必须是引用类型int[]
     * @param root
     * @param ret
     * @param depth
     */
    private void dfs(TreeNode root, int[] ret, int depth){
        if(root == null){
            return;
        }
        ret[0] = Math.max(++depth, ret[0]);
        dfs(root.left, ret, depth);
        dfs(root.right, ret, depth);
    }
}

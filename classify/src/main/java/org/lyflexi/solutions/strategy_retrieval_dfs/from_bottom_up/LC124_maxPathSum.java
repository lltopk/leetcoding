package org.lyflexi.solutions.strategy_retrieval_dfs.from_bottom_up;

import org.lyflexi.common.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 示例 2：
 *
 *
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *
 *
 * 提示：
 *
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 759,900/1.6M
 * 通过率
 * 48.3%
 */
public class LC124_maxPathSum {
    int ret = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return ret;
    }
    /**
     这个dfs: 自底向上求最大一侧链和
     整个迭代过程中, 根据dfs(root.left)和dfs(root.right)求两侧链和最大值, 即为最大路径和
     */
    private int dfs(TreeNode root){
        if(root == null){
            return 0;//求和, 则叶子节点 0 + leaf.val = leaf.val
        }
        //注意左右链的和都不能小于0, 否则记为0表示不选
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);

        //特别的当所有节点都为负数, 路径中只有一个节点是最优的, 毕竟节点越多，元素和越小
        ret = Math.max(ret, left + right + root.val);
        return Math.max(left, right) + root.val;
    }
}

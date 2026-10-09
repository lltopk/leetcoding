package org.lyflexi;

import java.util.*;

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
 * 示例 1：
 * 
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 
 * 示例 2：
 * 
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * 
 * 提示：
 * 
 * - 树中节点数目范围是 [1, 3 * 10^4]
 * 
 * - -1000 <= Node.val <= 1000
 */

/**
 * 答疑
 * 
 * 问：如果所有节点值都是负数，代码会算出什么结果？
 * 
 * 答：在所有节点值都为负数的情况下，代码中的 ans = max(ans, sum_l + node.val + sum_r) 等价于 ans = max(ans, node.val)，我们求的是最大节点值（绝对值最小的负数）。这是符合题目要求的，因为在所有节点值都为负数的情况下，路径只有一个节点是最优的（毕竟节点越多，元素和越小）。类比 53. 最大子数组和，当数组元素都是负数的时候，答案就是 $max(nums)$。
 */
public class Hot050_LC124_maxPathSum {
    private int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0; // 没有节点，和为 0
        }
        int sumL = dfs(node.left); // 左子树最大链和
        int sumR = dfs(node.right); // 右子树最大链和
        ans = Math.max(ans, sumL + node.val + sumR); // 左链 + node + 右链 = 路径
        return Math.max(Math.max(sumL, sumR) + node.val, 0); // 当前子树最大链和（注意这里和 0 取最大值了）
    }
}

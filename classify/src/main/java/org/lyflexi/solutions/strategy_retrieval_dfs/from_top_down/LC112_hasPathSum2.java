package org.lyflexi.solutions.strategy_retrieval_dfs.from_top_down;

import org.lyflexi.common.TreeNode;

/**
 * 112. 路径总和
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * 解释：等于目标和的根节点到叶节点路径如上图所示。
 * 示例 2：
 *
 *
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 * 解释：树中存在两条根节点到叶子节点的路径：
 * (1 --> 2): 和为 3
 * (1 --> 3): 和为 4
 * 不存在 sum = 5 的根节点到叶子节点的路径。
 * 示例 3：
 *
 * 输入：root = [], targetSum = 0
 * 输出：false
 * 解释：由于树是空的，所以不存在根节点到叶子节点的路径。
 *
 *
 * 提示：
 *
 * 树中节点的数目在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 916,801/1.6M
 * 通过率
 * 56.0%
 */

/**
 * 自定向下 简洁写法
 */
public class LC112_hasPathSum2 {
    /**
     * 「倒着减小」可以让我们直接递归调用 hasPathSum：
     *
     * 把 targetSum 减少 root.val。
     * 如果 root 是叶子节点：如果 targetSum=0，返回 true，否则返回 false。
     * 递归左子树，如果左子树返回 true，那么当前子树就返回 true，否则返回递归右子树的结果。
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        targetSum -= root.val;
        //叶子节点
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        }

        //如果上面返回true, 向上归一路返回 true || hasPathSum(root.right, targetSum);, 直到根节点接收到true
        //如果上面返回false, 向上归一路返回 false || hasPathSum(root.right, targetSum);, 则递归右子树hasPathSum(root.right, targetSum)
        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
    }
}

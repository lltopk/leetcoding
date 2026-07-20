package org.lyflexi.solutions.strategy_retrieval_dfs.from_top_down_retV;

import org.lyflexi.common.TreeNode;

/**
 * 1448. 统计二叉树中好节点的数目
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
 *
 * 「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [3,1,4,3,null,1,5]
 * 输出：4
 * 解释：图中蓝色节点为好节点。
 * 根节点 (3) 永远是个好节点。
 * 节点 4 -> (3,4) 是路径中的最大值。
 * 节点 5 -> (3,4,5) 是路径中的最大值。
 * 节点 3 -> (3,1,3) 是路径中的最大值。
 * 示例 2：
 *
 *
 *
 * 输入：root = [3,3,null,4,2]
 * 输出：3
 * 解释：节点 2 -> (3, 3, 2) 不是好节点，因为 "3" 比它大。
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：1
 * 解释：根节点是好节点。
 *
 *
 * 提示：
 *
 * 二叉树中节点数目范围是 [1, 10^5] 。
 * 每个节点权值的范围是 [-10^4, 10^4] 。
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 83,738/114K
 * 通过率
 * 73.5%
 */
public class LC1448_goodNodes {
    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE); // 也可以写 root.val
    }

    /**
     * 自顶向下， 同时带有返回值
     * @param root
     * @param mx
     * @return
     */
    private int dfs(TreeNode root, int mx) {
        if (root == null)
            return 0;
        mx = Math.max(mx, root.val);
        int left = dfs(root.left, mx);
        int right = dfs(root.right, mx);
        // 由于mx是基本类型， 所以当前节点的mx不是左右子树中的mx
        return left + right + (mx <= root.val ? 1 : 0);
    }
}

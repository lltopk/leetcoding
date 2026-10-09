package org.lyflexi;

import java.util.*;

/**
 * 98. 验证二叉搜索树
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 
 * 有效 二叉搜索树定义如下：
 * 
 * - 节点的左子树只包含 严格小于 当前节点的数。
 * 
 * - 节点的右子树只包含 严格大于 当前节点的数。
 * 
 * - 所有左子树和右子树自身必须也是二叉搜索树。
 * 
 * 示例 1：
 * 
 * 输入：root = [2,1,3]
 * 输出：true
 * 
 * 示例 2：
 * 
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 * 
 * 提示：
 * 
 * - 树中节点数目范围在[1, 10^4] 内
 * 
 * - -2^31 <= Node.val <= 2^31 - 1
 */

/**
 * 方法三：后序遍历
 * 
 * $dfs$ 返回子树的最小值和最大值，供上面的节点判断是否为二叉搜索树。
 */
public class Hot043_LC98_isValidBST3 {
    public boolean isValidBST(TreeNode root) {
        return dfs(root)[1] != Long.MAX_VALUE;
    }

    private long[] dfs(TreeNode node) {
        if (node == null) {
            return new long[]{Long.MAX_VALUE, Long.MIN_VALUE};
        }
        long[] left = dfs(node.left);
        long[] right = dfs(node.right);
        long x = node.val;
        // 也可以在递归完左子树之后立刻判断，如果发现不是二叉搜索树，就不用递归右子树了
        if (x <= left[1] || x >= right[0]) {
            return new long[]{Long.MIN_VALUE, Long.MAX_VALUE};
        }
        return new long[]{Math.min(left[0], x), Math.max(right[1], x)};
    }
}

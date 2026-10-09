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
 * 答疑
 * 
 * 问：如何证明，如果二叉树的中序遍历是严格递增的，那么二叉树一定是二叉搜索树？
 * 
 * 答：已知条件为，中序遍历是严格递增的。我们要证明这棵二叉树是二叉搜索树。对于这棵二叉树的任意节点 $x$，中序遍历中的在 $x$ 左边的点都是遍历过的点，这包含 $x$ 的左子树，所以 $x$ 的左子树的节点值都严格小于 $x$ 的节点值。中序遍历中的在 $x$ 右边的点都是未遍历过的点，这包含 $x$ 的右子树，所以 $x$ 的右子树的节点值都严格大于 $x$ 的节点值。所以这棵二叉树的每个节点都满足二叉搜索树的性质，所以这棵二叉树是二叉搜索树。
 */
public class Hot043_LC98_isValidBST2 {
    private long pre = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) { // 左
            return false;
        }
        if (root.val <= pre) { // 中
            return false;
        }
        pre = root.val;
        return isValidBST(root.right); // 右
    }
}

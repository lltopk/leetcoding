package org.lyflexi;

import java.util.*;

/**
 * 236. 二叉树的最近公共祖先
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 
 * 示例 1：
 * 
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * 
 * 示例 2：
 * 
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * 
 * 示例 3：
 * 
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 * 
 * 提示：
 * 
 * - 树中节点数目在范围 [2, 10^5] 内。
 * 
 * - -10^9 <= Node.val <= 10^9
 * 
 * - 所有 Node.val 互不相同 。
 * 
 * - p != q
 * 
 * - p 和 q 均存在于给定的二叉树中。
 */

/**
 * 答疑
 * 
 * 问：$lowestCommonAncestor$ 函数的返回值是什么意思？
 * 
 * 答：返回值的准确含义是「最近公共祖先的候选项」。对于最外层的递归调用者来说，返回值是最近公共祖先的意思。但是，在递归过程中，返回值可能是最近公共祖先，也可能是空节点（表示子树内没找到任何有用信息）、节点 $p$ 或者节点 $q$（可能成为最近公共祖先，或者用来辅助判断上面的某个节点是否为最近公共祖先）。
 * 
 * 问：为什么发现当前节点是 $p$ 或者 $q$ 就不再往下递归了？万一下面有 $q$ 或者 $p$ 呢？
 * 
 * 答：如果下面有 $q$ 或者 $p$，那么当前节点就是最近公共祖先，直接返回当前节点。如果下面没有，那既然都没有要找的节点了，也不需要递归，直接返回当前节点。（注意题目保证 $p$ 和 $q$ 都在二叉树中）
 */
public class Hot049_LC236_lowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root; // 找到 p 或 q 就不往下递归了，原因见上面答疑
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) { // 左右都找到
            return root; // 当前节点是最近公共祖先
        }
        // 如果只有左子树找到，就返回左子树的返回值
        // 如果只有右子树找到，就返回右子树的返回值
        // 如果左右子树都没有找到，就返回 null（注意此时 right = null）
        return left != null ? left : right;
    }
}

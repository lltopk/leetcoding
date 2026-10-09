package org.lyflexi;

import java.util.*;

/**
 * 101. 对称二叉树
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 
 * 示例 1：
 * 
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 
 * 示例 2：
 * 
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 * 
 * 提示：
 * 
 * - 树中节点数目在范围 [1, 1000] 内
 * 
 * - -100 <= Node.val <= 100
 * 
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 */

/**
 * 思路
 * 
 * 把输入的二叉树拆分成左子树 $p$ 和右子树 $q$。我们需要判断 $p$ 和 $q$ 是否互为镜像。
 * 
 * 类似 100. 相同的树，必须满足：
 * 
 * - $p.val$ 等于 $q.val$。
 * - $p$ 的左儿子与 $q$ 的右儿子互为镜像。这是一个和原问题相似的子问题，可以递归判断。
 * - $p$ 的右儿子与 $q$ 的左儿子互为镜像。这是一个和原问题相似的子问题，可以递归判断。
 */
public class Hot039_LC101_isSymmetric {
    public boolean isSymmetric(TreeNode root) {
        return isSameTree(root.left, root.right);
    }

    // 100. 相同的树（改成镜像判断）
    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }
        return p.val == q.val && isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
    }
}

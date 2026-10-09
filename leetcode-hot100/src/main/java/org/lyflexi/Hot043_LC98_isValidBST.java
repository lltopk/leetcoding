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
 * 问：为什么 Java 等语言要用 $long$ 类型？题目不是只有 $int$ 类型吗？
 * 
 * 答：虽然题目是 $int$ 类型，但开始递归的时候，$left$ 需要比所有节点值都要小，$right$ 需要比所有节点值都要大，如果节点值刚好是 $int$ 的最小值/最大值，就没有这样的 $left$ 和 $right$ 了，所以需要用 $long$ 类型。
 */
public class Hot043_LC98_isValidBST {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode node, long left, long right) {
        if (node == null) {
            return true;
        }
        long x = node.val;
        return left < x && x < right &&
               isValidBST(node.left, left, x) &&
               isValidBST(node.right, x, right);
    }
}

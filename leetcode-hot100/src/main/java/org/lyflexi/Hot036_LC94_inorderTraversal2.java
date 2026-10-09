package org.lyflexi;

import java.util.*;

/**
 * 94. 二叉树的中序遍历
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 * 
 * 示例 1：
 * 
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * 
 * 示例 2：
 * 
 * 输入：root = []
 * 输出：[]
 * 
 * 示例 3：
 * 
 * 输入：root = [1]
 * 输出：[1]
 * 
 * 提示：
 * 
 * - 树中节点数目在范围 [0, 100] 内
 * 
 * - -100 <= Node.val <= 100
 * 
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */

/**
 * 能不能做到 $O(1)$ 空间？不能写递归，也不能用栈模拟递归。
 * 
 * 请看下图：
 */
public class Hot036_LC94_inorderTraversal2 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        while (root != null) {
            if (root.left != null) {
                // 找 root 的前驱 pre：在中序遍历中，root 的上一个节点
                // 从 root.left 开始，一直向右走，直到走到尽头，或者遇到指向 root 的线索（回到 root 的路）
                TreeNode pre = root.left;
                while (pre.right != null && pre.right != root) {
                    pre = pre.right;
                }

                // root 的左子树尚未访问
                if (pre.right == null) {
                    pre.right = root; // 建立线索（回到 root 的路），相当于把 pre.right 当作栈
                    root = root.left; // 访问左子树
                    continue;
                }

                // root 的左子树访问完毕，去掉线索，恢复原样
                pre.right = null; // 注：如果调用完 inorderTraversal 不再使用这棵二叉树，这行代码可以去掉
            }

            // root 的左子树访问完毕
            ans.add(root.val); // 记录当前节点的值
            root = root.right; // 如果有右子树就访问右子树，没有就顺着线索回到指向的节点
        }

        return ans;
    }
}

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
 * 什么是中序遍历？
 * 
 * 二叉树有三种常见的遍历方式：
 * 
 * - 前序遍历：根-左-右。先获取根节点值，再访问根的左子树，最后访问根的右子树。
 * - 中序遍历：左-根-右。先访问根的左子树，再获取根节点值，最后访问根的右子树。
 * - 后序遍历：左-右-根。先访问根的左子树，再访问根的右子树，最后获取根节点值。
 * 
 * 对于中序遍历，我们有如下递归代码。不了解递归的同学，可以看视频讲解【基础算法精讲 09】。
 */
public class Hot036_LC94_inorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(ans, root);
        return ans;
    }

    private void dfs(List<Integer> ans, TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(ans, node.left);  // 左
        ans.add(node.val);    // 根（这行代码移到前面就是前序，移到后面就是后序）
        dfs(ans, node.right); // 右
    }
}

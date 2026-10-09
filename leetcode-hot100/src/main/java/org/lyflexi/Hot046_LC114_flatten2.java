package org.lyflexi;

import java.util.*;

/**
 * 114. 二叉树展开为链表
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 
 * - 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 
 * - 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * 
 * 示例 1：
 * 
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 
 * 示例 2：
 * 
 * 输入：root = []
 * 输出：[]
 * 
 * 示例 3：
 * 
 * 输入：root = [0]
 * 输出：[0]
 * 
 * 提示：
 * 
 * - 树中结点数在范围 [0, 2000] 内
 * 
 * - -100 <= Node.val <= 100
 * 
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 */

/**
 * 方法二：分治
 * 
 * 方法一需要用到一个在 DFS 之外的变量 $head$，能否只在 DFS 中解决呢？
 * 
 * 考虑分治，假如我们计算出了 $root = 1$ 左子树的链表 $2\to 3\to 4$，以及右子树的链表 $5\to 6$，那么接下来只需要穿针引线，把节点 $1$ 和两条链表连起来：
 * 
 * 1. 先把 $2\to 3\to 4$ 和 $5\to 6$ 连起来，也就是左子树链表尾节点 $4$ 的 $right$ 更新为节点 $5$（即 $root.right$），得到 $2\to 3\to 4\to 5\to 6$。
 * 2. 然后把 $1$ 和 $2\to 3\to 4\to 5\to 6$ 连起来，也就是节点 $1$ 的 $right$ 更新为节点 $2$（即 $root.left$），得到 $1\to 2\to 3\to 4\to 5\to 6$。
 * 3. 最后把 $root.left$ 置为空。
 * 
 * 上面的过程，我们需要知道左子树链表的尾节点 $4$。所以 DFS 需要返回链表的尾节点。
 * 
 * 链表合并完成后，返回合并后的链表的尾节点，也就是右子树链表的尾节点。如果右子树是空的，则返回左子树链表的尾节点。如果左右子树都是空的，返回当前节点。
 */
public class Hot046_LC114_flatten2 {
    public void flatten(TreeNode root) {
        dfs(root);
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftTail = dfs(root.left);
        TreeNode rightTail = dfs(root.right);
        if (leftTail != null) {
            leftTail.right = root.right; // 左子树链表的尾节点 -> 右子树链表的头节点
            root.right = root.left; // root -> 左子树链表的头节点
            root.left = null;
        }
        return rightTail != null ? rightTail : leftTail != null ? leftTail : root;
    }
}

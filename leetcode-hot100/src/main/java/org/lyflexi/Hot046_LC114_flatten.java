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
 * 方法一：头插法
 * 
 * 采用头插法构建链表，也就是从节点 $6$ 开始，在 $6$ 的前面插入 $5$，在 $5$ 的前面插入 $4$，依此类推。
 * 
 * 为此，要按照 $6\to 5\to 4\to 3\to 2\to 1$ 的顺序访问节点。如何遍历二叉树，才能实现这个顺序？
 * 
 * 既然 $1\to 2\to 3\to 4\to 5\to 6$ 是先序遍历，那么 $6\to 5\to 4\to 3\to 2\to 1$ 就是先序遍历的逆序，即按照右 - 左 - 根的顺序遍历二叉树。
 * 
 * 遍历的同时执行头插法，把当前节点插在链表头节点 $head$ 的前面，然后更新 $head$ 为当前节点。一开始 $head$ 是空节点。
 */
public class Hot046_LC114_flatten {
    private TreeNode head;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        // 右 - 左 - 根
        flatten(root.right);
        flatten(root.left);
        root.left = null;
        root.right = head; // 在头节点 head 的前面插入 root
        head = root; // 现在头节点是 root
    }
}

package org.lyflexi;

import java.util.*;

/**
 * 230. 二叉搜索树中第 K 小的元素
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（k 从 1 开始计数）。
 * 
 * 示例 1：
 * 
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * 
 * 示例 2：
 * 
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 * 
 * 提示：
 * 
 * - 树中的节点数为 n 。
 * 
 * - 1 <= k <= n <= 10^4
 * 
 * - 0 <= Node.val <= 10^4
 * 
 * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 */

/**
 * 写法一：记录答案
 * 
 * 在中序遍历，即「左-根-右」的过程中，每次递归完左子树，就把 $k$ 减少 $1$，表示我们按照中序遍历访问到了一个节点。如果减一后 $k$ 变成 $0$，那么答案就是当前节点的值，用一个外部变量 $ans$ 记录。
 */
public class Hot044_LC230_kthSmallest {
    private int ans;
    private int k;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode node) {
        if (node == null || k <= 0) {
            return;
        }
        dfs(node.left); // 左
        if (--k == 0) {
            ans = node.val; // 根
        }
        dfs(node.right); // 右
    }
}

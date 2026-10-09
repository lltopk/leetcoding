package org.lyflexi;

import java.util.*;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * 
 * 示例 1:
 * 
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * 
 * 示例 2:
 * 
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 * 
 * 提示:
 * 
 * - 1 <= preorder.length <= 3000
 * 
 * - inorder.length == preorder.length
 * 
 * - -3000 <= preorder[i], inorder[i] <= 3000
 * 
 * - preorder 和 inorder 均 无重复 元素
 * 
 * - inorder 均出现在 preorder
 * 
 * - preorder 保证 为二叉树的前序遍历序列
 * 
 * - inorder 保证 为二叉树的中序遍历序列
 */

/**
 * 写法二
 * 
 * 上面的写法有两个优化点：
 * 
 * 1. 用一个哈希表（或者数组）预处理 $inorder$ 每个元素的下标，这样就可以 $O(1)$ 查到 $preorder[0]$ 在 $inorder$ 的位置，从而 $O(1)$ 知道左子树的大小。
 * 2. 把递归参数改成子数组下标区间（左闭右开区间）的左右端点，从而避免复制数组。
 */
public class Hot047_LC105_buildTree2 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        Map<Integer, Integer> index = new HashMap<>(n); // 预分配空间
        for (int i = 0; i < n; i++) {
            index.put(inorder[i], i);
        }
        return dfs(0, n, 0, preorder, index); // 左闭右开区间
    }

    // 根据 preorder 的子数组 [preL,preR) 和 inorder 的子数组 [inL,inR) 生成二叉树，其中 inR 没用到，可以省略
    private TreeNode dfs(int preL, int preR, int inL, int[] preorder, Map<Integer, Integer> index) {
        if (preL == preR) { // 空节点
            return null;
        }
        int leftSize = index.get(preorder[preL]) - inL; // 左子树的大小
        TreeNode left = dfs(preL + 1, preL + 1 + leftSize, inL, preorder, index);
        TreeNode right = dfs(preL + 1 + leftSize, preR, inL + 1 + leftSize, preorder, index);
        return new TreeNode(preorder[preL], left, right);
    }
}

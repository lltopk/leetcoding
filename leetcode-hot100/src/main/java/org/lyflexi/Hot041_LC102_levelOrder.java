package org.lyflexi;

import java.util.*;

/**
 * 102. 二叉树的层序遍历
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * 
 * 示例 1：
 * 
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 * 
 * 示例 2：
 * 
 * 输入：root = [1]
 * 输出：[[1]]
 * 
 * 示例 3：
 * 
 * 输入：root = []
 * 输出：[]
 * 
 * 提示：
 * 
 * - 树中节点数目在范围 [0, 2000] 内
 * 
 * - -1000 <= Node.val <= 1000
 */

/**
 * 方法一：两个数组
 */
public class Hot041_LC102_levelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<TreeNode> cur = Arrays.asList(root);
        while (!cur.isEmpty()) {
            List<TreeNode> nxt = new ArrayList<>();
            List<Integer> vals = new ArrayList<>(cur.size()); // 预分配空间
            for (TreeNode node : cur) {
                vals.add(node.val);
                if (node.left != null)  nxt.add(node.left);
                if (node.right != null) nxt.add(node.right);
            }
            cur = nxt;
            ans.add(vals);
        }
        return ans;
    }
}

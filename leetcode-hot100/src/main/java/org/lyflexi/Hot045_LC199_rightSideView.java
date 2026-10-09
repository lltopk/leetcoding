package org.lyflexi;

import java.util.*;

/**
 * 199. 二叉树的右视图
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 
 * 示例 1：
 * 
 * 输入：root = [1,2,3,null,5,null,4]
 * 
 * 输出：[1,3,4]
 * 
 * 解释：
 * 
 * 示例 2：
 * 
 * 输入：root = [1,2,3,4,null,null,null,5]
 * 
 * 输出：[1,3,4,5]
 * 
 * 解释：
 * 
 * 示例 3：
 * 
 * 输入：root = [1,null,3]
 * 
 * 输出：[1,3]
 * 
 * 示例 4：
 * 
 * 输入：root = []
 * 
 * 输出：[]
 * 
 * 提示:
 * 
 * - 二叉树的节点个数的范围是 [0,100]
 * 
 * - -100 <= Node.val <= 100
 */

/**
 * 方法一：BFS
 * 
 * 前置题目：102. 二叉树的层序遍历，视频讲解【基础算法精讲 13】。
 * 
 * 套用 102 题的 BFS 模板，把每一层的最后一个节点值保存到答案中。
 */
public class Hot045_LC199_rightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> ans = new ArrayList<>();
        List<TreeNode> cur = Arrays.asList(root);
        while (!cur.isEmpty()) {
            ans.add(cur.get(cur.size() - 1).val);
            List<TreeNode> nxt = new ArrayList<>();
            for (TreeNode node : cur) {
                if (node.left != null)  nxt.add(node.left);
                if (node.right != null) nxt.add(node.right);
            }
            cur = nxt;
        }
        return ans;
    }
}

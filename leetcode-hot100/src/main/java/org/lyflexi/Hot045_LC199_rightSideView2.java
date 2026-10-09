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
 * 方法二：DFS
 * 
 * 视频讲解：【基础算法精讲 10】，欢迎点赞关注~
 * 
 * 先递归右子树，再递归左子树，当某个深度首次到达时，对应的节点就在右视图中。
 */
public class Hot045_LC199_rightSideView2 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, 0, ans);
        return ans;
    }

    private void dfs(TreeNode root, int depth, List<Integer> ans) {
        if (root == null) {
            return;
        }
        if (depth == ans.size()) { // 这个深度首次遇到
            ans.add(root.val);
        }
        dfs(root.right, depth + 1, ans); // 先递归右子树，保证首次遇到的一定是最右边的节点
        dfs(root.left, depth + 1, ans);
    }
}

package org.lyflexi.solutions.strategy_retrieval_dfs.from_top_down;

/**
 * 199. 二叉树的右视图
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [1,2,3,null,5,null,4]
 *
 * 输出：[1,3,4]
 *
 * 解释：
 *
 *
 *
 * 示例 2：
 *
 * 输入：root = [1,2,3,4,null,null,null,5]
 *
 * 输出：[1,3,4,5]
 *
 * 解释：
 *
 *
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
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 871,057/1.2M
 * 通过率
 * 73.7%
 */

import org.lyflexi.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 自顶向下
 */
public class LC199_rightSideView {
    List<Integer> ret = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root,0);
        return ret;
    }

    /**
     先递归右子树，再递归左子树, 同时维护深度,

     当深度等于答案长度的时候, 加入节点
     */
    private void dfs(TreeNode root, int depth){
        if(root == null){
            return;
        }
        if(depth == ret.size()){
            ret.add(root.val);
        }
        //先递归右子树
        dfs(root.right, depth + 1);
        //再递归左子树
        dfs(root.left, depth + 1);
    }
}

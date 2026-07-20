package org.lyflexi.solutions.strategy_retrieval_dfs.back_track.tree;

import org.lyflexi.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3,null,5]
 * 输出：["1->2->5","1->3"]
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：["1"]
 *
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 100] 内
 * -100 <= Node.val <= 100
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 531,058/741.7K
 * 通过率
 * 71.6%
 */
public class LC257_binaryTreePaths {
    List<String> ret = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return ret;
        }
        dfs(root, new StringBuilder());
        return ret;
    }

    private void dfs(TreeNode root, StringBuilder path) {
        if(root == null){
            return;
        }
        int preLen = path.length();
        path.append(root.val);
        if (root.left == null && root.right == null) {
            ret.add(new String(path));//这里不能return, 目的是保证最后的回溯， 也能够作用于这里的叶子节点ret.add(new String(path));
        }
        path.append("->");
        dfs(root.left, path);
        dfs(root.right, path);
        //回溯, 要么回溯上面的叶子节点ret.add(new String(path));， 要么回溯上面的path.append("->");
        path.setLength(preLen);
    }
}

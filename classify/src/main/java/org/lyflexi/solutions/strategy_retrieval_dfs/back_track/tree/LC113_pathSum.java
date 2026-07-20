package org.lyflexi.solutions.strategy_retrieval_dfs.back_track.tree;

/**
 * 113. 路径总和 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 示例 2：
 *
 *
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 *
 *
 * 提示：
 *
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 519,383/808.1K
 * 通过率
 * 64.3%
 */

import org.lyflexi.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树回溯
 */
public class LC113_pathSum {
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, new ArrayList<>(), targetSum);
        return ret;
    }
    private void dfs(TreeNode root, List<Integer> path, int targetSum){
        if(root == null){
            return;
        }
        targetSum -= root.val;
        path.add(root.val);

        if(targetSum == 0 && root.left==null && root.right == null){
            ret.add(new ArrayList<>(path));
        }
        dfs(root.left, path, targetSum);
        dfs(root.right, path, targetSum);
        path.remove(path.size() - 1);
    }
}

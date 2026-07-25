package org.lyflexi.solutions.strategy_retrieval_dfs.binary_search_tree;

import org.lyflexi.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 501. 二叉搜索树中的众数
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
 *
 * 如果树中有不止一个众数，可以按 任意顺序 返回。
 *
 * 假定 BST 满足如下定义：
 *
 * 结点左子树中所含节点的值 小于等于 当前节点的值
 * 结点右子树中所含节点的值 大于等于 当前节点的值
 * 左子树和右子树都是二叉搜索树
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,2]
 * 输出：[2]
 * 示例 2：
 *
 * 输入：root = [0]
 * 输出：[0]
 *
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 104] 内
 * -105 <= Node.val <= 105
 *
 *
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 289,867/511.6K
 * 通过率
 * 56.7%
 */
public class LC501_findMode {
    Map<Integer, Integer> cnt = new HashMap<>();
    int mxCnt = 0;
    public int[] findMode(TreeNode root) {
        dfs(root);
        int n = 0;
        for(int s: cnt.keySet()){
            if(cnt.get(s) == mxCnt){
                n++;
            }
        }
        int[] ret = new int[n];
        int i= 0;
        for(int s: cnt.keySet()){
            if(cnt.get(s) == mxCnt){
                ret[i++] = s;
            }
        }
        return ret;
    }
    private void dfs(TreeNode root){
        if(root == null){
            return;
        }
        dfs(root.left);
        cnt.merge(root.val, 1, Integer::sum);
        mxCnt = Math.max(mxCnt, cnt.get(root.val));
        dfs(root.right);
    }
}

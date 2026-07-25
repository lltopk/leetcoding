package org.lyflexi.solutions.strategy_retrieval_dfs.from_bottom_up.pair;

import org.lyflexi.common.TreeNode;

/**
 * 2265. 统计值等于子树平均值的节点数
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一棵二叉树的根节点 root ，找出并返回满足要求的节点数，要求节点的值等于其 子树 中值的 平均值 。
 *
 * 注意：
 *
 * n 个元素的平均值可以由 n 个元素 求和 然后再除以 n ，并 向下舍入 到最近的整数。
 * root 的 子树 由 root 和它的所有后代组成。
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [4,8,5,0,1,null,6]
 * 输出：5
 * 解释：
 * 对值为 4 的节点：子树的平均值 (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4 。
 * 对值为 5 的节点：子树的平均值 (5 + 6) / 2 = 11 / 2 = 5 。
 * 对值为 0 的节点：子树的平均值 0 / 1 = 0 。
 * 对值为 1 的节点：子树的平均值 1 / 1 = 1 。
 * 对值为 6 的节点：子树的平均值 6 / 1 = 6 。
 * 示例 2：
 *
 *
 * 输入：root = [1]
 * 输出：1
 * 解释：对值为 1 的节点：子树的平均值 1 / 1 = 1。
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 1000] 内
 * 0 <= Node.val <= 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 17,535/20.9K
 * 通过率
 * 83.7%
 */
public class LC2265_averageOfSubtree {
    int ret = 0;
    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ret;
    }

    /**
     要想知道平均值， 必须同时知道和 以及 节点个数
     因此递归函数返回int[]

     由于要统计子树信息，先要递归访问完子树， 才能知道子树信息， 所以要自底向上
     */
    private int[] dfs(TreeNode root){
        if(root == null){
            return new int[]{0, 0};
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        //根据左右子树信息， 计算当前父节点p
        int[] p = new int[2];
        p[0] = left[0] + right[0] + root.val;
        p[1] = left[1] + right[1] + 1;
        if( p[0] / p[1] == root.val){
            ret++;
        }
        return p;
    }
}

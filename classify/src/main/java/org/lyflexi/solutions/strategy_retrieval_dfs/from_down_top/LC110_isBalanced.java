package org.lyflexi.solutions.strategy_retrieval_dfs.from_down_top;

import org.lyflexi.common.TreeNode;

/**
 * 110. 平衡二叉树
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二叉树，判断它是否是 平衡二叉树
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 * 示例 3：
 *
 * 输入：root = []
 * 输出：true
 *
 *
 * 提示：
 *
 * 树中的节点数在范围 [0, 5000] 内
 * -104 <= Node.val <= 104
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 806,275/1.3M
 * 通过率
 * 60.2%
 */
public class LC110_isBalanced {
    public boolean isBalanced(TreeNode root) {
        return mxH(root, 0) == -1?false: true;
    }
    /**
     自底向下求最大高度变形。当不平衡的时候返回-1， 当-1弹回父节点继续返回-1直到根节点
     */
    private int mxH(TreeNode root, int h){
        if(root == null){
            return 0;
        }
        int hL = mxH(root.left, h+1);
        if(hL == -1){
            return -1;
        }
        int hR = mxH(root.right, h+1);
        if(hR == -1 || Math.abs(hR - hL) > 1){
            return -1;
        }
        //归： 0 + 1 = 1
        return Math.max(hL, hR) + 1;
    }
}

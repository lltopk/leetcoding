package org.lyflexi.solutions.bst;

import org.lyflexi.structDef.TreeNode;

/**
 * @Author: liuyanoutsee@outlook.com
 * @Date: 2025/3/30 12:47
 * @Project: interview-coding
 * @Version: 1.0.0
 * @Description: 迭代法进行二叉搜索树的查询目标节点
 */
public class BSTSearch {
    /**
     * 查找节点
     * @param root
     * @param num
     * @return
     */
    TreeNode search(TreeNode root,int num) {
        TreeNode cur = root;

        while (cur != null) {
            // 找到重复节点，直接返回
            if (cur.val == num)
                return cur;
            // 插入位置在 cur 的右子树中
            if (cur.val < num)
                cur = cur.right;
            // 插入位置在 cur 的左子树中
            else
                cur = cur.left;
        }
        // 返回目标节点
        return cur;
    }
}

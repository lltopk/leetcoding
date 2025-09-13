package org.lyflexi.solutions.bst;

import org.lyflexi.structDef.TreeNode;

/**
 * @Author: liuyanoutsee@outlook.com
 * @Date: 2025/3/30 12:49
 * @Project: interview-coding
 * @Version: 1.0.0
 * @Description: 迭代法进行二叉搜索树的插入操作
 */
public class BSTInsert {
    void insert(TreeNode root, int num) {
        if (root == null){
            root = new TreeNode(num);
            return;
        }
        //初始化待插入位置的前驱节点为null
        TreeNode pre = null;
        search(root, pre,num);
        // pre计算完成，执行新增节点
        if (pre != null){
            TreeNode target = new TreeNode(num);
            if (pre.val<num){
                pre.right = target;
            }
            if (pre.val>num){
                pre.left = target;
            }
        }
    }

    /**
     * 查找节点 ,同时计算前驱节点
     * @param root
     * @param pre
     * @param num
     * @return
     */
    TreeNode search(TreeNode root, TreeNode pre,int num) {
        TreeNode cur = root;
        // 循环查找，越过叶节点后跳出
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

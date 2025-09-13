package org.lyflexi.solutions.bst;

import org.lyflexi.structDef.TreeNode;

/**
 * @Author: liuyanoutsee@outlook.com
 * @Date: 2025/3/30 13:01
 * @Project: interview-coding
 * @Version: 1.0.0
 * @Description: 搜索二叉树的删除操作
 */
public class BSTDelete {
    /* 删除节点 */
    void remove(TreeNode root, int num) {
        // 若树为空，直接提前返回
        if (root == null)
            return;
        //初始化待插入位置的前驱节点为null
        TreeNode cur = root, pre = null;
        // 循环查找，越过叶节点后跳出
        search(cur,pre,num);
        // 若无待删除节点，则直接返回
        if (cur == null)
            return;
        // 子节点数量 = 0 or 1 ,当待删除节点的度为1时，将待删除节点替换为其子节点即可。
        if (cur.left == null || cur.right == null) {
            // 当子节点数量 = 0 / 1 时，
            // child = cur的子节点，特别的当子节点数量 = 0时，child = null
            TreeNode child = cur.left != null ? cur.left : cur.right;
            // 删除节点 cur
            if (cur != root) {
                if (pre.left == cur)
                    pre.left = child;
                else
                    pre.right = child;
            } else {
                // 若删除节点为根节点，则重新指定根节点
                root = child;
            }
        }
        // 子节点数量 = 2, 当待删除节点的度为2时，我们无法直接删除它，而需要使用一个节点替换该节点。
        // 由于要保持二叉搜索树“左子树根节点右子树”的性质，因此这个节点可以是右子树的最小节点或左子树的最大节点。
        // 这里我们选择右子树的最小节点进行删除，并替换当前节点
        else {
            // 获取中序遍历中 cur 的下一个节点
            TreeNode tmp = cur.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            // 递归删除节点 tmp
            remove(root,tmp.val);
            // 用 tmp 覆盖 cur
            cur.val = tmp.val;
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

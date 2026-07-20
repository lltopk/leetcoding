package org.lyflexi.solutions.strategy_retrieval_dfs.from_top_down;

import org.lyflexi.common.TreeNode;

/**
 * 623. 在二叉树中增加一行
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二叉树的根 root 和两个整数 val 和 depth ，在给定的深度 depth 处添加一个值为 val 的节点行。
 *
 * 注意，根节点 root 位于深度 1 。
 *
 * 加法规则如下:
 *
 * 给定整数 depth，对于深度为 depth - 1 的每个非空树节点 cur ，创建两个值为 val 的树节点作为 cur 的左子树根和右子树根。
 * cur 原来的左子树应该是新的左子树根的左子树。
 * cur 原来的右子树应该是新的右子树根的右子树。
 * 如果 depth == 1 意味着 depth - 1 根本没有深度，那么创建一个树节点，值 val 作为整个原始树的新根，而原始树就是新根的左子树。
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: root = [4,2,6,3,1,5], val = 1, depth = 2
 * 输出: [4,1,1,2,null,null,6,3,1,5]
 * 示例 2:
 *
 *
 *
 * 输入: root = [4,2,null,3,1], val = 1, depth = 3
 * 输出:  [4,2,null,1,1,3,null,null,1]
 *
 *
 * 提示:
 *
 * 节点数在 [1, 104] 范围内
 * 树的深度在 [1, 104]范围内
 * -100 <= Node.val <= 100
 * -105 <= val <= 105
 * 1 <= depth <= the depth of tree + 1
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 58,444/96.8K
 * 通过率
 * 60.4%
 */
public class LC623_addOneRow {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        //因为涉及头节点， 所以创建dummy
        TreeNode dummy = new TreeNode(0, root, null);
        dfs(dummy, val, depth, 0);
        return dummy.left;
    }

    private void dfs(TreeNode root, int val, int depth, int h){
        if(root == null){
            return;
        }
        if(h + 1 == depth){
            root.left = new TreeNode(val, root.left, null);//插入新节点同时链向原来root的左节点
            root.right = new TreeNode(val, null, root.right);//插入新节点同时链向原来root的右节点
        }

        dfs(root.left, val, depth, h+1);
        dfs(root.right, val, depth, h+1);
    }
}
